#!/bin/bash

cat $1 | awk -F"\t" '{ if($1 == "tcp") print $0}' | awk -F"\t" -v p1=$2 -v p2=$3 '{split($5,a,":"); if(a[2] >= p1 && a[2] <= p2) print$0}' | sort -k6 > $4
cat $1 | awk -F"\t" '{ if($1 == "udp") print $0}' | awk -F"\t" -v p1=$2 -v p2=$3 '{split($5,a,":"); if(a[2] >= p1 && a[2] <= p2) print$0}' | sort -k6 > $5