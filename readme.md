# Game of Life

John Horton Conway’s Game of Life is no ordinary game, but a mathematical system of two-dimensionally arranged cellular automata. It is the best known example of cellular automata. Conway first published the game in Scientific American 223 in October 1970:

- Task is to implement the game Game Of Life that is interactively playable on a shell.
- Use the given template and add all missing functionality.

## Game of Life Rules

The game board is divided into rows and columns. On each square is a cell, which can be either alive or dead. Each cell has 8 neighbors with which it interacts. After each time step (‘mutation’), the state of the cell changes as follows:

- Living cells with less than two neighbors die of loneliness in the following generation.
- Living cells with more than three neighbors die of overpopulation in the subsequent generation.
- Living cells with exactly two or three living neighbors remain alive in the subsequent generation.
- Dead cells with exactly three living neighbors are reborn in the subsequent generation.

In the beginning, the playing field is populated with an initial population. Afterwards, the rules mentioned above are applied simultaneously to all cells for each step in time. This results in complex patterns that remain either stable, oscillate, grow or shrink depending on their shape. Some even wander across the playing field, so-called *spaceships*.

Theoretically, the playing field is infinitely large. However, since memory is limited, a limited playing field is assumed right from the start. Cells outside the  playing field are considered dead.
