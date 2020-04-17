from marshal import load
from os import listdir
if __name__ == '__main__':
	folder="database"
	for file in listdir(folder):
		f=open(folder+"/"+file, "r")
		s=load(f)
		f.close()
		print file
		raw_input()
		print s
		raw_input()

