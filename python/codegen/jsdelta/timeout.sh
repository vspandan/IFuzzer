#!/bin/sh

# Helper script for running a command under a timeout. 
# Prints "TIMEOUT" to stderr if the command timed out.

timeout -k 1s -s KILL $*
if [ $? -eq 124 ]
then
    echo "TIMEOUT" >&2
fi