#!/bin/bash
for i in $( find "/home/matias/Desktop/Sistemas Operativos (copy)" -size -100k ); do
    echo $i
done