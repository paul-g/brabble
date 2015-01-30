#!/bin/bash

# Runs matrix multiply for size ranging from 64 to 1024 in steps of 32
function run_benchmark() {
    javac Measuring.java 

    for i in {64..1024..32}
    do
        # run the program and write its output both to the file "out" and stdout
        java Measuring $i | tee -a out
    done
}

# spawn a new process to run the benchmark
run_benchmark &

# wait a bit for the benchmark to write data
sleep 2

# # run the gnuplot script
gnuplot "plot.gnuplot"

# clean a bit
rm out
pkill -P $$

