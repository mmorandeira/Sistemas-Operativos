#!bin/bash

for i in $(ls -1 $1); do
    path=$(echo "$i" | cut -d "." -f1 | cut -d "_" -f$2)
    path=$path"/"$(echo "$i" |  cut -d "." -f1  | cut -d "_" -f$3)
    path=$path"/"$(echo "$i" |  cut -d "." -f1  | cut -d "_" -f$4)
    mkdir -p $path
    mv $1"/"$i $path
done