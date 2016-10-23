#!/usr/bin/env python
from shutil import copyfile
from marshal import dump, load
from collections import defaultdict
from datetime import datetime
from os import listdir,remove,makedirs
from os.path import isfile, join, abspath, exists
from codegen.fitness import FitnessElites, FitnessTournament, FitnessProportionate
from codegen.fitness import ReplacementTournament, MAX, MIN, CENTER
from codegen.GrammaticalEvolution import GrammaticalEvolution
from random import choice
from langparser.AntlrParser import *
import xml.etree.ElementTree as ElementTree
import ConfigParser
import logging

config = ConfigParser.RawConfigParser()
config.read('ConfigFile.properties')

LOG_FILENAME= config.get('Mappings', 'mappings.logfile');
LOG_LEVEL= config.get('Mappings', 'loglevel');

logging.basicConfig(filename=LOG_FILENAME,level=LOG_LEVEL,)

Population_size=int(config.get('Options', 'POPULATION_SIZE'));
FileListFile= abspath(config.get('TargetDir', 'FILELIST'))
database = config.get('TargetDir', 'DATABASE')
identifierKey = config.get('Interpreter', 'IDENTIFIER')
parsetreedir = config.get('TargetDir', 'PARSETREES')

class GECodeGenerator(object):
    def __init__(self):
        self.grammarFile=abspath(config.get('Options', 'GRAMMAR_FILE'))
        self.ges = GrammaticalEvolution()
        self.initialize()

    def initialize(self):
        self.ges.setGrammarFile(self.grammarFile)
        self.ges._extractProductions()
                

    def runFuzzer(self,interpreter,options,returnCodes,preSelectedNonTerminals,shellfileOption):
        tempList={}   
        TestCases={}
        if isfile(FileListFile):
            try:
                f2 = open(FileListFile, 'rb')
                TestCases=load(f2)
                f2.close()
            except Exception as e:
                print (e)
                remove(FileListFile)
                return
        else: 
            return
        try:
            keys=TestCases.keys()
            while len(tempList)<Population_size:
                if len(TestCases)>0:
                    t=choice(keys)
                    val = TestCases[t]
                    tempList[t]=val
                    keys.remove(t)
                    del TestCases[t]
                else:
                    break
            f2 = open(FileListFile, 'wb')
            dump(TestCases, f2)
            f2.close()
            
            if len(tempList) >= Population_size:
                logging.debug(tempList)
                self.ges.set_population_size(Population_size)
                self.ges.set_max_generations(int(config.get('Options', 'MAX_GENERATIONS')))
                self.ges.set_fitness_type(config.get('Options', 'FITNESS_TYPE'), float(config.get('Options', 'MAX_FITNESS_VALUE')))
                
                self.ges.set_fitness_fail(float(config.get('Options', 'FITNESS_FAIL')))
                
                self.ges.set_execution_timeout(int(config.get('Options', 'INTERPRETER_TIMEOUT')))
                
                self.ges.set_fitness_selections(
                    FitnessElites(self.ges.fitness_list, 0.5))

                # self.ges.set_fitness_selections(
                #     FitnessProportionate(self.ges.fitness_list, 'linear'))
                
                self.ges.set_crossover_rate(float(config.get('Options', 'CROSSOVER_RATE')))
                self.ges.set_mutation_rate(float(config.get('Options', 'MUTATION_RATE')))

                self.ges.set_max_depth(int(config.get('Options', 'MAX_DEPTH_EXPANSION')))
                self.ges.set_generative_mutation_rate(float(config.get('Options', 'GENERATIVE_PROBABILITY')))

                self.ges.set_children_per_crossover(int(config.get('Options', 'CHILDREN_PER_CROSSOVER')))
                
                self.ges.set_mutation_type(config.get('Options', 'MUTATION_TYPE'))

                self.ges.set_mutation_count(int(config.get('Options', 'MULTIPLE_MUTATION_COUNT')));
                self.ges.set_crossover_count(int(config.get('Options', 'MULTIPLE_CROSSOVER_COUNT')));
                self.ges._multiple_rate=(float(config.get('Options', 'MULTIPLE_RATE')))

                self.ges.set_max_fitness_rate(float(config.get('Options', 'MAX_FITNESS_INDV_RATE')))
                
                self.ges.set_replacement_selections(
                        ReplacementTournament(self.ges.fitness_list, tournament_size=int(config.get('Options', 'REPLACEMENT_TOURNAMENT_SIZE'))))
                
                self.ges.set_maintain_history(bool(config.get('Options', 'MAINTAIN_HISTORY')))
                
                self.ges.dynamic_mutation_rate(int(config.get('Options', 'DYNAMIC_MUTATION_RATE')))
                self.ges.dynamic_crossover_rate(int(config.get('Options', 'DYNAMIC_CROSSOVER_RATE')))
                
                self.ges.set_crossover_bias_rate(int(config.get('Options', 'CROSSOVER_BIAS_RATE')))
                self.ges.globalObj=config.get('Interpreter', 'GLOBALOBJ').split(",")
                
                if self.ges.create_genotypes(tempList,interpreter,options,returnCodes,preSelectedNonTerminals,shellfileOption):
                    self.ges.run()
                    for gene in self.ges.population:
                        if gene.get_fitness() != self.ges._fitness_fail :
                            generatedPrg= gene.get_program()
                            targetDirectory= abspath(config.get('TargetDir', 'TARGETDIR'))
                            if not exists(targetDirectory):
                                makedirs(targetDirectory)
                            newFile=targetDirectory+"/"+str(len(listdir(targetDirectory))+1)+config.get('Interpreter', 'FILE_TYPE')
                            f=open(newFile,'w')
                            f.write(generatedPrg)
                            f.close
                ges=None
            else:
                remove(FileListFile)
        finally:
            pass
    
    def finalize(self,codeFrags2):
            
            print (datetime.now())
            print ("Finalizing: Grouping Common Frags")
            for key in codeFrags2.keys():
                fileName = abspath(database + "/" + key)
                temp=[]
                if isfile(fileName):
                    f2 = open(fileName, 'rb')
                    temp=load(f2)
                    f2.close()
                temp = temp+codeFrags2.get(key)
                f1 = open(fileName, 'wb')
                dump(temp, f1)
                f1.close()
            print (datetime.now())
            print ("Finalized: Writing to file")
        
        

    def genFragPool(self):
        f2 = open(FileListFile, 'rb')
        fileList=load(f2).keys()
        f2.close()
        codeFrags2=defaultdict(list)
        if not exists(database):
            makedirs(database)
        fileList1=listdir(database)
        while True:
            input1=raw_input("Do you want to Append Fragment Pool ? Y/N : ")
            if input1 in ['y','n']:
                if input1=='y':
                    raw_input("Updating Existing Fragment Pool\n Press any key to continue...")
                else:
                    raw_input("Deleting Existing Fragment Pool\n Press any key to continue...")
                    for f in fileList1:
                        remove(database+"/"+f)
                break;
            else:
                print "Answer must be 'Y' or 'N'"
        count = 0
        for f in fileList:
            count+=1
            try:
                print(f)
                xml=extractCodeFrag(f,identifierKey)
                et1 = ElementTree.fromstring(xml)
                parent_map = dict((p, c) for p in et1.getiterator() for c in p)
                for key in parent_map.keys():
                    nonTerm=key.tag
                    p1=ProgramGen()
                    frag=p1.treeToProg(key)
                    if codeFrags2.has_key(nonTerm):
                        frags=codeFrags2.get(nonTerm)
                        frags.append(frag)
                        codeFrags2[nonTerm]=frags
                    else:
                        codeFrags2[nonTerm]=[frag]
            except Exception as e:
                print "Error:"+str(e)+"\nProcessing:"+f
            if count % 100 == 0:
                self.finalize(codeFrags2)
                codeFrags2.clear()
        self.finalize(codeFrags2)
        print ("Finished; Code generation and testing begins " +str(datetime.now()))

    def collectFiles(self, fileList,FILELISTFILE):
        try:
            if not exists(parsetreedir):
                makedirs(parsetreedir)
            if exists(abspath(parsetreedir+"/"+FILELISTFILE)):
                copyfile(abspath(parsetreedir+"/"+FILELISTFILE),abspath(FILELISTFILE))
                return
            fileList1=listdir(parsetreedir)
            for f in fileList1:
                remove(parsetreedir+"/"+f)
            tempList = {}
            print "Total Number of Files "+ str(len(fileList))
            count=0
            for f in fileList:
                print f
                count+=1
                fi=open(path.abspath(f),"r")
                program=fi.read()
                fi.close()
                xml=parseTree(program)
                fileName=path.abspath(parsetreedir+"/"+str(count))
                fi=open(fileName,"w")
                fi.write(xml)
                fi.close()
                tempList[f]=fileName
                print fileName
            print "Files Listed for Processing "+ str(len(tempList))
            f1 = open(abspath(parsetreedir+"/"+FILELISTFILE), 'wb')
            dump(tempList,f1)
            f1.close()
            copyfile(abspath(parsetreedir+"/"+FILELISTFILE),abspath(FILELISTFILE))
        except Exception as e:
            print (e)
