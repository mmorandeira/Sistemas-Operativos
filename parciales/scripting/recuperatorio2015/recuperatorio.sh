#!bin/bash

SAVEIFS=$IFS
IFS=$(echo -en "\n\b")

total=0
for i in $(cat "$1" ); do
    cargo=$(echo $i | cut -f2)
    ##echo $cargo
    if [ $cargo == $2 ]; then
        let "total+=$(echo $i | cut -f3)"
    fi
done
echo $total

sort -k filename.txt

hayBalotage=false;
for i in $(cat "$1"); do
    cargo=$(echo $i | cut -f2)
    ##echo $cargo
    if [ $cargo == $2 ]; then
        if ()
    fi
done

IFS=$SAVEIFS