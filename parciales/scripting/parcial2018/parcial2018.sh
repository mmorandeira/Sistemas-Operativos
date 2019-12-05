#!/bin/bash
SAVEIFS=$IFS
IFS=$(echo -en "\n\b")

resultados=$1
for set in $( cat $resultados ); do
    field1=$(echo $set | cut -f1)
    echo $field1
done