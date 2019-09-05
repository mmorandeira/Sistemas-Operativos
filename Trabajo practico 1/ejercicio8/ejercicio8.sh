#!/bin/bash

old_ifs=$IFS
IFS='
'
my_root_depth=$(echo $(realpath "$1" | tr -cd '/' | wc -c))
for i in $(find $1 -type d); do
    actual=$(echo $(realpath "$i" | tr -cd '/' | wc -c))
    result=$(($actual - $my_root_depth))
    spaces=""
    for ((j=0;j<result;j++)); do
        spaces=$spaces"   "
    done
    filename=$(basename $i)
    echo $spaces \* $filename
done

IFS=$old_ifs