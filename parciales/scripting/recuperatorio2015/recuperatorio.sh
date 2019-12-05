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

##sort -k filename.txt

hayBalotage=false;

##primero = $(cat "$1" | sort -k 3 -n | sed -n '1,1 p' | cut )
primero=$(cat "$1" | sort -k 3 -n -t $'\t' -r | sed -n '1,1 p' )
segundo=$(cat "$1" | sort -k 3 -n -t $'\t' -r | sed -n '2,2 p' )
echo $primero
echo $segundo
porcentajePrimero=$(echo $primero | cut -f 3)
porcentajePrimero=$(echo "scale=4;($porcentajePrimero/$total)*100" | bc)
porcentajeSegundo=$(echo $segundo | cut -f 3)
porcentajeSegundo=$(echo "scale=4;($porcentajeSegundo/$total)*100" | bc)
##(($(echo "$pocentajePrimer>=45.00")))
echo $porcentajePrimero
echo $porcentajeSegundo
if [[ "$porcetajePrimer"<=45.00 ]]; then
    diferencia=$(echo "scale=4;$rimero")
    if
    echo $primero
else
    echo "No hay ballotaje"
fi

##
##for i in $(cat "$1" | sort -k 3 -n | sed -n '1,2 p' ); do
##    cargo=$(echo $i | cut -f2)
##    ##echo $cargo
##    if [ $cargo == $2 ]; then
##        echo $i
##        let "$i+=1"
##    fi
##done
##
IFS=$SAVEIFS