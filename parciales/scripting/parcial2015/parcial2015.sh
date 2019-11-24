#!bin/bash

SAVEIFS=$IFS
IFS=$(echo -en "\n\b")

for i in $(cat $1); do
    
    pais=$( echo $i | cut -f2)
    ##echo $pais
    ##echo $2
    if [ "$2" == "$pais" ]; then
        ciudad=$(echo $i | cut -f1)
        ##echo $i
        let acum=0
        for value in {3..14}
        do
            mes=$( echo $i | cut -f$value)
            acum=$(echo "scale=8;$acum+($mes*1.8+32)" | bc)
            ##let "acum+=mes"
        done
        prom=$( echo "scale=2;$acum/12" | bc)
        echo -e $ciudad'\t'$prom
    fi
done

IFS=$SAVEIFS