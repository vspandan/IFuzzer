#!/usr/bin/env python
from os import listdir, mkdir, makedirs,remove,stat,kill
from os.path import isfile, join, isdir, exists, abspath
from GECodeGenerator import GECodeGenerator
import sys
import ConfigParser

config = ConfigParser.RawConfigParser()
config.read('ConfigFile.properties')

testsuite=config.get('Testsuite', 'TESTSUITE').split(',')

FILE_TYPE = config.get('Interpreter', 'FILE_TYPE')
LIB_FILE = config.get('Interpreter', 'LIB_FILE')
FILELISTFILE= config.get('TargetDir', 'FILELIST')
ESCAPELIST = config.get('Interpreter', 'ESCAPEFILELIST').split(",")

INCLUDE_NT = config.get('Interpreter', 'SELECTEDNT').split(",")

INCLUDE_NT1 = None

fileList = []
libfiLes=[]
shellfileOption = []

shell=config.get('Interpreter', 'SHELL_PATH').split(',')
options=(config.get('Interpreter', 'SHELL_OPTIONS').split(','))
returnCodes=[]
for code in config.get('Interpreter', 'SHELL_RETURN_CODES').split(','):
    returnCodes.append(int(code))
fileOptionSpecifier=config.get('Interpreter', 'SHELL_FILE_SPECIFIER').strip()
    

"""
Lists all the directories and makes a call to list the files
"""
def listAllTestCases(testCasesDir):
    if(isinstance(testCasesDir,list)):
        for dir in testCasesDir:
            listAllTestCasesDir(dir)
    else:
        listAllTestCasesDir(testCasesDir)

"""
Lists all the files in a directory
"""
def listAllTestCasesDir(testCaseDir):
    for f in listdir(testCaseDir):
            fi=join(testCaseDir,f)
            if not isfile(fi):
                listAllTestCasesDir(fi)
            else:
                if fi.endswith(FILE_TYPE):
                    statinfo=stat(abspath(fi))
                    if statinfo.st_size == 0 or statinfo.st_size >= 10000 :
                            continue
                    for escFile in ESCAPELIST:
                        if fi.endswith(escFile):
                            continue
                    fileList.append(abspath(fi))
                    if fi.endswith(LIB_FILE):
                        libfiLes.append(abspath(fi))

def run_cmd(fi,l,option,shellNum):
    try:
        cmd=[]
        cmd.append(shell[shellNum].strip())
        cmd=cmd+option.split()
        if len(shellfileOption)>0:
            cmd=cmd+shellfileOption[shellNum]
        cmd.append(fi)
        p = Popen(cmd, stdout=PIPE,stderr=PIPE)
        l[0]=p
        out, err = p.communicate()
        l[1]=(out,err,p.returncode)
        sys.stdout.flush()
        sys.stderr.flush()
    except Exception as e:
        print e
        pass

"""
Driver function
"""
if __name__ == "__main__":
    args = sys.argv[1:]
    sys.setrecursionlimit(300000)
    listAllTestCases(testsuite)
    g=GECodeGenerator()
    # f=open("shell.js","a")
    for a in range(len(shell)):
        shellfileoption=[]
        for shellfile in libfiLes:
            # f1=open(shellfile,"r")
            # f.write("\n")
            # f.write(f1.read());
            # f1.close()
            shellfileoption.append(shellfile)
            if len(fileOptionSpecifier)>0:
                shellfileoption.append(fileOptionSpecifier)
        shellfileOption.append(shellfileoption)
    # f.close()
    # raw_input(abspath("shellfull.js"))
    if not exists(abspath(FILELISTFILE)):
        g.collectFiles(fileList,FILELISTFILE)
    if args[0]=="0":
        g.genFragPool()
    g.runFuzzer(shell,options,returnCodes,INCLUDE_NT,shellfileOption)