% Performance Aware Programming

## What is Performance?

* The canonical definition of performance is (cit. req.)
$$ P = \frac{1}{Wall Clock Time} $$

* Wall Clock Time refers to the execution time of a program as measured
  by a clock on the _wall_\footnote{there are other clocks in a
  computer system, so it's important to distinguish}
* There are other measures
    * _MIPS_ \ \ \   -- Millions of instructions / sec
    * _GFLOPS_ -- Billions of floating point operations / sec
    * _GB/s_ \ \ \   -- DRAM/Disk/Cache/Network bandwidth
    * _CPI_  \ \ \ \ \   -- Cycles per instruction
    * Useful for understanding the bottlenecks of a system

* But these do not tell the _whole story_ as well as $P$ does
      * _As a user I (generally) want my program to run __faster___
      * I don't care about the factors involved

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

## Motivation

* Better _performance_ often translates to:
    * (A lot) Happier customers
    * Substantial savings
* Sample effects of low performance:
    * Lag on Android: Laggy UI => Sad customers :(
    * Data Centers: more servers => increased power/hardware costs
    * Battery life on Mobile devices => more processing time =>
      shorter battery life
    * __Weather prediction__ less simulation accuracy => less accurate
      results

## Challenges

* Maximising performance requires a good understanding of the entire
  software and hardware stack

* Understanding is complicated by the complex interactions between the
  different abstraction layers: system, algorithm, OS, hardware

* Optimisation must not affect correctness of results (sometimes
difficult e.g. when computing with floating point)

<!-- ## Approach -->

<!-- * _Optimisation is the root of all evil_ (Tony Hoare) -->

<!-- 1. Correct implementation -->
<!-- 2. Fast enough? If yes, done. -->
<!-- 3. If not, can you improve the algorithm? -->
<!-- 4. If fast, enough / done -->
<!-- 5. If can't improve algorithm, o -->


## Outline

1. __Measuring__ -- measuring an application performance
2. __Algorithms__ -- the impact of smart programs, efficiently
   implemented
3. __Hardware__ -- understanding the impact of the underlying
   hardware on the performance of our programs
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
  _measure_ the performance of an implementationg

## Estimating Performance - Computational Complexity

* mathematical model for _estimating_ the relative performance of
  algorithms
* Ignores implementation aspects (e.g. assumes all operations cost the same)

```Java
for (int i = 0; i < n; i++)
  for (int j = 0; j < n; j++)
    for (int k = 0; k < n; k++)
       sum += i * j * k;
```

* This algorithm performs $3n^3$ arithmetic operations
* We may refer to it as having _complexity O(n^3)_
    * it performs no worse than $c n^3$ for some c and large ns
    * in practice, the value of _c_ is often important


## Measuring Performance

* We will use $P = \frac{1}{Wall Clock Time}$
* This means (in general) we measure wall clock _execution time_
* Assume we normally run our program with
     * `$ java Measuring 512 -m`

### Measuring Execution Time in Linux

* Can measure runtime from the command line
```
$ time java Measuring 512 -m
real	0m18.522s user	0m18.561s sys	0m0.060s
```
* And also
```
$ /usr/bin/time -v java Measuring 512 -m
```

## Measuring performance

```
$ time java Measuring 512 -m
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

* Let's implement a simple matrix-matrix multiplication:
    *

* Then we will:
     1. save the running times
     2. Do some post-processing
     3. Plot a graph of the results
     4. Compare double precision floating point vs long

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
        java Measuring $i -m | tee -a out
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

## Estimating vs Measuring

* What do you observe by plotting the execution time for matrix
  multiply using double precision vs long

* Why do you think this is the case?

## Estimating vs Measuring

* Now apply a simple loop interchange optimisation:
* replace the innermost loop (k) with its immediate neighbour (j)
* What impact does this have on _algorithmic complexity_?
* What impact does this have on the _measured performance_?
* Why do you think this is the case

## Estimating vs Measuring - Filter Example

* Now consider a simple filter sum algorithm which computes the sum of
  all array elements greater than `limit`
* Implement this function and time it for some random data and various array sizes
* Now sort the array before running the filter sum function
* What impact does this have on _algorithmic complexity_?
* What impact does this have on the _measured performance_?
* Why do you think this is the case

# Questions ?
