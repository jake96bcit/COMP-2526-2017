package ca.bcit.comp2526.a2a;

import java.util.ArrayList;

/**
 * This is class World.
 * 
 * @author Vinh Le
 * @version 1.0
 * 
 */
public class World {
    
    /**
     * Variables for magic number error.
     */
    public static final int HUNDRED = 100;
    
    /**
     * Variables for magic number error.
     */
    public static final int EIGHTY = 100;
    
    /**
     * Variables for magic number error.
     */
    public static final int FIFTY = 100;

    /**
     * This is 2D array of type Cell.
     */
    private Cell[][] cells;

    /**
     * This is World's row variable.
     */
    private int rows;

    /**
     * This is World's column variable.
     */
    private int cols;

    /**
     * This is World Constructor.
     * 
     * @param row - World's row.
     * @param col - World's column.
     * 
     */
    public World(int row, int col) {
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative or zero");
        }
        
        this.rows = row;
        this.cols = col;
        cells = new Cell[rows][cols];
    }

    /**
     * This is init method which will generate a 
     * Cell object in World 25x25 Demesion.
     */
    public void init() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(this, i, j);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].init();
            }
        }
    }

    /**
     * This is the method which deciding which one are moving
     * (Plat or Herbivore).
     */
    public void takeTurn() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].takeTurn();
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].resetTurn();
            }
        }
        spawn();
    }

    /**
     * This method is using for generating 
     * a plant or a hebivore using RandomGenerator. 
     */
    private void spawn() {
        Cell cell;
        int val =  RandomGenerator.nextNumber(HUNDRED);
        if (val >= EIGHTY) {
            cell = getRandomEmptyCell();

            if (cell != null) {
                Herbivore herbivore = new Herbivore(cell);
                herbivore.init();
                herbivore.revalidate();
            }
        } else if (val >= FIFTY) {
            cell = getRandomEmptyCell();

            if (cell != null) {
                Plant plant = new Plant(cell);
                plant.init();
                plant.revalidate();
            }
        }
    }

    /**
     * Getting a cell randomly.
     * 
     * @return cell
     */
    private Cell getRandomEmptyCell() {
        ArrayList<Cell> cellList = getAllEmptyCells();
        Cell cell;
        int seed;

        if (cellList.size() == 0) {
            return null;
        } else {
            seed = RandomGenerator.nextNumber(cellList.size() - 1);
            cell = cellList.get(seed);
            return cell;
        }
    }

    /**
     * This method will loop through the cells 2D array 
     * and add all the empty cell to cellList.
     * @return cellList
     *  - List of empty cells
     */
    private ArrayList<Cell> getAllEmptyCells() {
        ArrayList<Cell> cellList = new ArrayList<Cell>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells[i][j].getInhabitant() == null) {
                    cellList.add(cells[i][j]);
                }
            }
        }
        return cellList;
    }

    /**
     * Returns all adjacent Cells in a 2D array, null = no Cell. 
     * Cell[1][1] is always null as it is its own Cell.
     * 
     * @param cell
     *            the Cell to get adjacent Cells from
     * @return the 2D Cell array of adjacent cells.
     */
    public Cell[][] getAdjacentCells(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        final int three = 3;
        int x1 = (int) cell.getLocation().getX();
        int y1 = (int) cell.getLocation().getY();
        Cell[][] adjCell = new Cell[three][three];

        checkCornerCells(y1, x1, adjCell);
        checkSideCells(y1, x1, adjCell);
        checkOtherCells(y1, x1, adjCell);
        return adjCell;
    }

    /**
     * Support method for getAdjacentCells, 
     * checks and stores the Cells adjacent to
     * the corner Cells.
     * 
     * @param row
     *            the y position of the Cell to check
     * @param col
     *            the x position of the Cell to check
     * @param cell
     *            the 3x3 2D array cell to store with
     */
    private void checkCornerCells(int row, int col, Cell[][] cell) {
        final int two = 2;
        final int three = 3;

        if (row < 0 || col < 0 || cell.length != three 
                || cell[two].length != three) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative and cell " 
                            + "array passed must be size [3][3]");
        }

        /*
         * Map of 2D array for reference in y,x 
         * index format 00 01 02 10 CELL 12 20 21
         * 22
         */
        if (row == 0 && col == 0) { // Top left
            cell[1][two] = cells[row][col + 1];
            cell[two][two] = cells[row + 1][col + 1];
            cell[two][1] = cells[row + 1][col];
        } else if (row == 0 && col == cols - 1) { // Top right
            cell[1][0] = cells[row][col - 1];
            cell[two][0] = cells[row + 1][col - 1];
            cell[two][1] = cells[row + 1][col];
        } else if (row == rows - 1 && col == 0) { // Bottom left
            cell[0][1] = cells[row - 1][col];
            cell[0][two] = cells[row - 1][col + 1];
            cell[1][two] = cells[row][col + 1];
        } else if (row == rows - 1 && col == cols - 1) { // Bottom right
            cell[0][1] = cells[row - 1][col];
            cell[0][0] = cells[row - 1][col - 1];
            cell[1][0] = cells[row][col - 1];
        }
    }

    /**
     * Support method for getAdjacentCells, 
     * checks and stores the Cells adjacent to
     * the side Cells.
     * 
     * @param row
     *            the y position of the Cell to check
     * @param col
     *            the x position of the Cell to check
     * @param cell
     *            the 3x3 2D array cell to store with
     */
    private void checkSideCells(int row, int col, Cell[][] cell) {
        final int two = 2;
        final int three = 3;

        if (row < 0 || col < 0 || cell.length != three 
                || cell[two].length != three) {

            throw new IllegalArgumentException(
                    "Parameters cannot be negative and cell " 
                            + "array passed must be size [3][3]");
        }

        if (row == 0 && (col > 0 && col < cols - 1)) { // Top side
            cell[1][two] = cells[row][col + 1];
            cell[two][two] = cells[row + 1][col + 1];
            cell[two][1] = cells[row + 1][col];
            cell[two][0] = cells[row + 1][col - 1];
            cell[1][0] = cells[row][col - 1];
        } else if ((row > 0 && row < rows - 1) 
                && col == cols - 1) { // Right side
            cell[0][1] = cells[row - 1][col];
            cell[two][1] = cells[row + 1][col];
            cell[two][0] = cells[row + 1][col - 1];
            cell[1][0] = cells[row][col - 1];
            cell[0][0] = cells[row - 1][col - 1];
        } else if (row == rows - 1 
                && (col > 0 && col < cols - 1)) { // Bottom side
            cell[0][1] = cells[row - 1][col];
            cell[0][two] = cells[row - 1][col + 1];
            cell[1][two] = cells[row][col + 1];
            cell[1][0] = cells[row][col - 1];
            cell[0][0] = cells[row - 1][col - 1];
        } else if ((row > 0 && row < rows - 1) && col == 0) { // Left side
            cell[0][1] = cells[row - 1][col];
            cell[0][two] = cells[row - 1][col + 1];
            cell[1][two] = cells[row][col + 1];
            cell[two][two] = cells[row + 1][col + 1];
            cell[two][1] = cells[row + 1][col];
        }
    }

    /**
     * Support method for getAdjacentCells, 
     * checks and stores the Cells adjacent to
     * the Cells.
     * 
     * @param row
     *            the y position of the Cell to check
     * @param col
     *            the x position of the Cell to check
     * @param cell
     *            the 3x3 2D array cell to store with
     */
    private void checkOtherCells(int row, int col, Cell[][] cell) {
        final int two = 2;
        final int three = 3;

        if (row < 0 || col < 0 || cell.length != three 
                || cell[two].length != three) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative and cell " 
            + "array passed must be of size [3][3]");
        }

        if ((row > 0 && row < rows - 1) && (col > 0 && col < cols - 1)) {
            cell[0][1] = cells[row - 1][col];
            cell[0][two] = cells[row - 1][col + 1];
            cell[1][two] = cells[row][col + 1];
            cell[two][two] = cells[row + 1][col + 1];
            cell[two][1] = cells[row + 1][col];
            cell[two][0] = cells[row + 1][col - 1];
            cell[1][0] = cells[row][col - 1];
            cell[0][0] = cells[row - 1][col - 1];
        }
    }

    /**
     * Getting the number of rows.
     * 
     * @return rows - the number of rows
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * Getting the number of columns.
     * 
     * @return cols - the number of columns
     */
    public int getColumnCount() {
        return cols;
    }

    /**
     * Getting the cell at row and column.
     * 
     * @param row - Cell's row
     * @param col - Cell's column
     * 
     * @return cells[row][col]
     *  - cell at row and col
     */
    public Cell getCellAt(int row, int col) {
        return cells[row][col];
    }
}
