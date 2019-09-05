#!/bin/bash
SAVEIFS=$IFS
IFS=$(echo -en "\n\b")
for i in $( find "$1" -type f ); do
    filename=`basename "$i"`
    path=`dirname "$i"`
    extension="$(echo "$filename" | cut -d. -f2 )"
    if [ "$extension" = "java" ]; then
        echo $filename
        imports=$(cat $i | grep ^import | sort | uniq | wc -l)
        echo $imports
    fi
done