% Performance Aware Programming
% Paul Grigoras

## Last time

1. $Performance = {Wall Clock Time}^{-1}$
2. How to measure _Wall Clock Time_
    * `time`, `/usr/bin/time -v`
    * `java -Xprof`, `jvisualvm`
    * `System.currentTimeMillis()`, `System.nanoTime()`
3. How to run a simple benchmark and plot results
    * Write a script to automate (`bash`, `python`)
    * Plot with `gnuplot` (or `matplotlib`)

## Last Time - Matrix Multiply Loop Interchange

* Matrix multiply
```Java
for (int i = 0; i < n; i++)      // i
  for (int j = 0; j < n; j++)    // j
    for (int k = 0; k < n; k++)  // k
      c.data[i][j] += data[i][k] * other.data[k][j];
```

* Matrix multiply loop interchange
```Java
for (int i = 0; i < n; i++)       // i
  for (int k = 0; k < n; k++)     // k ---- swapped
    for (int j = 0; j < n; j++)   // j --|
      c.data[i][j] += data[i][k] * other.data[k][j];
```

## Last Time - Matrix Multiply Loop Interchange

![](img/loop-interchange.png)

## Last Time - Matrix Multiply Loop Interchange

* This is incredible:
    * both routines perform exactly the same operations
    * still, the loop interchanged version is up to 10X faster...
    * clearly we can't explain this at the _language_ level

*  If you don't know why,
    *  __come to Lecture 3__
\pause
*  If you don't know a _second_ reason,
    * __come to Lecture 3__
\pause
* Just __come to Lecture 3__


## This time - Algorithms

* Why you should have read at least one of these books by the end of
  your degree...


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

* As the name suggests...
\pause
* ...CS explores the _entire space of possible solutions_ to find the
  best one
     * it is definitely __correct__...
     * but can also be __very slow__
\pause
*
```Java
for (Solution sol : generate_all_solutions())
  if (sol.check())
      sol.print()
```


## Complete Search - Recursive, with pruning

* Recursive with pruning
*
```Java
void search(Solution sol)
  if (sol.reject()) return;
  if (sol.check())  sol.print();
  for (Node n : valid_next_nodes(sol)):
    search(new Solution(sol, node))
```
\pause
* `sol.reject()` may discard an entire solution sub-tree, for which it
  would be impossible to obtain a valid solution

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

##

![](img/slow.jpg)

<!-- ## Complete Search - Discrete Knapsack -->

<!-- * This is solution is correct (modulo implementation bugs) -->
<!-- * But how fast is it? -->
<!-- * TODO plots -->

# Correct (Done). Optimise!

## Optimising

* We can see that for a relatively large number of items our solution
  becomes _insanely slow_

* Ideas for optimisation
    * Is there some redundant computation we can eliminate?
    * Is there some operation we can perform more efficiently?

\pause

* Let's have a closer look at the _sub-problems_ we are solving...

## Optimising - Overlapping problems

TODO picture of overlapping subproblems

* We can _cache_ overlapping problems

## Optimising - A note on recursion

* Recursive solutions tend to be slow(ish)
* Unless the recursion is obviously tail-recursive, some compilers may

## Performance Comparison

* Dynamic Programming
    * considerably fewer function calls
    * considerably faster
    * a bit more challenging to develop
    * restricted by maximum problem size
    * Effectively we are trading _space_ for _compute_

# Questions


## Comparison

## Summary

* Hardware optimisation can give us a great performance boost (e.g. 10-20X)
* But, better algorithms are plain awesome:
    * CS --$O(2^n)$ vs DP -- $O(n * w)$

## Algorithms

* Algorithms tree

## Follow Up

* Making programs run faster on your own can be depressing

* Fortunately, there are many online communities:
    * UVA Online
    * Codeforces
    * Hackerrank
    * Topcoder
    * Project Euler

## Next Time

*

* Slides / code from
    * I will also upload them on CATE

# Questions?
