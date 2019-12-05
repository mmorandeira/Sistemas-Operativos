#!/bin/bash

SAVEIFS=$IFS
IFS=$(echo -en "\n\b")
#
#total=0
#for i in $(cat "$1" ); do
#    cargo=$(echo $i | cut -f2)
#    if [ $cargo == $2 ]; then
#        let "total+=$(echo $i | cut -f3)"
#    fi
#done
#echo $1
#echo empezo prueba
#echo $1
#prueba=$(awk -F"\t" '$2 == "Presidente" { printe $0 }' $1)
#echo $prueba
#echo termino prueba
#primero=$(cat $1 | awk -F"\t" '$2 == "Presidente" { printe $0 }' $1 | sort -k 3 -n -t $'\t' -r | sed -n '1,1 p' )
#echo $primero
#segundo=$(awk -F"\t" '$2 == "Presidente" { printe $0 }' $1 | sort -k 3 -n -t $'\t' -r | sed -n '2,2 p' )
#porcentajePrimero=$(echo $primero | cut -f 3)
#porcentajePrimero=$(echo "scale=4;($porcentajePrimero/$total)*100" | bc)
#porcentajeSegundo=$(echo $segundo | cut -f 3)
#porcentajeSegundo=$(echo "scale=4;($porcentajeSegundo/$total)*100" | bc)
#if [[ "$porcetajePrimer"<=45.00 ]]; then
#    diferencia=$(echo "scale=4;$porcentajePrimero-$porcentajeSegundo" | bc)
#    if [[ diferencia>=10.00 ]]; then
#        echo "No hay ballotaje"
#    else
#        echo "Hay ballotaje"
#        echo $primero
#        echo $segundo
#    fi
#else
#    echo "No hay ballotaje"
#fi

function leerCiudades {
    for file in $(ls -1 $1); do
        cat $1/$file
    done    
}

#$(awk -v pres="$column2" -F"\t" '$1==pres' "$1")

presidente="Presidente"

var=$(leerCiudades $1 | awk -v pres="$presidente" -F"\t" ' $2 == pres {print $0}' | awk -F"\t" '{arr[$1]+=$3}END{for (a in arr) print a, ";",arr[a]}' | tr ';' '\t' | sort -n -k2)
echo "$var"

#for presidente in $(leerCiudades $1 | sort -k 1 | cut -f 1 | uniq); 
#    var=$var$'\n'$(leerCiudades $1 | awk -v pres=$presidente -F"\t" ' $1 == $pres { print $0 }')
#done
#echo $var



#presidente=$(echo "Alberto Fernandez")
#echo $presidente
##var=$( cat $1 )
#var=$(cat $1 | awk -v pres=$presidente -F"\t" ' $1 == "$pres" { sum += $3 } END { print $1,$2,$sum } ')
#echo $var

IFS=$SAVEIFS
