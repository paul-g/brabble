#!/bin/bash

function run_benchmark() {
    javac Measuring.java 

    for i in {64..1024..32}
    do
        java Measuring $i -m | tee -a out
    done
}

function run_filter_benchmark() {
    javac Measuring.java 

    for i in {64..1024..32}
    do
        java Measuring $i -f | tee -a out
    done
}

run_benchmark &
sleep 2
gnuplot "plot.gnuplot"
rm out
pkill -P $$
