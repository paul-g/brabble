This is a set of four introductory lectures on Performance Aware
Programming. Lectures 2 -- 4 are in progress and will be uploaded
after they have been presented.

* __Lecture 1 -- Measuring__ defines performance and looks at how we can measure it in
  our programs.
*  __Lecture 2 -- Algorithms__ -- the impact of smart programs on performance
   implemented
* __Lecture 3 -- Hardware__ -- the impact of the underlying hardware on the
   performance of our programs
* __Lecture 4 -- Tools__ -- how to get the most of our tools when programming

Compiling the slides requires pandoc >= 1.12. Example:

```
pandoc -t beamer lecture1.md -o lecture1.pdf
```

Compiling and running the examples in `src/` requires Oracle Java JDK >= 1.8. Example:

```
javac Measuring.java && java Measuring
```
