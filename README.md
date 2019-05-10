# JDataTest

This repository is a simple framework for manipulating and plotting 2D data sets.
The goal is to have a set of points in 2D-space, sort them in some chosen order 
and then print the resulting chain of points as a vector graphic.

# Example graphics

[This](https://raw.githubusercontent.com/hairbeRt/JDataTest/master/pictures/xy.svg?sanitize=true) graphic shows a random data set sorted via the xyCompare procedure. Here all points are sorted by their x-coordinates, with sorting by y-coordinate occuring if the x-coordinates collide.

[This](https://raw.githubusercontent.com/hairbeRt/JDataTest/master/pictures/abs.svg?sanitize=true) graphic shows a similar procedure where the points are first sorted by their absolute value and then by geometric argument.

# Future Goals

Implement a data structure that can efficiently perform an approximately-nearest-neighbor-query and visualise the procedures in play.

> What if it's all just Voronoi diagrams all the way down?
