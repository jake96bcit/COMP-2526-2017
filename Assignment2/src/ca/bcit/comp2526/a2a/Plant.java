package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

/**
 * The Plant, represented as a green Cell.
 * 
 * @author Vinh Le
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Plant extends JPanel implements Inhabitant {
    private Cell cell;

    /**
     * Constructor for objects of type Plant.
     * 
     * @param location
     *            the cell to instantiate this Plant on
     */
    public Plant(Cell location) {
        if (location == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        cell = location;
    }

    /**
     * Initializes the Plant.
     */
    public void init() {
        setCell(cell);
    }

    /**
     * Reserved for future implementation (reproduction growth etc).
     */
    public void takeTurn() {
        //move();
    }
    
    /**
     * Herbivore moves if space is unoccupied or moves to a Plant and eats.
     */
    @SuppressWarnings("unused")
    private void move() {
        Cell[][] cells = cell.getAdjacentCells();
        boolean moved = false;
        int stuck = 0;
        final int ten = 10;
        int counter = 0;

        Point point = direction();
        int y1 = (int) point.getY();
        int x1 = (int) point.getX();
        
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (cells[j][i].getInhabitant() instanceof Herbivore) {
                    counter++;
                }
            }
        }
        
        while (!moved) {
            if (cells[y1][x1] != null) {
                Inhabitant inhabitant = cells[y1][x1].getInhabitant(); 
                if (inhabitant == null || inhabitant instanceof Herbivore) {
                    if (inhabitant instanceof Herbivore && counter >= 2) { 
                        spread(cells[y1][x1]);
                    }
                    removeCell(cell);
                    setCell(cells[y1][x1]);
                    moved = true;
                } 
            //if it is surrounded by impassable objects or cannot 
            //find a valid path after ten tries, give up
            } else if (stuck == ten) {  
                moved = true;
            }
            stuck++;
        }
    }
    
    /**
     * Pick a random cell to check whether plant can grow there or not.
     * 
     * @return cell's position
     */
    private Point direction() {
        int y1 = 1;
        int x1 = 1;
        final int two = 2;
        final int ten = 10;
        final int twenty = 20;
        final int thirty = 30;
        final int forty = 40;
        final int fifty = 50;
        final int sixty = 60;
        final int seventy = 70;
        final int eighty = 80;
        
        /* Map of 2D array for reference in y,x index format
         * 00   01     02
         * 10   CELL   12
         * 20   21     22
         */
        int direction = RandomGenerator.nextNumber(eighty);
        if (direction < ten) { //moves north
            y1 = 0;
            x1 = 1;
        } else if (direction < twenty) { //moves north east
            y1 = 0;
            x1 = two;
        } else if (direction < thirty) { //moves east
            y1 = 1;
            x1 = two;
        } else if (direction < forty) { //moves south east
            y1 = two;
            x1 = two;
        } else if (direction < fifty) { //moves south
            y1 = two;
            x1 = 1;
        } else if (direction < sixty) { //moves south west
            y1 = two;
            x1 = 0;
        } else if (direction < seventy) { //moves west
            y1 = 1;
            x1 = 0;
        } else if (direction < eighty) { //moves north west
            y1 = 0;
            x1 = 0;
        }
        Point point = new Point(x1, y1);
        return point;
    }
    
    /**
     * Spreading plant.
     * 
     * @param currentCell - cell to spread
     */
    public void spread(Cell currentCell) {
        new Plant(cell);
    }

    /**
     * Sets the Plant on the specified cell.
     * 
     * @param cell
     *            the cell to set this Plant on
     */
    public void setCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        this.cell = cell;
        cell.setInhabitant(this);
        cell.add(this);
    }

    /**
     * Removes the Plant from the specified cell.
     * 
     * @param currentCell
     *            the cell to remove this Plant from
     */
    public void removeCell(Cell currentCell) {
        if (currentCell == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        currentCell.removeInhabitant(this);
        currentCell.remove(this);
    }

    /**
     * Draws the Plant.
     * 
     * @param draw
     *            device context for the Panel to draw on
     */
    public void paintComponent(Graphics draw) {
        final int r = 19;
        final int g = 237;
        final int b = 12;
        draw.setColor(new Color(r, g, b));
        draw.fillRect(0, 0, getWidth(), getHeight());
    }
}
