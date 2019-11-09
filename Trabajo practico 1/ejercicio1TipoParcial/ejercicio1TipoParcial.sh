#!/bin/bash


tr '\n' ' ' < $1 | tr ',' ' ' | tr '.' ' ' | tr -s ' '  | tr ' ' '\n' | sort | uniq > words.txt


SAVEIFS=$IFS
IFS=$(echo -en "\n\b")
for i in $(cat words.txt); do
    echo $i $'\t' $(grep -o $i $1 | wc -w) >> aux.txt
done
sort -t $'\t' -k 2,2 -n -r aux.txt > final.txt
max=$(head -n 1 final.txt | cut -f 2 | tr -d ' ')
echo $max
for i in $(cat final.txt); do
   palabra=$(echo $i | cut -f 1)
   freq=$(echo $i | cut -f 2)
   freqfin=$(echo "scale=4;$freq/$max" | bc)
   echo $palabra $'\t' $freqfin
done
IFS=$SAVEIFS
rm aux.txt
rm words.txt
rm final.txt



 

