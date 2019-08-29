#!/bin/bash
function trletter {
    case $1 in
        a)      
            echo 1
            ;;
        b)      
            echo 2
            ;;
        c)      
            echo 3
            ;;
        d)      
            echo 4
            ;;
        e)      
            echo 5
            ;;
        f)      
            echo 6
            ;;
        g)      
            echo 7
            ;;
        h)      
            echo 8
            ;;
        i)      
            echo 9
            ;;
        j)      
            echo 10
            ;;
        k)      
            echo 11
            ;;
        l)     
            echo 12
            ;;
        m)
            echo 13
            ;; 
        *)
            echo -1
            ;;
    esac
}

for i in $( find "$1" -type f ); do
    filename=`basename "$i"`
    path=`dirname "$i"`
    extension="$(echo "$filename" | cut -d. -f2 )"
    name="$(echo "$filename" | cut -d. -f1 )"
    if [ "$extension" = "pgsql" ]; then
        newname="$(echo $filename | cut -c1-10)"
        number="$(expr substr $name 11 1)"
        number_tr="$(trletter $number)"
        mv "$path/$filename" "$path/$newname-$number_tr.pgsql" 
    fi
done
