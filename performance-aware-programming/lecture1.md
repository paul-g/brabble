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
* To _maximise_ performance we need to:
    * _estimate_ performance
    * _measure_ performance

## Estimating Performance
* Algorithmic complexity -- a high level model for _estimating_ the
  relative performance of algorithms
* Assumes all operations cost the same
* Ignores low level aspects (e.g. hardware
  implementation/optimisations)

## Measuring Performance

### Various Performance Measures
* The canonical definition of performance is (cit. req.)is
$$ P = 1 / T_w $$
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

## Running Example - Matrix Multiply

## Benchmarking

* Disk
* DRAM
* Cache (L1, L2, L3)
* CPU peak GFLOPS
* CPU peak INT OPS
