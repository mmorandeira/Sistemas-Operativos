#!/bin/bash

SAVEIFS=$IFS
IFS=$(echo -en "\n\b")

function calcular {
    for line in $(cat $1); do
        cantidad=$(echo $line | wc -w)
        if [ "$cantidad" -le "$2" ]; then
            echo "$line"
        fi
    done;
}

calcular $1 $2 > $3
IFS=$SAVEIFS