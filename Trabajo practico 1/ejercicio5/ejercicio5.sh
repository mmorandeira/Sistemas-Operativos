#!/bin/bash
SAVEIFS=$IFS
IFS=$(echo -en "\n\b")
for i in $( find "$1" -type f ); do
    filename=`basename "$i"`
    path=`dirname "$i"`
    name_tr=$(echo "$filename" | tr A-Z a-z | tr -s ' ' | tr ' ' '_')
    mv $i "$path"/"$name_tr"
done