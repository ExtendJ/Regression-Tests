#!/bin/sh

if [ $# -lt "1" ]; then
    echo "Usage: $0 <JastAdd2 root>"
    exit 1
fi

(cd $1; ant jar) && cp $1/jastaddj.jar . && ant test
