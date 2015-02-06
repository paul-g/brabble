#!/binb/bash

javac Main.java

echo " n           Class               Result     Took(ms)       Calls    "
echo "---          -----               ------     --------       -----    "

for i in {10..70..10} 
do
    java Main $i < test.in
    echo "-----------------------------------------------------------------"
done
