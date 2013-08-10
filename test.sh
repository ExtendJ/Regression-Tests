#!/bin/bash

if [ $# -lt "1" ]; then
    echo "Usage: $0 <JastAddJ root>"
    exit 1
fi

# build JastAddJ
(cd $1; ant jar)
ant -Djastaddj.jar=$1/jastaddj.jar ${@:2} test
