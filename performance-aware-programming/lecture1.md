% Performance Aware Programming

## Motivation

* Scientific computing (e.g. weather prediction) -- Increase throughput
* Mobile Applications -- Increase power efficiency
* Cloud Infrastructure -- Reduce number of servers
* Networking -- Reduce latency

## Challenges

* Maximising performance requires a good understanding of the entire
  software and hardware stack

* Understanding is complicated by the complex interactions between the
  different abstraction layers: system, algorithm, OS, hardware

* Optimisation must not affect correctness of results (sometimes
difficult e.g. when computing with floating point)

## Outline

1. __Measuring__ -- writing programs to measure and visualise systems and
   applications performance
2. __Algorithms__ -- the impact of smart programs, efficiently
   implemented
3. __Tools__ -- how to get the most of our tools when programming
4. __Hardware__ -- understanding the impact of the underlying
   hardware on the performance of our programs

# 1. Measuring

## Methodology
* The canonical definition of performance is (cit. req.)
$$ P = \frac{1}{Wall Clock Time} $$

* There are other measures
    * _MIPS_ \ \ \   -- Millions of instructions / sec
    * _GFLOPS_ -- Billions of floating point operations / sec
    * _GB/s_ \ \ \   -- DRAM/Disk/Cache/Network bandwidth
    * _CPI_  \ \ \ \ \   -- Cycles per instruction
    * Useful for understanding the bottlenecks of a system

* But these do not tell the _whole story_ as well as $P$ does
      * _As a user I (generally) want my program to run __faster___
      * I don't care about the factors involved

## Measuring

* To say whether an application has better performance, we need to
    * _estimate_ performance
    * _measure_ performance

### Estimating Performance
* Algorithmic complexity -- a high level model for _estimating_ the
  relative performance of algorithms
* Assumes all operations cost the same
* Ignores low level aspects (e.g. hardware
  implementation/optimisations)

## Measuring Performance

### Various Performance Measures
* This means (in general) we measure wall clock _execution time_
* How you measure performance depends on the application
* Other measures: _MIPS_, _CPI_, _GFLOPS_ etc.


## Measuring Execution Time in Linux

* Can time how much an application runs

```
time ./my_program
```

## Measuring Execution Time In Java

* Millisecond resolution

```
long start = System.getCurrentTimeMillis();
// Do work
long tookMs = System.getCurrentTimeMillis() - start;
```

* Nanosecond resolution:

```
long startTime  = System.nanoTime();
// ... the code being measured ...
long estimatedTime = System.nanoTime() - startTime;
```
Source: http://docs.oracle.com/javase/7/docs/api/java/lang/System.html#currentTimeMillis()

## Java Profiler

## Estimating vs Measuring

* Floating point operations

* Computer _Science_ vs Computer _Engineering_

## Visualisation

### gnuplot
* use to plot data,  functions etc.

```
$ gnuplot
$ gnuplot>  plot sin(x)/x
$ gnuplot>  splot sin(x*y/20)
$ gnuplot>  plot sin(x) title 'Sine', tan(x) title 'Tangent'
```

* gnuplot _scripts_ -- great for automation

### gnuplot script




## Running Example - Matrix Multiply

## Benchmarking

* Disk
* DRAM
* Cache (L1, L2, L3)
* CPU peak GFLOPS
* CPU peak INT OPS
