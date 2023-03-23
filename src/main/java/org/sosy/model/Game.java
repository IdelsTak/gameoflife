package org.sosy.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO: Add JavaDoc and implement the Grid-interface accordingly
 */
/**
 * The Game of Life (an example of a cellular automaton ) is played on an
 * infinite two-dimensional rectangular grid of cells. Each {@link Cell} can be
 * either alive or dead. The status of each cell changes each turn of the game
 * (also called a generation) depending on the statuses of that cell's
 * neighbors.
 */
public class Game implements Grid {

    // Staying alive in this range
    private static final int STAY_ALIVE_MIN_NEIGHBORS = 2;
    private static final int STAY_ALIVE_MAX_NEIGHBORS = 3;

    // Condition for getting newly born
    private static final int NEWBORN_NEIGHBORS = 3;

    // TODO: add instance variables
    private final Set<Cell> cells;
    private int cols;
    private int rows;
    private int generations;

    /**
     * TODO: add JavaDoc
     */
    /**
     * Creates a new {@code Game} instance with specified number of columns and
     * rows.
     *
     * @param cols
     * @param rows
     */
    public Game(int cols, int rows) {
        if (cols <= 0 || rows <= 0) {
            throw new IllegalArgumentException("Number of columns and rows must be positive");
        }

        this.cols = cols;
        this.rows = rows;

        cells = new HashSet<>();
        generations = 0;
    }

    @Override
    public boolean isCellAlive(int col, int row) {
        if (!(col < cols) || !(row < rows)) {
            throw new IllegalArgumentException("Parameters for column and row may not exceed the maximum number of columns and rows");
        } else if (col < 0 || row < 0) {
            throw new IllegalArgumentException("Number of column and row may not be negative");
        }

        return cells.contains(new Cell(col, row));
    }

    @Override
    public void setCellAlive(int col, int row) {
        if (!(col < cols) || !(row < rows)) {
            throw new IllegalArgumentException("Parameters for column and row may not exceed the maximum number of columns and rows");
        } else if (col < 0 || row < 0) {
            throw new IllegalArgumentException("Number of column and row may not be negative");
        }

        cells.add(new Cell(col, row));
    }

    @Override
    public void setCellDead(int col, int row) {
        if (!(col < cols) || !(row < rows)) {
            throw new IllegalArgumentException("Parameters for column and row may not exceed the maximum number of columns and rows");
        } else if (col < 0 || row < 0) {
            throw new IllegalArgumentException("Number of column and row may not be negative");
        }

        cells.remove(new Cell(col, row));
    }

    @Override
    public void resize(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;

        Set<Cell> temp = cells.stream().filter(c -> c.getColumn() < cols && c.getRow() < rows).collect(Collectors.toSet());

        cells.clear();

        cells.addAll(temp);
    }

    @Override
    public int getColumns() {
        return cols;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public Collection<Cell> getPopulation() {
        return null;
    }

    @Override
    public void clear() {
        cells.clear();
    }

    @Override
    public void next() {
        boolean[][] m = getMatrix();

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                int n = countNeighbours(i, j, m);
                if (m[i][j]) {
                    if (n < STAY_ALIVE_MIN_NEIGHBORS) {
                        setCellDead(i, j);
                    } else if (n > STAY_ALIVE_MAX_NEIGHBORS) {
                        setCellDead(i, j);
                    }
                } else {
                    if (n == NEWBORN_NEIGHBORS) {
                        setCellAlive(i, j);
                    }
                }
            }
        }

        generations++;
    }

    @Override
    public int getGenerations() {
        return generations;
    }

    private boolean[][] getMatrix() {
        boolean[][] m = new boolean[cols][rows];

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                m[x][y] = isCellAlive(x, y);
            }
        }

        return m;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean[][] m = getMatrix();

        for (boolean[] rows : m) {
            sb.append("==");
        }
        
        sb.append("\n");
        
        for (boolean[] columns : m) {
            for (int i = 0; i < columns.length; i++) {
                sb.append(columns[i] ? "+" : "-").append(" ");
            }
            sb.append("\n");
        }
        
        for (boolean[] rows : m) {
            sb.append("==");
        }
        
        sb.append("\n");

        return sb.toString();
    }

    // TODO: implement the methods from the Grid-interface
    // You may add further private and public methods as needed.
    // The methods provided in the interface are however sufficient.
    /**
     *
     * @param i
     * @param j
     * @param m
     * @return
     */
    private int countNeighbours(int i, int j, boolean[][] m) {
        int count = 0;
        int x;
        int y;

        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                x = k;
                y = l;
                if (x == -1) {
                    x = m.length - 1;
                }
                if (y == -1) {
                    y = m.length - 1;
                }
                if (x == m.length) {
                    x = 0;
                }
                if (y == m.length) {
                    y = 0;
                }
                if ((m[x][y]) && !(x == i && y == j)) {
                    count++;
                }
            }
        }

        return count;
    }

}
