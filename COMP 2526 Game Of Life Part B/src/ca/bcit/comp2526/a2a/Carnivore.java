package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;
/**
 * This is Carnivore class.
 * This class can eat Herbivore and Omnivore.
 * 
 * @author Vinh Le
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class Carnivore extends Animal implements Inhabitant {
    
    /**
     * Maximum hunger for Carnivore.
     */
    public static final int MAX_HUNGER = 7;

    /**
     * Constructor for objects of type Carnivore.
     * 
     * @param location
     *            the cell to instantiate this Carnivore on
     */
    public Carnivore(Cell location) {
        super(location);
    }

    /**
     * Draws the Carnivore.
     * 
     * @return Pink
     */
    public Color getColor() {
        return Color.PINK;

    }

    /**
     * Carnivore takes its turn.
     */
    public void takeTurn() {
        if (!isTurnTaken()) {
            if (getHunger() == MAX_HUNGER) {
                die();
            } else {
                reproduce();
                move();
            }
            setTurnTaken(true);
        }
    }
    
    /**
     * A Carnivore can give birth if there are: 
     * at least 1 Carnivore neighbours, at
     * least two free neighbouring cell, and 2 neighbouring cells with food
     * (Herbivore and Omnivore). 
     * A Carnivore cannot be born and give birth during the same turn.
     */
    @Override
    public void reproduce() {
        Cell[] neighbors = getCell().getNeighbors();
        ArrayList<Cell> emptyCells = new ArrayList<>();
        int foodCount = 0;
        int carnivoreCount = 0;
        for (int i = 0; i < neighbors.length; i++) {
            Inhabitant inhabitant = neighbors[i].getInhabitant();
            if (inhabitant == null) {
                emptyCells.add(neighbors[i]);
            } else if (inhabitant.canBeEattenByCarnivore()) {
                foodCount++;
            } else if (this.getClass().getName()
                    .equals(inhabitant.getClass().getName())) {
                carnivoreCount++;
            }
        }
        int size = emptyCells.size();
        int randIndex;
        if (carnivoreCount >= 1 && size >= 2 && foodCount >= 2) {
            if (size == 1) {
                randIndex = 0;
            } else {
                randIndex = RandomGenerator.nextNumber(size);
            }
            Cell newCell = emptyCells.get(randIndex);
            Carnivore c = new Carnivore(newCell);
            c.setTurnTaken();
            newCell.addLife(c);
        }
    }

    /**
     * Carnivore moves if space is unoccupied or moves to a Food Cell and eats.
     */
    protected void move() {
        Cell[] cells = getCell().getNeighbors();
        ArrayList<Cell> foodCells = new ArrayList<>();
        ArrayList<Cell> emptyCells = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            Inhabitant inhabitant = cells[i].getInhabitant();
            if (inhabitant == null) {
                emptyCells.add(cells[i]);
            } else if (inhabitant.canBeEattenByCarnivore()) {
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
        return false;
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

