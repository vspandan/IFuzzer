CWD="$(pwd)"
cd ~/jsengines/firefox
hg pull
hg update
cd js/src
autoconf2.13
if [ -d "build1" ]; then
	cd build1
	../configure --enable-optimize --enable-posix-nspr-emulation --enable-valgrind --enable-gczeal --enable-debug
#	make -j4
	cd ..
fi
if [ -d "build2" ]; then
	cd build2
	../configure --enable-debug --enable-optimize --enable-posix-nspr-emulation --enable-valgrind
	make -j4
	cd ..

fi
if [ -d "build3" ]; then
	cd build3
	../configure --enable-gczeal --enable-optimize="-O2 -g" --enable-posix-nspr-emulation --disable-jemalloc --disable-tests --disable-debug
	make -j4
	cd ..
fi
cd $CWD
