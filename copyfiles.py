from os import listdir
import sys
from os.path import isfile, join
from shutil import copyfile
if __name__=='__main__':
    args = sys.argv[1:]
    count=len(listdir(args[1]))
    print count
    for f in listdir(args[0]):
    	print f
        fi=join(args[0],f)
        if isfile(fi):
            copyfile(fi, args[1]+"/"+str(count+1)+"_.js")
            count=count+1

