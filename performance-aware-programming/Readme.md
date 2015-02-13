This is a set of four introductory lectures on Performance Aware
Programming.


#### Lecture 1 - Measuring

 Defines performance and looks at how we can measure it in our
 programs using various Linux builtins and Java method calls. The
 [accompanying example](src/lecture1) shows the impact of the loop
 interchange optimisation on the performance of matrix
 multiplication. A script is provided to run the experiment for
 varying input sizes and plot the results.

####Lecture 2 -- Algorithms

We look at the Discrete Knapsack problem and we show just how large
the performance impact of clever algorithms can be: we start by
developing a Complete Search version and then optimise this to cache
re-ocurring subproblems (cf. Dynamic Programming) to great effect.

#### Lecture 3 -- Hardware

We analyse the impact of the underlying hardware on the performance of
our programs. In particular we investigate how understanding the cache
hierarchy and CPU architecture can help us write code which is more
easily optimised by the JVM.

### In Progress
These lectures are in progress and will be uploaded after they have been presented.

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
