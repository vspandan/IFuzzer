from os import path,stat
from copy import deepcopy
from random import choice
from collections import defaultdict

from re import split
import sys
sys.path.append(path.dirname(path.abspath(__file__))+"/ECMAScript.jar")
sys.path.append(path.dirname(path.abspath(__file__))+"/antlr-4.5-rc-2-complete.jar")
from langparser import CodeFragmentExtractor
import xml.etree.ElementTree as ElementTree

globalobj=[]
identifier=[]

class ProgramGen:
    def __init__(self):
        self.out=""

    def treeToProg1(self,root):
        if root is not None:
            for child in root:
                if child.text is not None:
                    self.out+=child.text
                self.treeToProg1(child)   
                if child.tail is not None:
                    self.out+=child.tail
        return self.out

    def treeToProg(self,root):
        if root is not None:
            if root.text is not None:
                self.out+=root.text
            self.treeToProg1(root)
        return self.out


class processTree(object):
    def __init__(self):
        self.position=0
        self.out=""
        self.subcode={}

    def subCodeGen(self,root,pos):
        for child in root:
            self.position+=1
            if child.text is not None:
                if child.tag == identifier and child.text not in globalobj:
                    self.subcode[pos]=self.subcode[pos]+" _id_"+child.text
                else:
                    self.subcode[pos]=self.subcode[pos]+child.text
            self.subCodeGen(child,pos)
            if child.tail is not None:
                self.subcode[pos]=self.subcode[pos]+child.tail
        
    def printChild(self,root,nTList):
        if root is not None:
            for child in root:
                self.position+=1
                if nTList.has_key(self.position-1):
                    if nTList[self.position-1]==child.tag:
                        self.out += " "+child.tag +" "
                        if child.text is None:
                            self.subcode[self.position-1] = ""
                        else:
                            self.subcode[self.position-1] = child.text
                        self.subCodeGen(child,self.position-1)
                        if child.tail is not None:
                            self.out+=child.tail
                        continue
                if child.text is not None:
                    self.out+=child.text
                self.printChild(child,nTList)   
                if child.tail is not None:
                    self.out+=child.tail
        return self.subcode,self.out    

def extractNonTerminal(input,nonTerminals):        
    def extractNT(root,nonTerminals):
        for child in root:
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

def genCodeFrag(input,nT,INCLUDE_NT_LIST = None, count=1):
    print("GenCodeFrag method - Entered ")
    selectedNTList={}
    subcode={}
    out=""
    try: 
        if len(input) > 0 and len(nT) > 0:
            proceed=True
            root=None
            internalCount=0
            while internalCount < count :
                selectedNt=None
                tempNT=deepcopy(nT)
                while True:
                    selectedNt=choice(tempNT)
                    if INCLUDE_NT_LIST is None:
                        break;
                    tempNT.remove(selectedNt)
                    if selectedNt in INCLUDE_NT_LIST : 
                        proceed=True
                        break
                    if len(tempNT) <= 0:
                        proceed=False
                        break
                indices = [i for i, x in enumerate(nT) if x == selectedNt]
                selected=choice(indices)
                internalCount+=1
                selectedNTList[selected]=selectedNt
                root = ElementTree.fromstring(input)
                print root
                t=processTree()
                if root.text is not None:
                    t.out=root.text
            if proceed:
                subcode,out=t.printChild(root,selectedNTList)
    except:
        pass
    finally:
        print("GenCodeFrag method - Completed ")
        return subcode,out,selectedNTList

"""
True: Program
False: File
"""
def parseTree(input):
    print("Parsing Program - Started")
    if len(input)>0:
        try:
            c=CodeFragmentExtractor()
            output = c.XMLIRGenerator("\n"+input,True)
            c=None
        except:
            return "",[],0
    print("Parsing Program - Completed ")
    return output['parsecode']


"""
Restricting to accept only files;
True: Program
False: File
"""
def extractCodeFrag(fileName):
    print(fileName)
    c=CodeFragmentExtractor()
    f=open(fileName,"r")
    d = c.XMLIRGenerator("\n"+f.read(),True)
    f.close()
    c=None
    xml=d['parsecode']
    return xml

def CountNestedStructures(output,metricNonTerminals):
    nonTerminals={}
    nonTerminals1={}
    nonTerminals2={}
    for nonTerm in metricNonTerminals:
        nonTerminals[nonTerm]=[]
        nonTerminals1[nonTerm]=False
        nonTerminals2[nonTerm]=0
    
    def Analayse(root):
        if root is not None:
            if root.tag in metricNonTerminals:
                nonTerminals1[root.tag]=True
                nonTerminals2[root.tag]+=1
            for child in root:
                Analayse(child)
            if root.tag in metricNonTerminals:
                nonTerminals[root.tag].append(nonTerminals2[root.tag])
                nonTerminals2[root.tag]=0
                nonTerminals1[root.tag]=False

    try:
        root = ElementTree.fromstring(output)
        Analayse(root)
    except:
        return nonTerminals
    return nonTerminals          

if __name__ == '__main__':
    f=open("/home/rubbernecker/ifuzzer_s/test.js","r")
    print parseTree(f.read())
    
    