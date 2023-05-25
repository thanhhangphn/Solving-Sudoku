# Solving-Sudoku

This project uses node based stack and 2D array to write a stack-based solver for the Sudoku game that implements a depth-first search algorithm. Using the solver, I explored how the number of starting values provided affects the complexity of the search for a solution.

The result is a graphic display version of the Sudoku game which chooses a predetermined number of random starting values, displays the solver’s solving
process, and shows its final condition onto the screen.

## Final Result Showcase

![CS231 (1)](https://github.com/thanhhangphn/Solving-Sudoku/assets/119096071/7056b181-414c-4239-95e7-041035edc939)
![CS231](https://github.com/thanhhangphn/Solving-Sudoku/assets/119096071/20ea6601-dee8-4b1a-8219-0d67def2c424)


## Description

Using this implementation of the Sudoku solver, I can explore the relationship between the number of randomly selected (but valid) initial values and the likelihood of finding a solution for the board as following:

When the number of initial values is as low as 10, all boards have a solution. However, the more initial
values there are, the more constraints there are, and the less possible solutions there
are. Thus, as the number of initial values rises higher around 20, the board is less
likely to have a solution (3-5 out of 5 cases). As the number of initial values
reaches more than 30, it is likely that there is no solution to the board, which makes
sense because the initial values are chosen randomly. The same happens when the
number of initial values reaches 40. Thus, the number of initial values and the
number of boards that have the solution seems to be in an decreasing relation.

## Sources/Credits
Colby College's CS231 (Data Structures and Algorithms)'s resources
