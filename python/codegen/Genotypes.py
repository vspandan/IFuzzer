#!/usr/bin/env python

from datetime import datetime
from re import sub,split
from string import lower
from marshal import load
from os import path
from codegen.Utilities import base10tobase2, base2tobase10
from random import choice
from os.path import abspath

import ConfigParser
import logging

config = ConfigParser.RawConfigParser()
config.read('ConfigFile.properties')

LOG_FILENAME= config.get('Mappings', 'mappings.logfile');
LOG_LEVEL= config.get('Mappings', 'loglevel');

logging.basicConfig(filename=LOG_FILENAME,
                    level=LOG_LEVEL,
                    )

class Genotype(object):

    def __init__(self, member_no):
        self._keys = []        
        self._generation=0
        self.member_no = member_no
        self.local_bnf = {}
        self._fitness = None
        self.keywords = None
        # self._identifiers=set()
        self.identifiers=[]
        self.preSelectedNonTerminals=[]
        self._max_depth = 0
        self.err = ""
        self.out = ""
        self.rc=None
        self.score=0
        self.prgLength=0
        self._initial_member_no=-1
        self.syntaxTree=None
        self.evolutionGraph=[]
        self.origin=None
        self.nonTerminals=[]

    def set_keys(self, keys):
        self._keys = keys
    
    def resolve_variable(self, variable):
        self.score+=1
        values = self.local_bnf[variable]
        value = choice(values)
        #value = sub('[\'()]', '', value)
        return str(value)
    
    def _converge(self, item):
        fileName = abspath(config.get('TargetDir', 'DATABASE') + "/" + item)
        
        if  path.isfile(fileName): 
            f = open(fileName,'r')
            d = load(f)
            f.close()
            s = choice(d)
            return s
        else:
            return self.resolve_variable(item)
            
    
    def _map_variables(self, program):
        try:
            depth = 0
            while True:
                prg_list = program.split()
                position = 0
                continue_map = False
                while position < len(prg_list):
                    item = prg_list[position]
                    if item in self.keywords:
                        prg_list[position]=lower(item)
                    elif item in self._keys:
                        if depth < self._max_depth:
                            prg_list[position] = self.resolve_variable(item)
                        else:
                            prg_list[position] = self._converge(item)
                        continue_map = True
                    elif item in ['identifierName','literal']:
                            prg_list[position] = self._converge(item)
                    position += 1
                    
                depth += 1
                program = ' '.join(prg_list)
                
                if continue_map is False:
                    return program

        except:
            return ""

    def _map_gene(self,selectedNTList):
        program=self.local_bnf['CodeFrag']
        logging.debug("Before mutation incomplete CodeFrag:"+program)
        # subcode=self._map_variables(nonTerminal)
        # program = program.replace(nonTerminal,subcode)
        logging.info("Involved nonTerminals"+str(selectedNTList))
        self.local_bnf['program'] = self._map_variables(self.local_bnf['CodeFrag'])  
        logging.debug("Completed mutation")
        logging.debug("After mutation complete CodeFrag:"+program)
    
    def get_program(self):
        return self.local_bnf['program']

    def get_fitness(self):
        return self._fitness   