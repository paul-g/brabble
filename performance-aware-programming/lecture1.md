% Performance Aware Programming

# Aware != Expert

# What is Performance?

## What is Performance?

* The canonical definition of performance\footnote{Patterson \&
Henessey, Computer Organization and Design}
$$ P = \frac{1}{Wall Clock Time} $$

* Wall Clock Time is the total elapsed time when performing a task
* It includes the processing time as well as time spent waiting for
  I/O, operating system overhead etc.
* There are other times we may be interested to measure
  (e.g. user/kernel CPU time), so it's important to distinguish

## What is Performance?

* System/application designers may also be interested in other measures
    * _MIPS_ \ \ \   -- Millions of instructions / sec
    * _GFLOPS_ -- Billions of floating point operations / sec
    * _GB/s_ \ \ \   -- DRAM/Disk/Cache/Network bandwidth
    * _CPI_  \ \ \ \ \   -- Cycles per instruction
    * Useful for understanding the bottlenecks of a system

* But these do not tell the _whole story_ as well as $P$ does
      * _As a user I (generally) want my program to run __faster___
      * I don't necessarily care about the factors involved

## Is Performance the Most Important Property?

* No! The most important property of a program is _correctness_
* But, often _correctness_ requires _performance_:
    * Launch abort system on the space shuttle triggers correctly...
    * ... 3 seconds after it's supposed to
    * Airbags on a car trigger correctly...
    * ... 5 seconds after impact
* And often, _correct_ but slow programs are plain _useless_:
    * Tomorrow's weather forecast is correct...
    * ...the day after tomorrow
* When improving the performance of a program (_optimising_) __always
check the results are correct__

# Why  Care About Performance?

## Motivation

* Better _performance_ often results in:
    * Improved customer experience
    * Substantial savings in operating costs
    * Facilitating otherwise impossible tasks
* Sample effects of low performance:
    * __Operating System__ Laggy UI => Sad customers :(
    * __Data Centers__ more servers => increased costs
    * __Battery life__ more processing time => shorter battery life
    * __Weather prediction__ smaller simulation accuracy => less accurate
      results

## Challenges

1. Maximising performance requires a good understanding of the entire
  software and hardware stack

2. Complex interaction between the different layers (system, algorithm,
  OS, hardware) may lead to surprising, hard to explain results

3. Optimisation must not affect correctness of results (sometimes
difficult e.g. when computing with floating point)

<!-- ## Approach -->

<!-- * _Optimisation is the root of all evil_ (Tony Hoare) -->

<!-- 1. Correct implementation -->
<!-- 2. Fast enough? If yes, done. -->
<!-- 3. If not, can you improve the algorithm? -->
<!-- 4. If fast, enough / done -->
<!-- 5. If can't improve algorithm, o -->


## Outline

Outline for this and next three lectures:

1. __Measuring__ -- measuring application performance in Linux/Java
2. __Algorithms__ -- the impact of smart programs, efficiently
   implemented
3. __Hardware__ -- the impact of the underlying hardware on the
   performance of our programs
4. __Tools__ -- how to get the most of our tools when programming

# 1. Measuring

## Measuring

* To compare applications on performance we need to
    * _estimate_ performance -- predict how fast and application is
      based on a well established, generic model
    * _measure_ performance -- measure how our implementation performs
      in practice

* We use _computational complexity_ to estimate the performance of an
  algorithm
* We use various system and programming language utilities to
  _measure_ the performance of an implementation

## Estimating Performance - Computational Complexity

* Model for _estimating_ the relative performance of
  algorithms
* Ignores implementation aspects (e.g. all operations identical)
```Java
for (int i = 0; i < n; i++)
  for (int j = 0; j < n; j++)
    for (int k = 0; k < n; k++)
       sum += i * j * k;
```

. . .

* This algorithm performs $3n^3$ arithmetic operations
* We may refer to it as having complexity $O(n^3)$
    * it performs no worse than $c n^3$ for some c and large ns
    * the constant factor c is ignored in big Oh notation
    * in practice, the value of _c_ is often quite important

. . .

* So how do we _measure_ the performance of a program?

## Measuring Performance

* We will use $P = \frac{1}{Wall Clock Time}$
* This means (in general) we measure wall clock _execution time_
* Assume we normally run our program with
     * `$ java Measuring 512`

. . .

### Measuring Execution Time in Linux

* Can measure runtime from the command line
```
$ time java Measuring 512
real	0m18.522s user	0m18.561s sys	0m0.060s
```
* But this one is probably better
```
$ /usr/bin/time -v java Measuring 512
```

## Measuring performance

```
$ time java Measuring 512
real	0m18.522s user	0m18.561s sys	0m0.060s
```
* `real` -- the _wall clock time_
* `user` -- time spent running user code
* `sys` -- time spent running operating system kernel code

### Important!
* `user` and `sys` are _CPU times_ -- they only include
  time spent actually running on the CPU
* the time spent (e.g. on I/O operations), is not included in `user`
  and `sys`, but _it is included_ in `real`
* most of the time we are interested in the `real` time

. . .

* CPU time is reported across cores, which is why `user` + `sys` may
  be greater than `real`

## Java Profiler

### Command Line
* Use the `-Xprof` flag to enable runtime profiling
```
java -Xprof Measuring
```

### Visual VM

1. May find with `locate jvisualvm | grep bin`
2. Start the profiler, then run an application
3. Select correct JVM instance from the GUI and poke around


* Granularity of the profiler may not be enough so we use language
  APIs to measure execution time
* Profiling may introduce overhead which can affect results

## Measuring Execution Time In Java

It is often useful to measure (wall clock) execution time of some
fragments of code ourselves.  We can do this easily in Java.

* Millisecond resolution
```Java
long start = System.getCurrentTimeMillis();
// Do work
long tookMs = System.getCurrentTimeMillis() - start;
```

* Nanosecond resolution:
```Java
long startTime  = System.nanoTime();
```

## Running Example - Matrix Multiply

* Let's implement a simple matrix multiply optimisation
    * $A B = C$, where A, B, C -- square matrices (same rank)
    * $C_{i, j} = \sum\limits_{k=1}^nA_{i,k}B_{k,j}$

* Steps
     1. Measure and save the running times for various sizes
     2. Do some post-processing and plot the results (size vs time)
     3. Optimise the matrix multiplication
     4. Plot and compare optimised vs un-optimised

## Automating with Shell Scripts
* We don't want to repeat any pre/post processing steps
* We write `scripts` to automate these steps
* Scripts are small programs that glue together a few programs and
  can provide easy automation for many use cases
* We can write a script to benchmark our code for various input sizes
```bash
function run_benchmark() {
    javac Measuring.java
    for i in {64..1024..32}
    do
        java Measuring $i | tee -a out
    done
}
```


## Visualisation

### gnuplot
* use to plot raw data,  functions etc. e.g.:
```
$ gnuplot
$ gnuplot>  plot sin(x)/x
```

* we can write _gnuplot scripts_ -- great for automation
* we can even make gnuplot udpate itself using:
```
pause <time>
reread
```

## Estimating vs Measuring - Matrix Multiply

* Now apply a simple loop interchange optimisation
    * swap the innermost loop (k) with its immediate neighbour (j)
* What impact do you expect this optimisation to have on
    * _correctness_?
    * _algorithmic complexity_?
    * _measured performance_?
* Why?

. . .

* Answers and more examples in Lecture 3

# Questions ?
