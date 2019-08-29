#!/bin/bash
for i in $( find "$1" -type f ); do
    filename=`basename "$i"`
    path=`dirname "$i"`
    extension="$(echo "$filename" | cut -d. -f2 )"
    name="$(echo "$filename" | cut -d. -f1 )"
    #echo $extension
    #echo $filename
    if [ "$extension" = "sql" ]; then
        mv "$path/$filename" "$path/$name.pgsql"
    fi
    #mv $i "$path"/"$name_tr"
done
