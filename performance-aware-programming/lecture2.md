% Performance Aware Programming
% Paul Grigoras

## Last time

1. A quick look at:
    2. What performance is
    3. How to measure it
    4. How to run a simple benchmark and plot results

2. A surprising example of optimisation -- loop interchange
    3. Same number of operations -- Much better performance
    5. Since same number of operations => the loop interchanged
       version is somehow better for levels that are below the
       programming language (e.g. hardware friendly)
    6. (More in Lecture 3)

## This time

1. What improvement can we get by optimising our _algorithms_

2. We will look at the famous _knapsack_ problem
    3. Start with a correct but _sloooow_ version (Complete Search)
    4. Continue with a faster version (Dynamic Programming)
    5. Show how we can make it even faster (for an extension to the problem) (Greedy)

## The Discrete Knapsack Problem

Given:

1. a list of items (I) with weights ($W_i$) and prices ($P_i$)
2. a backpack with a limited weight capacity ($W_{max}$),

Maximise the profit achieved by fitting items into the backpack.

Mathematically, maximise $\sum$ subject to $\sum$

__Note__
1. You are not allowed to split items.
2. Each item can only be used once.

## The Discrete Knapsack Problem - Example

TODO image


## Complete Search

* Explores the space of possible solutions to find the best one
* Since it explores the _entire space_ it is correct...
* ... but it is also (in general) _very slow_

```
solutions = generate_all_solutions()
for solution in solutions:
  if check(solution):
    print solution
```


## Complete Search - With pruning

```
bactrack(solution)
  if reject(solution) return
  if check(solution) print(solution)
  for node in valid_next_nodes(solutions):
    backtrack(solution ++ node)
```

* reject may seem like a minor addition, but it discards an entire
  solution sub-tree (this is called _pruning_), for which it would be
  impossible to obtain a valid solution

## Complete Search - Discrete Knapsack

TODO Add picture of the items (I)

1. What is a possible solution?
    * A subset of I

2. When do we reject a solution ?
    * When $W_{current} > W_{max}$

3. When is a solution correct and optimal?
    * We only know at the end...


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

## Greedy

1. For KNAP-FR we can do better
    2. Greedy approach

## Sorting in Java



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
