#!/bin/bash

FILE=$1
counter=0
while read line
do
    arr[counter]=$line
    let counter++
done < $FILE

for ((i=$counter -1; i >=0 ; i--)); do
    echo ${arr[$i]}
done