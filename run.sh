if [ "$1" == "-c" ]; then
	cd langparser
	java -jar antlr-4.5-rc-2-complete.jar -package langparser ECMAScript.g4
	javac -cp "antlr-4.5-rc-2-complete.jar" *.java
	cd ..
	jar cvfm ECMAScript.jar langparser/Manifest.txt langparser/*.class langparser/antlr-4.5-rc-2-complete.jar
	mv ECMAScript.jar langparser/
	rm -rf langparser/*.class
elif [ "$1" == "-s" ]; then
	while true
	do
		date
		jython -J-Xmx2000m -J-XX:-UseGCOverheadLimit GEInterpreterFuzzer.py 2
	done
elif [ "$1" == "-g" ]; then
	jython -J-Xmx2000m -J-XX:-UseGCOverheadLimit GEInterpreterFuzzer.py 0
	while true
	do
		date
		jython -J-Xmx2000m -J-XX:-UseGCOverheadLimit GEInterpreterFuzzer.py 2
	done
else
	echo "run [options]"
	echo "\"-g\" Generation Fragments "
	echo "\"-s\" Spidermonkey 31"
	echo "\"-s\" Generate Parser"
fi;
