This is a set of four introductory lectures on Performance Aware
Programming. 


#### Lecture 1 - Measuring 
 Defines performance and looks at how we can measure it in our programs using various Linux builtins and Java method calls. The [accompanying example](src/lecture1) shows the impact of the loop interchange optimisation on the performance of matrix multiplication. A script is provided to run the experiment for varying input sizes and plot the results.

### In Progress
These lectures are in progress and will be uploaded after they have been presented.

*  __Lecture 2 -- Algorithms__ -- the impact of smart programs on performance
   implemented
* __Lecture 3 -- Hardware__ -- the impact of the underlying hardware on the
   performance of our programs
* __Lecture 4 -- Tools__ -- how to get the most of our tools when programming

Compiling the slides requires pandoc >= 1.12. Example:

```
pandoc -t beamer lecture1.md -o lecture1.pdf
```

Compiling and running the examples in `src/` requires Oracle Java JDK >= 1.8. Example:

```
cd src/lecture1
javac Measuring.java && java Measuring
```


### Requires

1. gnuplot and gnuplot-x11
2. Oracle Java JDK 1.8

You can install gnuplot using

```
sudo apt-get install gnuplot-x11
```

Instructions on installing Java 1.8 can be found [here](http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html).
