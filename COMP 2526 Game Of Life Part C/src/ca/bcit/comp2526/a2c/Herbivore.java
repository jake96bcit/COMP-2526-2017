package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Herbivore, represented as a yellow Cell.
 * 
 * @author Vinh Le
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Herbivore extends Animal implements Inhabitant, Serializable {

    /**
     * Constructor for objects of type Herbivore.
     * 
     * @param location
     *            the cell to instantiate this Herbivore on
     */
    public Herbivore(Cell location) {
        super(location);
    }

    /**
     * Draws the Herbivore.
     * 
     * @return Yellow
     */
    public Color getColor() {
        return Color.YELLOW;

    }

    /**
     * Herbivore takes its turn.
     */
    public void takeTurn() {
        if (!isTurnTaken()) {
            if (getHunger() == MAX_IDLE) {
                die();
            } else {
                reproduce();
                move();
            }
            setTurnTaken(true);
        }
    }
    
    /**
     * An Herbivore can give birth if there are: 
     * at least 2 Herbivore neighbours, at
     * least one free neighbouring cell, and 2 neighbouring cells with food
     * (Plants). 
     * An Herbivore cannot be born and give birth during the same turn.
     */
    @Override
    public void reproduce() {
        Cell[] neighbors = getCell().getNeighbors();
        ArrayList<Cell> emptyCells = new ArrayList<>();
        int herbivoreCount = 0;
        int plantCount = 0;
        for (int i = 0; i < neighbors.length; i++) {
            Inhabitant inhabitant = neighbors[i].getInhabitant();
            if (inhabitant == null) {
                emptyCells.add(neighbors[i]);
            } else if (inhabitant.canBeEattenByHerbivore()) {
                plantCount++;
            } else if (this.getClass().getName()
                    .equals(inhabitant.getClass().getName())) {
                herbivoreCount++;
            }
        }
        int size = emptyCells.size();
        int randIndex;
        if (herbivoreCount >= 2 && size >= 1 && plantCount >= 2) {
            if (size == 1) {
                randIndex = 0;
            } else {
                randIndex = RandomGenerator.nextNumber(size);
            }
            Cell newCell = emptyCells.get(randIndex);
            Herbivore h = new Herbivore(newCell);
            h.setTurnTaken();
            newCell.addLife(h);
        }
    }

    /**
     * Herbivore moves if space is unoccupied or moves to a Food Cell and eats.
     */
    protected void move() {
        Cell[] cells = getCell().getNeighbors();
        ArrayList<Cell> foodCells = new ArrayList<>();
        ArrayList<Cell> emptyCells = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            Inhabitant inhabitant = cells[i].getInhabitant();
            if (inhabitant == null) {
                emptyCells.add(cells[i]);
            } else if (inhabitant.canBeEattenByHerbivore()) {
                foodCells.add(cells[i]);
            }
        }
        int randIndex;
        int foodSize = foodCells.size();
        int emptySize = emptyCells.size();

        if (foodSize > 0) {
            if (foodSize == 1) {
                randIndex = 0;
            } else {
                randIndex = RandomGenerator.nextNumber(foodSize - 1);
            }
            Cell foodCell = foodCells.get(randIndex);
            eat(foodCell);
        } else if (emptySize > 0) {
            if (emptySize == 1) {
                randIndex = 0;
            } else {
                randIndex = RandomGenerator.nextNumber(emptySize - 1);
            }
            moveToEmpty(emptyCells.get(randIndex));
            setHunger(getHunger() + 1);
        } else {
            setHunger(getHunger() + 1);
        }
    }
    
    /**
     * Setting the new born turn taken to 
     * true so it will not do anything after it born.
     */
    private void setTurnTaken() {
        setTurnTaken(true);
    }

    @Override
    public boolean canBeEattenByCarnivore() {
        return true;
    }

    @Override
    public boolean canBeEattenByHerbivore() {
        return false;
    }

    @Override
    public boolean canBeEattenByOmnivore() {
        return true;
    }
}
