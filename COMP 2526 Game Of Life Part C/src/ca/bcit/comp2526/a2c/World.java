package ca.bcit.comp2526.a2c;

import java.io.Serializable;
import java.util.Iterator;

/**
 * The World built with Cells, does not wrap around the edges.
 * It is flat but nothing falls off the edge either.
 * 
 * @author Vinh Le
 * @version 1.0
 */
@SuppressWarnings("serial")
public class World implements Serializable {
    
    /**
     * Maximum value of row and column.
     */
    public static final int MAX_ROW_COL = 25;
    
    /**
     * Variables for magic number error.
     */
    public static final int MAX_RANDOM_NUMBER = 100;
    
    /**
     * Herbivore lower bound for chance to be spawned.
     */
    public static final int HERBIVORE_LOWER_BOUND = 80;
    
    /**
     * Plant lower bound for chance to be spawned.
     */
    public static final int PLANT_LOWER_BOUND = 50;

    /**
     * Carnivore lower bound for chance to be spawned.
     */
    public static final int CARNIVORE_LOWER_BOUND = 40;
    
    /**
     * Omnivore lower bound for chance to be spawned.
     */
    public static final int OMNIVORE_LOWER_BOUND = 32;
    
    private final int rows;
    private final int cols;
    private Cell[][] map;
    
    
    /**
     * Constructor for objects of type World.
     * 7a
     * 
     * @param rows the amount of rows of Cells of the World
     * @param cols the amount of columns of Cells of the World
     */
    public World(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative or zero");
        }
        this.rows = rows;
        this.cols = cols;
        map = new Cell[rows][cols];
    }
    
    /**
     * Instantiates the Cells on the World then initializes them.
     * 7b
     */
    public void init() {
        RandomGenerator.reset();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col] = new Cell(this, row, col);
                int val = RandomGenerator.nextNumber(MAX_RANDOM_NUMBER);
                if (val >= HERBIVORE_LOWER_BOUND) {
                        map[row][col].addLife(new Herbivore(map[row][col]));
                    
                } else if (val >= PLANT_LOWER_BOUND) {
                        map[row][col].addLife(new Plant(map[row][col]));
                } else if (val >= CARNIVORE_LOWER_BOUND) {
                    map[row][col].addLife(new Carnivore(map[row][col]));
                } else if (val >= OMNIVORE_LOWER_BOUND) {
                    map[row][col].addLife(new Omnivore(map[row][col]));
                }
                map[row][col].init();
            }
        }
    }
    
    /**
     * Advances time within the World by one turn.
     * Calls the spawn method.
     */
    public void takeTurn() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col].takeTurn();
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col].resetTurn();
                map[row][col].repaint();
            }
        }
    }
    
    /**
     * Advances time within the World by one turn.
     * Calls the spawn method.
     */
    public void takeTurnUsingLinkedList() {
        DoubleLinkedList<Cell> cells = new DoubleLinkedList<Cell>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                try {
                    cells.addToEnd(map[row][col]);
                } catch (CouldNotAddException e) {
                    e.getMessage();
                }
            }
        }
        
        // Creating cells iterator
        Iterator<Cell> it = cells.iterator();
        
        while (it.hasNext()) {
            Cell cell = it.next();
            if (cell.getInhabitant() != null) {
                cell.takeTurn();
            }
        }
        
        // Resetting iterator
        it = cells.iterator();
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col].resetTurn();
                map[row][col].repaint();
            }
        }
    }
    
    /**
     * Returns the number of rows in this world.
     * @return number of rows in this world.
     */
    public int getRowCount() {
        return rows;
    }
    
    /**
     * Returns the number of columns in this world.
     * @return number of columns in this world.
     */
    public int getColumnCount() {
        return cols;
    }
    
    /**
     * Returns the Cell with the location specified.
     * 7c
     * @param row the row of the Cell
     * @param col the column of the Cell
     * @return the Cell at that location.
     */
    public Cell getCellAt(int row, int col) {
        if (row < 0  || col < 0 || row > this.rows || col > this.cols) {
            //System.out.print(col + " " + cols + " . " + row + " " + rows);
            throw new IllegalArgumentException(
                    "Row must be in [0, " + this.rows 
                    + "], Column must be in [0, " + this.cols + "].");
        }
        return map[row][col];
    }
    
    /**
     * Reinitializes the world following being loaded from a save state.
     */
    public void reinit() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (map[i][j].getInhabitant() != null) {
                    map[i][j].getInhabitant().init();
                }
                map[i][j].init(); 
            }
        } 
    }
}
