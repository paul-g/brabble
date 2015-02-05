% Performance Aware Programming
% Paul Grigoras

## Last time

1. What performance is $P = \frac{1}{Wall Clock Time}$
2. How to measure _Wall Clock Time_
    * `time`, `/usr/bin/time -v`
    * Profilers `java -Xprof <Program>`, `jvisualvm`
    * `System.currentTimeMillis()`, `System.nanoTime()`
3. How to run a simple benchmark and plot results
    * Write a script to automate (`bash`, `python`)
    * Plot with `gnuplot` (or `matplotlib`)

## Last Time

* Loop interchange

TODO add picture

\pause

*  Do you know why?
    *  If not -> come to Lecture 3
    *  If yes, can you think of a _second_ reason?

## This time

1. What improvement can we get by optimising our _algorithms_

2. We will look at the famous _knapsack_ problem
    3. Start with a correct but _sloooow_ version (__Complete Search__)
    4. Continue with a faster version (__Dynamic Programming__)

# The Problem

## The Discrete Knapsack Problem

__Given__

1. a list of items (I) with weights ($W_i$) and prices ($P_i$)
2. a backpack with a limited weight capacity ($W_{max}$),

_Maximise the profit achieved by fitting items into the backpack._

\pause

* Mathematically, maximise $\sum P_j$ subject to $\sum W_j <= W_{max}$

\pause

__Note__

1. You are not allowed to split an item.

2. Each item can only be used once.

## The Discrete Knapsack Problem - Example

TODO image

# Solution I - Correct, but slooow...

## Complete Search

![](img/no-idea.jpg)

* When you have no idea what to do... do a _complete search_


## Complete Search

* Explores the space of possible solutions to find the best one
     * it is definitely __correct__...
     * but can also be __very slow__

```
for solution in generate_all_solutions():
  if check(solution):
    print solution
```


## Complete Search - Recursive, with pruning

```
search(solution)
  if reject(solution) return
  if check(solution) print(solution)
  for node in valid_next_nodes(solutions):
    search(solution ++ node)
```

* reject may seem like a minor addition, but it discards an entire
  solution sub-tree (this is called _pruning_), for which it would be
  impossible to obtain a valid solution

## Complete Search - Visualisation



## Complete Search - Discrete Knapsack

TODO Add picture of the items (I)

1. What is a possible solution?
    * A subset of I

2. When do we reject a solution ?
    * When $W_{current} > W_{max}$

3. When is a solution correct and optimal?
    * We only know at the end...


4. What are the valid_next_nodes ?
    * Next unused items


## Complete Search - Discrete Knapsack Recursive

```Java
double solve(int [] w, int [] p,
             int i, int weight)
{

  // terminate when we have no more items to use
  if (i == 0) return 0;

  // if the current item weighs more than our capacity, don't use it
  if (w[i] > weight)
     return solve(w, p, i - 1, weight);

  // else pick the best profit between picking and not picking the item
  return max(solve(w, p, i - 1, weight),
             solve(w, p, i - 1, weight - w[i]) + p[i]);
}
```

## Complete Search - Discrete Knapsack

TODO going to the pub

## Complete Search - Discrete Knapsack

* This is solution is correct (modulo implementation bugs)
* But how fast is it?
* TODO plots

# Correct (Done). Optimise!

## Dynamic Programming

1. We can _cache_ overlapping problems

## Performance Comparison - A Better Look at the Code



## Performance Comparison

1. Dynamic Programming much faster

# Questions


## Comparison


## Summary

1. Hardware optimisation can give us a great performance boost (e.g. 10-20X)
2. Better algorithms are much much better
3. Plot of O(2^n) vs O(n * w)
4. Plot of O(2^n) vs O(nlong) Greedy

## Follow Up

* Making programs run faster on your own can be depressing

* Fortunately, there are many online communities:
    * UVA Online
    * Codeforces
    * Hackerrank
    * Topcoder
    * Project Euler

## Next Time



## Greedy

1. For KNAP-FR we can do better
    2. Greedy approach

## Sorting in Java
