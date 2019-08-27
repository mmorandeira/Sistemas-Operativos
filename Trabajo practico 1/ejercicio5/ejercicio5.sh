#!/bin/bash
SAVEIFS=$IFS
IFS=$(echo -en "\n\b")
for i in $( ls $1 ); do
    echo $i
    filename=$(basename -- "$i")
    extension="${filename##*.}"
    filename="${filename%.*}"
    echo $filename
    echo $extension
    answer=$(echo "$filename" | tr '[:lower:]' '[:upper:]')
    answer=$(echo "$filename" | tr ' ' '_')
    echo $answer
done