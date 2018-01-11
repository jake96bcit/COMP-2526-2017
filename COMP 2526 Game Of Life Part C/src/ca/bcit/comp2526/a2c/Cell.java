package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The Cell. Represents a square space within a World. 
 * Can hold objects thats exists in the World. 
 * Represented by a brown Cell.
 * 
 * @author Vinh Le
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Cell extends JPanel implements Serializable {
    
    private final int row;
    private final int col;
    private final World world;
    private Inhabitant inhabitant;
    
    /**
     * Constructor for objects of type Cell. 
     * (6a)
     * 
     * @param world the World this Cell is placed on
     * @param row the row position of the Cell
     * @param col the column position of the Cell
     */
    public Cell(World world, int row, int col) {
        if (row < 0 || col < 0 || world == null) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative or null");
        }
        
        this.world = world;
        this.row = row;
        this.col = col;
    }
    
    /**
     * Initializes the Cell and places the inhabitant on the Cell randomly 
     * using the RandomGenerator class. Stores adjacent Cells from World.
     * (6b)
     */
    public void init() {
        setLayout(new GridLayout(1, 1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inhabitant != null) {
            //System.out.println(this.printMe() + inhabitant.getColor());
            setBackground(inhabitant.getColor());
        } else {
            setBackground(Color.WHITE);
        }
    }
    
    /**
     * Advances time within the Cell by one turn.
     */
    public void takeTurn() {
        if (inhabitant != null) {
            inhabitant.takeTurn();
        }
    }
    
    /**
     * Sets the Inhabitant to this Cell.
     * @param object the Inhabitant to set
     */
    public void setInhabitant(Inhabitant object) {
        if (inhabitant == null) {
            inhabitant = object;
        }   
    }
    
    /**
     * Removes the Inhabitant of this Cell.
     */
    public void removeInhabitant() {
        if (inhabitant != null) {
            //this.remove(0);
            inhabitant = null;
        }
    }
    
    /**
     * Returns the current Inhabitant of the Cell.
     * @return the Inhabitant of the Cell
     */
    public Inhabitant getInhabitant() {
        return inhabitant;
    }
    
    /**
     * Returns the location of the cell as a Pointer object.
     * 6c
     * @return the Point object of the location of the cell.
     */
    public Point getLocation() {
        Point location = new Point(col, row);
        return (location);
    }
    
    /**
     * Returns all adjacent cells in a 2D array, null = no Cell. 
     * Cell[1][1] is always null as it is its own Cell.
     * 6d
     * @return the 2D Cell array of adjacent cells.
     */
    public Cell[] getNeighbors() {
        int r = this.row;
        int c = this.col;
        
        if (isCorner()) {
            return getCornerAdjacentCells();
        } else if (isSide()) {
            return getSideAdjacentCells();
        }
        return new Cell[] {
                world.getCellAt(r - 1, c),
                world.getCellAt(r - 1, c + 1),
                world.getCellAt(r, c + 1),
                world.getCellAt(r + 1, c + 1),
                world.getCellAt(r + 1, c),
                world.getCellAt(r - 1, c - 1),
                world.getCellAt(r + 1, c - 1),
                world.getCellAt(r, c - 1)
         };
    }
    
    /**
     * Checking the current cell is a corner cell or not.
     * @return true if yes
     *          false if not
     */
    private boolean isCorner() {
        return (col == 0 && row == 0) 
                || (col == 0 && row == World.MAX_ROW_COL - 1) 
                || (col == World.MAX_ROW_COL - 1 && row == 0) 
                || (col == World.MAX_ROW_COL - 1 
                    && row == World.MAX_ROW_COL - 1); 
    }
    
    /**
     * Checking the current cell is a side cell or not.
     * @return true if yes
     *          false if not
     */
    private boolean isSide() {
        return (col == 0 && (row > 0 && row < World.MAX_ROW_COL - 1)) 
                || (row == 0 && (col > 0 && col < World.MAX_ROW_COL - 1))
                || (col == World.MAX_ROW_COL - 1
                    && (row > 0 && row < World.MAX_ROW_COL - 1)) 
                || (row == World.MAX_ROW_COL - 1 
                    && (col > 0 && col < World.MAX_ROW_COL - 1));
    }
    
    /**
     * Getting the array that hold the adjacent cells of corner cell.
     * @return array of cell
     */
    private Cell[] getCornerAdjacentCells() {
        int r = this.row;
        int c = this.col;
        //System.out.println(r + " conner " + c);
        if (col == 0 && row == 0) { // top left
            return new Cell[] {
                world.getCellAt(r, c + 1), 
                world.getCellAt(r + 1, c), 
                world.getCellAt(r + 1, c + 1)
            };
        } else if (col == 0 && row == World.MAX_ROW_COL - 1) { // bottom left
            return new Cell[] {
                world.getCellAt(r - 1, c),
                world.getCellAt(r - 1, c + 1),
                world.getCellAt(r, c + 1)
            };
        } else if (col == World.MAX_ROW_COL - 1 && row == 0) { // top right
            return new Cell[] {
                world.getCellAt(r + 1, c),
                world.getCellAt(r + 1, c - 1),
                world.getCellAt(r, c - 1)
            };
        } 
        // bottom right
        return new Cell[] {
                world.getCellAt(r, c - 1),
                world.getCellAt(r - 1, c - 1),
                world.getCellAt(r - 1, c)
            };
    }
    
    /**
     * Getting the array that hold the adjacent cells of side cell.
     * @return array of cell
     */
    private Cell[] getSideAdjacentCells() {
        int r = this.row;
        int c = this.col;
        //System.out.println(r + " " + c);
        if (r == 0 && (c > 0 && c < World.MAX_ROW_COL - 1)) { // top side
            return new Cell[] {
                world.getCellAt(r, c + 1),
                world.getCellAt(r + 1, c + 1),
                world.getCellAt(r + 1, c),
                world.getCellAt(r + 1, c - 1),
                world.getCellAt(r, c - 1)
            };
        }  else if (c == 0 && (r > 0 
                && r < World.MAX_ROW_COL - 1)) { // left side
            return new Cell[] {
                world.getCellAt(r - 1, c),
                world.getCellAt(r - 1, c + 1),
                world.getCellAt(r, c + 1),
                world.getCellAt(r + 1, c + 1),
                world.getCellAt(r + 1, c)
            };
        }  else if (c == World.MAX_ROW_COL - 1 
                && (r > 0 && r < World.MAX_ROW_COL - 1)) { // right side
            return new Cell[] {
                world.getCellAt(r + 1, c),
                world.getCellAt(r + 1, c - 1),
                world.getCellAt(r, c - 1),
                world.getCellAt(r - 1, c - 1),
                world.getCellAt(r - 1, c),
            };
        }  
        return new Cell[] {
                world.getCellAt(r, c - 1),
                world.getCellAt(r - 1, c - 1),
                world.getCellAt(r - 1, c),
                world.getCellAt(r - 1, c + 1),
                world.getCellAt(r, c + 1)
            };
    }
    
    /**
     * Resets the Inhabitants' turn so that 
     * they are ready to take the next turn.
     */
    public void resetTurn() {
        if (inhabitant != null) {
            inhabitant.resetTurn();
        }
    }

    /**
     * Creates the Inhabitants into the World based on random probability.
     * 
     * @param inputInhabitant 
     */
    protected void addLife(Inhabitant inputInhabitant) {
        this.inhabitant = inputInhabitant;
        this.inhabitant.init();
    }
}
