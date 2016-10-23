#!/usr/bin/env python
from copy import deepcopy
from os import makedirs,remove
from threading import Thread
from datetime import datetime
from os import path,listdir,remove,kill
from random import choice, randint, random
from re import sub,split
from subprocess import Popen,PIPE
from langparser.AntlrParser import *
from codegen.fitness import CENTER, MAX, MIN
from codegen.fitness import FitnessList, Fitness, Replacement
from codegen.Genotypes import Genotype
from time import time,sleep
from jsbeautifier import beautify
from tempfile import NamedTemporaryFile
from marshal import load,dump
from lizard import analyze_file

# import tests.jit_test
import signal 
import ConfigParser
import logging

config = ConfigParser.RawConfigParser()
config.read('ConfigFile.properties')

LOG_FILENAME= config.get('Mappings', 'mappings.logfile');
LOG_LEVEL= config.get('Mappings', 'loglevel');

logging.basicConfig(filename=LOG_FILENAME,level=LOG_LEVEL,)

VARIABLE_FORMAT = '(\W+)'
VARIABLE_FORMAT1 = '([a-zA-Z0-9_$]+)'
STOPPING_MAX_GEN = 'max_generations'

class GrammaticalEvolution(object):

    def __init__(self):
        self.shrink_mutation_rate=0
        self.FUNCTION_EXEC_TIMEOUT=5
        self._pre_selected = []
        self.history = []
        self.globalObj=[]
        self.population = []
        self.non_Terminals=[]
        self.stopping_criteria = {
                STOPPING_MAX_GEN: None
                }
        self.fitness_selections = []
        self.replacement_selections = []        
        
        self._crossover_rate = 0.4
        self._children_per_crossover = 2
        self._mutation_type = 's'
        self._mutation_rate = 0.02
        self._max_fitness_rate = 0.5

        self._start_gene_length = None
        self._max_gene_length = None
        self._max_program_length = None

        self.fitness_list = FitnessList(CENTER)
        
        self._generation = 0
        self._fitness_fail = -1000
        self._maintain_history = True

        self._bnf = {}
        self._population_size = 0
        
        self.dynamic_mutation = 0
        self.dynamic_crossover = 0

        self.mutationCount = 1
        self.crossoverCount = 1
        self._multiple_rate = 0
        self._max_depth = 0
        self._generative_mutation_rate=0.5
        self.parsimony_constant=0
        self.meanLength=1
        self.crossover_break=False
        self.mutation_break=False
        self.function_break=False
        self.crossover_bias_rate=0
        self.targetDir=config.get('TargetDir', 'BUGSDIR')
        self.getMetricNonTerminals()
        self.built_in=None

    def extractNonTerminal(self,input,nonTerminals):        
        def extractNT(root,nonTerminals):
            for child in root:
                if child.tag in self.non_Terminals:
                    nonTerminals.append(child.tag)
                extractNT(child,nonTerminals)    
            return nonTerminals
        if len(input)>0:
            try:
                root =ElementTree.fromstring(input)
                nonTerminals=extractNT(root,nonTerminals)
            except:
                pass
        return nonTerminals

    def getMetricNonTerminals(self):
        logging.debug("getMetricNonTerminals Started")
        nonTerminalListStr= config.get('Interpreter', 'METRICS_NON_TEMINALS')
        nonTermList=nonTerminalListStr.split(",")
        self.metricNonTerm={}
        for nonTerm in nonTermList:
            splitValues=nonTerm.split(":")
            self.metricNonTerm[splitValues[0]]=float(splitValues[1])
        logging.debug("getMetricNonTerminals Completed")

    def set_crossover_bias_rate(self,percentage):
        self.crossover_bias_rate=percentage

    def set_max_depth(self,depth):
        self._max_depth=depth

    def set_generative_mutation_rate(self,rate):
        self._generative_mutation_rate=rate

    def set_multiple_rate(self, rate):
        self._multiple_rate = rate

    def set_crossover_count(self, count):
        self.crossoverCount=count

    def set_mutation_count(self, count):
        self.mutationCount=count

    def set_execution_timeout(self, timeout):
        self.execution_timeout = timeout

    def dynamic_mutation_rate(self, ind):
        self.dynamic_mutation = ind
    
    def dynamic_crossover_rate(self, ind):
        self.dynamic_crossover = ind
    
    def setGrammarFile(self,fileName):
        self.grammarFile=fileName
    
    def _extractProductions(self):
        bnf=""        
        f = open(self.grammarFile,'r')
        for line in f:
            bnf+=line;
        f.close()        
        self.set_bnf(bnf)
    
    def set_population_size(self, size):
        size = long(size)
        if isinstance(size, long) and size > 0:
            self._population_size = size
            i = len(self.fitness_list)
            while i < size:
                self.fitness_list.append([0.0, i])
                i += 1
        else:
            raise ValueError("""
                population size, %s, must be a long above 0""" % (size))

    def set_bnf(self, bnf):
        def strip_spaces(key, values):
            values = [value.strip()
                for value in values.split('|') if value]
            return values

        non_Terminals=[]

        bnf_dict = {}
        for item in bnf.split('\n'):
                if item.find(':') >= 0:
                    key, values = item.split(':',1)
                    key = key.strip()
                    bnf_dict[key] = strip_spaces(key, values)
                    non_Terminals.append(key)
                elif item:
                    values = bnf_dict[key]
                    values.extend(strip_spaces(key, item))
                    bnf_dict[key] = values
                else:
                    pass
        self.non_Terminals = non_Terminals   
        self._bnf = bnf_dict

    def set_maintain_history(self, true_false):
            self._maintain_history = true_false

    def set_max_program_length(self, max_program_length):
        max_program_length = long(max_program_length)
        self._max_program_length = max_program_length

    def set_fitness_fail(self, fitness_fail):
        fitness_fail = float(fitness_fail)
        self._fitness_fail = fitness_fail

    def set_mutation_type(self, mutation_type):
        self._mutation_type = mutation_type

    def set_mutation_rate(self, mutation_rate):
        self._mutation_rate = mutation_rate

    def set_crossover_rate(self, crossover_rate):
        self._crossover_rate = crossover_rate

    def set_children_per_crossover(self, children_per_crossover):
        self._children_per_crossover = children_per_crossover

    def set_max_generations(self, generations):
            self.stopping_criteria[STOPPING_MAX_GEN] = generations

    def get_max_generations(self):
        return self.stopping_criteria[STOPPING_MAX_GEN]

    def set_fitness_type(self, fitness_type, target_value=0.0):
        self.fitness_list.set_fitness_type(fitness_type)
        self.fitness_list.set_target_value(target_value)

    def get_fitness_type(self):
        return self.fitness_list.get_fitness_type()

    def set_max_fitness_rate(self, max_fitness_rate):
        self._max_fitness_rate = max_fitness_rate

    def set_fitness_selections(self, *params):
        for fitness_selection in params:
            if isinstance(fitness_selection, Fitness):
                self.fitness_selections.append(fitness_selection)
            else:
                raise ValueError("Invalid fitness selection")

    def set_replacement_selections(self, *params):
        for replacement_selection in params:
            if isinstance(replacement_selection, Replacement):
                self.replacement_selections.append(replacement_selection)
            else:
                raise ValueError("Invalid replacement selection")

    def get_fitness_history(self, statistic='best_value'):
        hist_list = []
        for fitness_list in self.history:
            hist_list.append(fitness_list.__getattribute__(statistic)())
        return hist_list

    def get_best_member(self):
        return self.population[self.fitness_list.best_member()]

    def get_worst_member(self):
        return self.population[self.fitness_list.worst_member()]

    def calcfitness(self):
        logging.debug("calcfitness Started")
        self.meanLength=1
        
        def calculateCovariance(meanFitness):
            logging.debug("calculateCovariance Started")
            value=0
            for gene in self.population:
                value += ( gene.get_fitness() - meanFitness ) * ( gene.prgLength - self.meanLength )
            logging.debug("calculateCovariance Completed")
            return value

        def variance():
            logging.debug("variance Started")
            value=0
            for gene in self.population:
                value += ( gene.prgLength - self.meanLength ) ** 2 
            logging.debug("variance Completed")
            return value
        
        totalLength=0
        totalFitness=0.0
        for gene in self.population:
            starttime = datetime.now()
            gene._generation = self._generation
            gene.starttime = starttime
            gene.set_keys (self.non_Terminals)
            if self._generation == 0 :
                gene._fitness=10
                # logging.debug("First Generation - calling compute_fitness")
                # self.compute_fitness(gene)
                score,prgLength = self.computeSubScore(gene,gene.get_program())
                gene.prgLength=prgLength
                gene._fitness =  gene.score
                    
            self.population[gene.member_no]=gene
            self.fitness_list[gene.member_no][0] = gene.get_fitness()
            totalLength += gene.prgLength
            totalFitness += gene.get_fitness()

        self.meanLength=totalLength/self._population_size
        meanFitness=totalFitness/self._population_size
        varianceValue=variance()
        if varianceValue == 0:
            self.parsimony_constant=0
        else:
            self.parsimony_constant=calculateCovariance(meanFitness)/varianceValue
        logging.debug("calcfitness Completed")

    def run(self, starting_generation=0):
        logging.debug("Starting Code Generation Process")
        self._generation = starting_generation
        starttime=time()
        self.calcfitness()
        self._generation+=1
        logging.debug("completed : "+str(self._generation)+" in "+str(round((time()-starttime))) + " seconds")
        while True:
            logging.debug("Starting "+str(self._generation)+" Generation - "+str(datetime.now()))
            starttime=time()
            if self._maintain_history:
                self.history.append(deepcopy(self.fitness_list))
            if self._continue_processing() and self.fitness_list.best_value() != self._fitness_fail:
                self._perform_endcycle()
                self._generation+=1
                self.calcfitness()
            else:
                break
            diff=round((time()-starttime))
            logging.debug("completed : "+str(self._generation)+" in "+str(diff) + " seconds")
        logging.debug("Completed Code Generation Process")
      
    def create_genotypes(self,fileList,interpreter_Shell,interpreter_Options,interpreter_ReturnCodes,preSelectedNonTerminals,shellfileOption):
        logging.debug("create_genotypes Started")
        self.interpreter_Shell=interpreter_Shell
        self.shellfileOption=shellfileOption
        self.interpreter_Options =interpreter_Options
        self.interpreter_ReturnCodes=interpreter_ReturnCodes
        self.preSelectedNonTerminals=preSelectedNonTerminals
        self._extractProductions()
        
        if len(fileList)<self._population_size:
            logging.debug("create_genotypes Completed")
            return
        member_no = 0
        files=fileList.keys()
        while member_no < self._population_size:
            gene = Genotype(member_no)
            gene.local_bnf = deepcopy(self._bnf)
            gene.local_bnf['<member_no>'] = [gene.member_no]
            # gene.keywords=self._bnf['keyword']+self._bnf['futureReservedWord']
            gene.keywords=[]
            gene.preSelectedNonTerminals=preSelectedNonTerminals
            gene._initial_member_no =  member_no
            
            f=open(path.abspath(files[member_no]),"r")
            program=f.read()
            f.close()
            gene.origin=files[member_no]
            gene.evolutionGraph.append(files[member_no])
            logging.debug(gene.origin)    
            gene.local_bnf["program"]=program
            f=open(path.abspath(fileList[files[member_no]]),"r")
            gene.syntaxTree=f.read()
            f.close()
            
            gene.non_term=self.extractNonTerminal(gene.syntaxTree,[])
            self.population.append(gene)
            
            member_no += 1
        logging.debug("create_genotypes Completed")
        return True;  
    

    def logBug(self,program,shell,option,err):
        logging.debug("logBug Started")
        program+="\n//"+shell+"\n//"+option + "\n//" + str(datetime.now()) + "\n//" + err.replace("\n"," ") +"\n//Generation:"+str(self._generation)
        logging.info("Crash:")
        logging.info(program)
        FILECOUNT = len(listdir(self.targetDir))
        newFile=self.targetDir+"/"+str(FILECOUNT)+".js"
        f1=open(newFile,'w')
        f1.write(program)
        f1.close
        logging.info("logBug Completed - "+newFile)


    def extractIdentifiers(self,et1):
        try:
            identifiers=[]
            parent_map = dict((p, c) for p in et1.getiterator() for c in p)
            for key in parent_map.values():
                if key.tag=='identifierName':
                    if key.text is not None:
                        val=key.text.strip()
                        if val not in self.globalObj and val not in identifiers:
                            identifiers.append(val)
            return identifiers
        except Exception as e:
            logging.info("extractIdentifiers-exception:")
            logging.info(e)

    def compute_fitness(self,gene,identifiers=None,mapIdentifiers=False):
        logging.debug("compute_fitness started")
        ti=time()
        flag=True;
        gene._fitness=self._fitness_fail
        program=gene.local_bnf['program']
        if self._generation > 0:
            program=self.de_EscapeText(gene,program,mapIdentifiers,identifiers)
            try:
                program=beautify(program)
            except Exception as e:
                logging.info("compute_fitness-exception:")
                logging.info(e)
            gene.local_bnf['program']=program

        if len(program) > 0:
            try:
                f=NamedTemporaryFile(delete=False)
                f.close()
                refError=False
                # while True:
                tempFileObj=open(f.name,"w")
                tempFileObj.write(program)
                tempFileObj.close()
                ti1=time()
                l=[None,None]
                t=Thread(target=self.run_cmd,kwargs={'fi':f.name,'l':l,'option':self.interpreter_Options[0],'shellNum':0})
                t.start()
                t.join(self.execution_timeout)
                if t.isAlive():
                    if l[0] is not None:
                        l[0].kill()
                        kill(l[0].pid, 9)
                        sleep(.1)
                        logging.debug("compute_fitness completed - Killed timeout process"+str(time()-ti)+" seconds")
                    gene.rc=None
                    gene.err=None
                    gene.out=None
                    return
                if (time()-ti1) > self.execution_timeout:
                    gene.rc=None
                    gene.err=None
                    gene.out=None
                    return
                (out,err,rc)=l[1]
                gene.rc=rc
                gene.err=err
                gene.out=out
                if 'SyntaxError' in gene.err or 'SyntaxError' in gene.out:
                    logging.debug("compute_fitness completed - Reference Error or SyntaxError :"+ gene.err)
                    return
                execStart=time()
                for a in range(len(self.interpreter_Shell)):
                    foundBug=False
                    for option in self.interpreter_Options:
                        ti1=time()
                        l=[None,None]
                        t=Thread(target=self.run_cmd,kwargs={'fi':f.name,'l':l,'option':option, 'shellNum':a})
                        t.start()
                        t.join(self.execution_timeout)
                        if t.isAlive():
                            if l[0] is not None:
                                l[0].kill()
                                kill(l[0].pid, 9)
                                sleep(.1)
                                logging.debug("compute_fitness completed - Killed timeout process"+str(time()-ti)+" seconds")
                            gene.rc=None
                            gene.err=None
                            gene.out=None
                            return
                        self.run_delta(f.name,option,a)
                        logging.debug("Invoked  interpreter for "+str(time()-ti1) +"seconds")
                        if (time()-ti1) > self.execution_timeout:
                            gene.rc=None
                            gene.err=None
                            gene.out=None
                            return
                        (out,err,rc)=l[1]
                        gene.rc=rc
                        gene.err=err
                        if rc not in self.interpreter_ReturnCodes:
                            logging.debug("Found CRASH")
                            foundBug=True
                            self.logBug(program,self.interpreter_Shell[a],option,err)
                            logging.debug("Logged CRASH")
                if foundBug:
                    gene._fitness=self.fitness_list.get_target_value();
                    return
                score,prgLength = self.computeSubScore(gene,program,err,time()-execStart)
                gene.score+=score
                gene._fitness =  gene.score - (self.parsimony_constant * gene.prgLength )
            except Exception as e:                    
                logging.info("compute_fitness-1-exception:")
                logging.info(e)
                logging.info("compute_fitness completed with exception"+str(time()-ti)+" seconds")
            finally:
                try:
                    logging.debug("compute_fitness completed"+str(time()-ti)+" seconds")
                    remove(f.name)
                except Exception as e:
                    logging.info("compute_fitness-2-exception:")
                    logging.info(e)
                    pass
        
    def run_delta(self, fi,option,shellNum):
        try:
            cmd=["node"]
            cmd.append("codegen/jsdelta/delta.js")
            cmd.append("--cmd")
            runcmd=self.interpreter_Shell[shellNum].strip()
            opt=option.strip()
            if len(opt)>0:
                runcmd=runcmd+" "+opt+" "
            if len(self.shellfileOption)>0:
                runcmd=runcmd+' '.join(self.shellfileOption[shellNum])
            
            cmd.append(runcmd)
            cmd.append(fi)
            p = Popen(cmd, stdout=PIPE,stderr=PIPE)
            out, err = p.communicate()
            sys.stdout.flush()
            sys.stderr.flush()
        except Exception as e:
            logging.info("run_delta-exception:")
            logging.info(e)
            pass

    def run_cmd(self, fi,l,option,shellNum):
        try:
            cmd=[]
            cmd.append(self.interpreter_Shell[shellNum].strip())
            opt=option.strip()
            if len(opt)>0:
                cmd=cmd+opt.split()
            if len(self.shellfileOption)>0:
                cmd=cmd+self.shellfileOption[shellNum]
            cmd.append(fi)
            p = Popen(cmd, stdout=PIPE,stderr=PIPE)
            l[0]=p
            out, err = p.communicate()
            l[1]=(out,err,p.returncode)
            sys.stdout.flush()
            sys.stderr.flush()
        except Exception as e:
            logging.info("run_cmd-exception:")
            logging.info(e)
            pass

    def computeSubScore (self, gene, program,err="",exec_time=0):
        logging.debug("computeSubScore started")
        pLen=1
        try:
            score=0.0
            ti=time()
            i = analyze_file.analyze_source_code("test.js", program)
            cycloMetricComplexity=1
            pLen=i.token_count
            score= -float(exec_time)/pLen+1
            funcListSize=len(i.function_list)+1
            for index in range(funcListSize-1):
                cycloMetricComplexity += i.function_list[index].cyclomatic_complexity
            avgCycloMetricComplx=cycloMetricComplexity/funcListSize+1
            nonTerminalsMeticsInfo=CountNestedStructures(gene.syntaxTree,self.metricNonTerm.keys())
            for a in nonTerminalsMeticsInfo.keys():
                for temp in nonTerminalsMeticsInfo[a]:
                    score += temp*self.metricNonTerm[a]
            if "warning" in err:
                logging.debug("warning found: "+err)
                score+=10
            score=score/avgCycloMetricComplx
        except Exception as e:
            logging.info("computeSubScore-exception:")
            logging.info(e)
            pass
        logging.debug("computeSubScore completed")
        return score,pLen

    def _perform_endcycle(self):
        logging.debug("performing crossover and mutation")
        self._pre_selected = self._evaluate_fitness(True)
        childList=[]
        remainingPopCount = self._population_size - len(self._pre_selected)
        while len(childList) < remainingPopCount:
            if round(random(),1) <= 0.7:
                limitSelection=True
            else:
                limitSelection=False
            limitSelection=False
            ch=choice([0,1])
            ch=0 #forced order crossover-> perform mutation on generated childen; commenting some previous logic to accomodate this
            if ch==0:
                fitness_pool = self._evaluate_fitness(False,limitSelection)
                child_list1 = self._perform_crossovers(fitness_pool)
                # if child_list is not None:
                #     childList.extend(child_list)   
                # fitness_pool = self._evaluate_fitness()
                # new logic
                child_list = self._perform_mutations(child_list1,len(child_list1))
                # child_list = self._perform_mutations(fitness_pool,(remainingPopCount-len(childList)))
                if child_list is not None:
                    childList.extend(child_list)
            else:
                fitness_pool = self._evaluate_fitness()
                child_list = self._perform_mutations(fitness_pool,(remainingPopCount-len(childList)))
                if child_list is not None:
                    childList.extend(child_list)
                    
                fitness_pool = self._evaluate_fitness(False,limitSelection)
                child_list = self._perform_crossovers(fitness_pool)
                if child_list is not None:
                    childList.extend(child_list)    
        self._perform_replacements(childList)
        logging.debug("completed performing crossover and mutation")

    def _evaluate_fitness(self,ind=False,limitSelection=False): 
        logging.debug("_evaluate_fitness started")
        parentlist = []
        parentlist1 = []
        if ind:
            total = int(round(self._max_fitness_rate * float(self._population_size)))
        else: 
            total = len(self.population)
        count = 0
        if limitSelection or ind:
            for fsel in self.fitness_selections:
                fsel.set_fitness_list(self.fitness_list)
                selected=fsel.select()
                for i in selected:
                    if count == total:
                        break
                    parentlist.append(i)
                    count += 1
            for member_no in parentlist:
                gene=self.population[member_no]
                if gene._fitness!=self._fitness_fail:
                    parentlist1.append(deepcopy(gene))
        else:
            for gene in self.population:
                if gene._fitness!=self._fitness_fail:
                    parentlist1.append(deepcopy(gene))
        logging.debug("_evaluate_fitness completed")
        return parentlist1

    def _perform_crossovers(self, parentlist):
        logging.debug("_perform_crossovers started")
        ti=time()
        child_list = []
        try:
            length = int(round(self._crossover_rate * float(self._population_size)))
            """
            If no of fitness selections is less than no of indv undergoing crossover, than only no equal to no of fitness selections are allowed to undergo process.
            """
            if len(parentlist) < length:
                length=len(parentlist)
            if length % 2 == 1:
                length -= 1
            if length >= 2:
                logging.debug("_perform_crossovers " + str(len(parentlist)) + " individuals are participating in the crossover")
                while len(parentlist) >= 2 :
                    try:
                        logging.info("_perform_crossovers - Remaining " + str(len(parentlist)) + " individuals are participating in the crossover")
                        child1 = choice(parentlist)
                        parentlist.remove(child1)
                        child2 = choice(parentlist)
                        parentlist.remove(child2)

                        child1Prg=child1.get_program()
                        child1.local_bnf['prevprogram']=child1Prg
                        child2Prg=child2.get_program()
                        child2.local_bnf['prevprogram']=child2Prg

                        if self.preSelectedNonTerminals is not None:
                            commonNonTerm=[val for val in child1.non_term if (val in set(child2.non_term) and val in self.preSelectedNonTerminals)]
                        else:
                            commonNonTerm=[val for val in child1.non_term if (val in set(child2.non_term))]

                        trail=0
                        while trail<5:
                        
                            trail += 1
                            count=1
                            selectedNTList = []

                            if round(random(),1) < self._multiple_rate:
                                count=int(self.crossoverCount*(round(random(),1)))+1
                            
                            if len(commonNonTerm) == 0:
                                break
                            # child1.syntaxTree=parseTree(child1Prg)
                            # child2.syntaxTree=parseTree(child2Prg)
                            et1 = ElementTree.fromstring(child1.syntaxTree)
                            et2 = ElementTree.fromstring(child2.syntaxTree)

                            # et11=child1.syntaxTree
                            # et12=child2.syntaxTree

                            # st1=""
                            
                            i=0;
                            while True:
                                k=choice(commonNonTerm)
                                li2=[]
                                for r in et2.iter(k):
                                    li2.append(r)

                                li1=[]
                                for r in et1.iter(k):
                                    li1.append(r)


                                if len(li1)==0 or len(li2)==0 :
                                    continue
                                try:
                                    identifiers1=self.extractIdentifiers(et1)
                                    identifiers2=self.extractIdentifiers(et2)
                            
                                    selectedXMLNode1= choice(li1)
                                    selectedXMLNode2= choice(li2)
                                    child_1 = selectedXMLNode1.getchildren()
                                    child1DeepCopy=deepcopy(child_1)
                                    child_2 = selectedXMLNode2.getchildren()
                                    child2DeepCopy=deepcopy(child_2)

                                    while len(child_1):
                                        ch=child_1[0]
                                        selectedXMLNode1.remove(ch)

                                    while len(child_2):
                                        ch=child_2[0]
                                        selectedXMLNode2.remove(ch)

                                    for ch in child2DeepCopy:
                                        selectedXMLNode1.append(ch)

                                    for ch in child1DeepCopy:
                                        selectedXMLNode2.append(ch)
                                    

                                    subTreeIdentifiers1= self.extractIdentifiers(selectedXMLNode2) #generated child1
                                    subTreeIdentifiers2= self.extractIdentifiers(selectedXMLNode1) #generated child2

                                    mapping1={}
                                    if len(identifiers1)-len(subTreeIdentifiers2)>0:
                                        for elem in selectedXMLNode1.iter('identifierName'):
                                            if elem.text in mapping1:
                                                elem.text=mapping1[elem.text]
                                            else:
                                                ident=""
                                                if len(identifiers1)-len(subTreeIdentifiers2)!=0:
                                                    idents=[x for x in identifiers1 if x not in subTreeIdentifiers2]
                                                    if len(idents)>0:
                                                        ident=choice(idents)
                                                    else:
                                                        ident=choice(identifiers1)
                                                else:
                                                    ident=choice(identifiers1)
                                                mapping1[elem.text]=ident
                                                elem.text = ident

                                    mapping2={}
                                    if len(identifiers2)-len(subTreeIdentifiers1)>0:
                                        for elem in selectedXMLNode2.iter('identifierName'):
                                            if elem.text in mapping2:
                                                elem.text=mapping2[elem.text]
                                            else:
                                                ident=""
                                                if len(identifiers2)-len(subTreeIdentifiers1)!=0:
                                                    idents=[x for x in identifiers2 if x not in subTreeIdentifiers1]
                                                    if len(idents)>0:
                                                        ident=choice(idents)
                                                    else:
                                                        ident=choice(identifiers2)
                                                else:
                                                    ident=choice(identifiers2)
                                                mapping2[elem.text]=ident
                                                elem.text = ident

                                    i+=1;
                                    selectedNTList.append(k)
                                    if (i==count):
                                        break
                            	except Exception as e:
                                	logging.info("_perform_crossovers-1-exception:")
                                	logging.info(e)
                                	pass
                        if et1 is None or et2 is None:
                            break
                        
                        p1=ProgramGen()
                        p2=ProgramGen()
                        
                        child1.local_bnf['program']=p1.treeToProg(et1)
                        child2.local_bnf['program']=p2.treeToProg(et2)

                        child1.score=10
                        child2.score=10
                        logging.debug("_perform_crossovers - calling compute_fitness - child1")
                        self.compute_fitness(child1)
                        logging.debug("_perform_crossovers - calling compute_fitness - child2")
                        self.compute_fitness(child2)
                        
                        logging.debug("_perform_crossovers - child1 - "+str(child1.get_fitness()) +" child2 - "+str(child2.get_fitness()))
                        if child1.get_fitness()!= self._fitness_fail and child2.get_fitness()!= self._fitness_fail:
                            child1.syntaxTree=ElementTree.tostring(et1)
                            child2.syntaxTree=ElementTree.tostring(et2)
                            child1.non_term=self.extractNonTerminal(child1.syntaxTree,[])
                            child2.non_term=self.extractNonTerminal(child2.syntaxTree,[])

                            if self._children_per_crossover == 2:
                                child_list.append(child1)
                                child_list.append(child2)
                            else:
                                child_list.append(child1)
                            logging.info("Crossover-Success")
                        else:
                            logging.info("Crossover-Failed")
                            logging.info(selectedNTList)
                            logging.debug("Err Code (Child1) ::"+str(child1.rc))
                            logging.debug("Out:"+str(child1.out))
                            logging.debug("Err:"+str(child1.err))
                            logging.debug("Origin:"+child1.origin)
                            logging.debug("Err Code (Child2) ::"+str(child2.rc))
                            logging.debug("Out:"+str(child2.out))
                            logging.debug("Err:"+str(child2.err))
                            logging.debug("Origin:"+child2.origin)
                            child1.local_bnf['program']=child1Prg
                            child2.local_bnf['program']=child2Prg
                    except Exception as e:
                        logging.info("_perform_crossovers-2-exception:")
                        logging.info(e)
                        pass
                logging.debug("_perform_crossovers completed")
                return child_list
        except Exception as e:
            logging.info("_perform_crossovers-3-exception:")
            logging.info(e)
            logging.info("_perform_crossovers completed with exception")
            return child_list

    def genIncompleteSyntaxTree(self,gene,count):
        logging.debug("genIncompleteSyntaxTree started")
        et1 = ElementTree.fromstring(gene.syntaxTree)
        selectedNt=[]
        i=0;
        while i<count:
            k=choice(gene.non_term)
            if k not in self.preSelectedNonTerminals:
                continue
            li=[]
            for r in et1.iter(k):
                li.append(r)
            if len(li)>0:
                selectedNt.append(k)
                selectedXMLNode1= choice(li)
                child1 = selectedXMLNode1.getchildren()
                child1DeepCopy=deepcopy(child1)
                while len(child1):
                    ch=child1[0]
                    selectedXMLNode1.remove(ch)
                etTemp = ElementTree.fromstring("<MutationNode> "+k+" </MutationNode>")
                selectedXMLNode1.append(etTemp)
                i+=1
        t=ProgramGen()
        logging.debug("genIncompleteSyntaxTree completed")
        return t.treeToProg(et1),selectedNt,et1

            
    def mutate(self,gene):
        try:
            logging.debug("mutate started")
            pr=gene.local_bnf['program']
            ti3=time()
            if round(random(),1) < self._mutation_rate :
                shrink=False

                count=1
                if len(gene.non_term) > 1:
                    if round(random(),1) < self._generative_mutation_rate :
                        gene._max_depth=self._max_depth
                    else:
                        gene._max_depth=0
                    
                    if round(random(),1) < self._multiple_rate:
                        count=int(self.mutationCount*round(random(),1))+1

                    if round(random(),1) < self.shrink_mutation_rate:
                        shrink=True
                    
                    trail=0
                
                    while trail < 3:
                        trail+=1
                        # gene.syntaxTree=parseTree(pr)
                        gene.local_bnf['CodeFrag'],selectedNt,et1=self.genIncompleteSyntaxTree(gene,count)
                        
                        if len(selectedNt) <=0 :
                            logging.debug("Mutation-Failed-Not selected any non-terminal")
                            return None
                
                        if shrink:
                            for val in selectedNt:
                                gene.local_bnf['CodeFrag'].replace(val,'')
                            break

                        gene.score=10
                        
                        gene._map_gene(selectedNt)
                        identifiers=self.extractIdentifiers(et1)
                        logging.debug("mutate - calling compute_fitness")
                        self.compute_fitness(gene,identifiers,True)
                        if gene.get_fitness() != self._fitness_fail:
                            gene.syntaxTree=parseTree(pr)
                            gene.non_term=self.extractNonTerminal(gene.syntaxTree,[])
                            logging.info("Mutation-Success")
                            break
                        else:
                            gene.local_bnf['program']=pr
                            logging.debug(gene.rc)
                            logging.debug(gene.out)
                            logging.debug(gene.err)
                            logging.debug(gene.origin)
                            logging.info("Mutation-Failed")
        finally:
            pass
        logging.debug("mutate completed" +str(time()-ti3))
        return gene               
    
    def _perform_mutations(self, mlist, count):
        logging.debug("_perform_mutations started")
        mutatedList=[]
        for gene in mlist:
            try:
                result=self.mutate(gene)
                if result is not None:
                    mutatedList.append(result)
                    mlist.remove(gene)
                if len(mutatedList) == count:
                    break
            except Exception as e:
                logging.info("_perform_mutations-exception:")
                logging.info(e)
                return mlist
        logging.debug("_perform_mutations completed")
        return mutatedList

    def _perform_replacements(self, fitness_pool):
        logging.debug("_perform_replacements started")
        position = 0
        for gene in self._pre_selected:
            gene.member_no=position
            self.population[position]=gene
            position+=1
        
        fitness_pool.sort(key=Genotype.get_fitness,reverse=True)
        for gene in fitness_pool:
            if position<self._population_size:
                gene.member_no=position
                self.population[position]=gene
                position+=1
            else:
                break
        logging.debug("_perform_replacements completed")

    def de_EscapeText(self, gene, string,mapIdentifiers=False,identifiers=None):
        logging.debug("de_EscapeText started")
        # wordList=split(VARIABLE_FORMAT, string)
        wordList=string.split()
        modifiedWordList=[]
        mapping={}
        for word in wordList:
            if "&lt" in word:
                word=word.replace("&lt;","<")
            elif "&gt" in word:
                word=word.replace("&gt;",">")
            elif "&quot" in word:
                word=word.replace("&quot;","\"")
            elif "&amp" in word:
                word=word.replace("&amp;","&")
            elif "&apos" in word:
                word=word.replace("&apos;","\\")
            elif "&pipe" in word:
                word=word.replace("#pipe;","|")
            if mapIdentifiers:
                if "<<id>>_" in word:
                    if word in mapping:
                        word=mapping[word]
                    else:
                        word=choice(identifiers)
            modifiedWordList.append(word)
        logging.debug("de_EscapeText completed")
        return ' '.join(modifiedWordList)

    def _continue_processing(self):
        logging.debug("_continue_processing started")
        """
        This method analyzes the fitness list against the stopping_criteria defined over target_value and max generations
        """
        status = True
        fitl = self.fitness_list
        s=set()
        for i in range(len(fitl)):
            s.add(fitl[i][0])
        logging.debug(self.interpreter_Shell)
        logging.debug("Mutation_rate:"+str(self._mutation_rate) +",crossover_rate:"+str(self._crossover_rate)+",multiple_rate:"+str(self._multiple_rate))
        logging.info("Unique: "+str(len(s)) +" Genotype Objects based on Fitness")
        logging.info("Fitness values : After Generation " + str(self._generation))
        logging.info(str(datetime.now()))
        logging.debug(fitl)
        
        if self.stopping_criteria[STOPPING_MAX_GEN] is not None:
            if self.stopping_criteria[STOPPING_MAX_GEN] <= self._generation:
                logging.debug("_continue_processing completed")
                return False
        if fitl.get_target_value() is not None:
                if fitl.best_value() == fitl.get_target_value():
                    logging.debug("_continue_processing completed")
                    return False
        logging.debug("_continue_processing completed")
        return status