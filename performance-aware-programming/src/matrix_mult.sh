#!/bin/bash

function run_benchmark() {
    javac Measuring.java 

    for i in {64..1024..32}
    do
        java Measuring $i | tee -a out
    done
}

run_benchmark &
sleep 2
gnuplot "plot.gnuplot"
rm out
pkill -P $$


# echo "Slow matrix multiply"
# grep 'Took' out | sed -n 'n;p' > slow.out

# echo "Fast matrix multiply"
# grep 'Took' out | sed -n 'p;n' > fast.out
    
# echo "Sizes"
# grep 'Size' out > size.out

# paste -d" " size.out slow.out fast.out > mmult.dat

# sed -i 's/Took: //g' mmult.dat
# sed -i 's/Size: //g' mmult.dat

# # Clean
# rm out slow.out fast.out size.out

