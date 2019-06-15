# JDataTest

This repository is a simple framework for manipulating and plotting 2D data sets.
The goal is to have a set of points in 2D-space, sort them in some chosen order
and then print the resulting chain of points as a vector graphic.

# Example graphics

[This](https://raw.githubusercontent.com/hairbeRt/JDataTest/master/pictures/xy.svg?sanitize=true) graphic shows a random data set sorted via the xyCompare procedure. Here all points are sorted by their x-coordinates, with sorting by y-coordinate occuring if the x-coordinates collide.

[This](https://raw.githubusercontent.com/hairbeRt/JDataTest/master/pictures/abs.svg?sanitize=true) graphic shows a similar procedure where the points are first sorted by their absolute value and then by geometric argument.

[This](https://raw.githubusercontent.com/hairbeRt/JDataTest/master/pictures/hilbert.svg?sanitize=true) Shows the ordering of points in a plane with an uncontinuous space-filling curve. The quadrants are ordered in cyclic order and the points within any quadrants are ordered recursively, where the orientation of the quadrant ordering is left unchanged in the recursive step (unlike in a "real" Hilbert-curve, where the ordering of the quadrant is rotated in the recursive step). The ordering of this uncontinuous curve is visualised in third and fourth order [here](https://raw.githubusercontent.com/hairbeRt/JDataTest/master/pictures/uncontinuous_third_order.svg?sanitize=true) and [here](https://raw.githubusercontent.com/hairbeRt/JDataTest/master/pictures/uncontinuous_fourth_order.svg?sanitize=true).

[This](https://raw.githubusercontent.com/hairbeRt/JDataTest/master/pictures/64_uncontinuous.svg?sanitize=true) Image shows an interesting or annoying (you decide) side-effect of using whole-number-coordinates for the 2D-geometry. While the ordering of the points in the smallest scale should consist of a Z-like ordering, some rows show an N-ordering. This is due to the fact that the sizes of the quadrants (1000/64) are no longer whole numbers, so will be rounded, which "kicks" points on a small scale out of their intuitive quadrant (one could say that the algorithm still runs correctly, just that the realised decomposition of quadrants only turns one quadrant into four _nearly_ equally-sized quadrants). A possible future solution is to use double-precision arithmetic at least for the quadrant-decomposition.

[This](https://raw.githubusercontent.com/hairbeRt/JDataTest/master/pictures/uncontinuousHilbert.svg?sanitize=true) Image shows how the uncontinuous Z-ordering curve looks in eigth order.

[This](https://raw.githubusercontent.com/hairbeRt/JDataTest/master/pictures/uncontinuousHilbert.svg?sanitize=true) Shows the continuous actual Hilbert Curve in fourth order.

# Statistical evaluation

Coming soon. See [here](https://raw.githubusercontent.com/hairbeRt/JDataTest/master/statistical_data/) for collected data.

# Future Goals

Implement a data structure that can efficiently perform an approximately-nearest-neighbor-query and visualise the procedures in play.

> What if it's all just Voronoi diagrams all the way down?
