#!/bin/bash
SAVEIFS=$IFS
IFS=$(echo -en "\n\b")
for i in $( find "$1" -size -$2k -type f ); do
    echo $i
    echo 'Desea eliminar el archivo?(y/n)'
    read answer
    if [ "$answer" = "y" ]; then
        echo "--------------------------------------"
        rm $i
        #echo "/home/matias/Desktop/Sistemas Operativos (copy)"$i
    else
        echo 'Desea comprimir el archivo?(y/n)'
        read answer
        if [ "$answer" = "y" ]; then
            # cd "$(dirname "$i" )"
            zip "$(echo "$i" | cut -d. -f1 )" "$i"  
        fi
    fi
done