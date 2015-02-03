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

1. What improvement can we get by optimising our algorithms

2. We will look at the famous _knapsack_ problem
    3. Start with a correct but _sloooow_ version (Complete Search)
    4. Continue with a faster version (Dynamic Programming)
    5. Show how we can make it even faster (for an extension to the problem) (Greedy)

## The Knapsack Problem

1. KNAP-DI
2. KNAP-FR

## Complete Search

1. Pseudocode
2. Implementation in Java

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
