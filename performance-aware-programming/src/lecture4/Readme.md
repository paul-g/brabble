# Monte Carlo PI

A simple visualisation of the Monte Carlo PI algorithm.

It will generate and display a number of random points in the square
with the lower left corner at (0, 0) and upper right at (1, 1). It
then counts how many of the points are also inside the circle with
center (0.5, 0.5) of radius 0.5.

To approximate the value of Pi, we then use the fact that the
probability of picking a point in the circle is equal to the ratio of
the areas of the circle and that of the square.

## Instructions

You can compile and run the code with:

```
javac *.java && java Main
```

Run this from the directory containing the source files.
