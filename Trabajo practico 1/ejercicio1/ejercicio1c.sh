#!/bin/bash

FILE=$1
counter=0
IFS='
'
for i in $(cat $1); do
    arr[counter]=$i
    let counter++
done

for ((i=$counter -1; i >=0 ; i--)); do
    echo ${arr[$i]}
done