#!/bin/bash

SAVEIFS=$IFS
IFS=$(echo -en "\n\b")


dia=$(echo $2 | cut -d':' -f1)
mes=$(echo $2 | cut -d':' -f2)
anio=$(echo $2 | cut -d':' -f3)
fecha1=$(echo $anio":"$mes":"$dia)
dia=$(echo $3 | cut -d':' -f1)
mes=$(echo $3 | cut -d':' -f2)
anio=$(echo $3 | cut -d':' -f3)
fecha2=$(echo $anio":"$mes":"$dia)
#echo $fecha1,$fecha2

function leerLog {
    for linea in $(cat $1); do
        id=$(echo $linea | cut -d',' -f1)
        dia=$(echo $linea | cut -d',' -f2 | cut -d' ' -f1 | cut -d':' -f'1')
        mes=$(echo $linea | cut -d',' -f2 | cut -d' ' -f1 | cut -d':' -f'2')
        anio=$(echo $linea | cut -d',' -f2 | cut -d' ' -f1 | cut -d':' -f'3')
        horas=$(echo $linea | cut -d',' -f2 | cut -d' ' -f2)
        duracion=$(echo $linea | cut -d',' -f3)
        red=$(echo $linea | cut -d',' -f4)
        echo -en "$id","$anio":"$mes":"$dia" " " "$horas","$duracion","$red" "\n"
    done
}

#$2<=fecha2 && s2>=fecha1

function crearArchivo {
    for file in $(ls -1 $1); do
        #echo $file
        #echo for1
        var=$(leerLog $1/$file | awk -v fecha1="$2" -v fecha2="$3" -F"," ' $2 >= fecha1 && $2<=fecha2 {print $0}' | awk -F"," '{arr[$4]+=$3}END{for (a in arr) print a, ";",arr[a]}' | tr ';' '\t'  | sort -n -k2 -r | sed -n '1,1 p')
        echo -en "$file""\t""$var""\n"
    done
}

crearArchivo "$1" "$fecha1" "$fecha2" > $4


IFS=$SAVEIFS