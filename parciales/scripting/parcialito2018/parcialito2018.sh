
SAVEIFS=$IFS
IFS=$(echo -en "\n\b")

if [ "$2" -lt "1" ]; then
    echo "ERROR el primer parametro debe ser mayor o igual a 1"
else
    if [ "$3" -lt "$2" ]; then
        echo "ERROR intervalo no valido"
    else
        cant=$(wc -l $1 | cut -d ' ' -f1);
        if [ "$cant" -lt "$3" ]; then
            echo "ERROR el 3er parametro debe ser menor o igual que la longitud del archivo"
        else
            index=1
            for line in $(cat $1); do
                if [ "$index" -le "$3" ] && [ "$index" -ge "$2" ] ; then
                    echo $line                    
                fi
                let "index+=1"
            done
        fi
    fi
fi

IFS=$SAVEIFS