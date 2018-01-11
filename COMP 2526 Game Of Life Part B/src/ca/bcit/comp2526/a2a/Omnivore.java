/**
 * 
 */
package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;

import ca.bcit.comp2526.a2a.RandomGenerator;

/**
 * This is Omnivore class. This life form can eat every kind of life form except
 * another Omnivore.
 * 
 * @author Vinh Le
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class Omnivore extends Animal implements Inhabitant {

    /**
     * Maximum hunger for Omnivore.
     */
    public static final int MAX_HUNGER = 2;

    /**
     * Maximum free call and food that needed for the reproduction of Omnivore.
     */
    public static final int MAX_FREE_OR_FOOD_CELLS = 3;

    /**
     * Constructor for objects of type Omnivore.
     * 
     * @param location
     *            the cell to instantiate this Omnivore on
     */
    public Omnivore(Cell location) {
        super(location);
    }

    /**
     * Draws the Omnivore.
     * 
     * @return Blue
     */
    public Color getColor() {
        return Color.BLUE;

    }

    /**
     * Omnivore takes its turn.
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
     * A Omnivore can give birth if there are: at least 1 Omnivore neighbours, at
     * least three free neighbouring cell, and three neighbouring cells with food
     * (Plant, Herbivore and Carnivore). A Omnivore cannot be born and give birth
     * during the same turn.
     */
    @Override
    public void reproduce() {
        Cell[] neighbors = getCell().getNeighbors();
        ArrayList<Cell> emptyCells = new ArrayList<>();
        int foodCount = 0;
        int omnivoreCount = 0;
        for (int i = 0; i < neighbors.length; i++) {
            Inhabitant inhabitant = neighbors[i].getInhabitant();
            if (inhabitant == null) {
                emptyCells.add(neighbors[i]);
            } else if (inhabitant.getClass().getName().equals("Herbivore")
                    || inhabitant.canBeEattenByHerbivore()
                    || !inhabitant.canBeEattenByCarnivore()) {
                foodCount++;
            } else if (!inhabitant.canBeEattenByOmnivore()) {
                omnivoreCount++;
            }
        }
        int size = emptyCells.size();
        int randIndex;
        if (omnivoreCount > 0 && size > 2 && foodCount > 2) {
            randIndex = RandomGenerator.nextNumber(size);
            Cell newCell = emptyCells.get(randIndex);
            Omnivore o = new Omnivore(newCell);
            o.setTurnTaken();
            newCell.addLife(o);
        }
    }

    /**
     * Omnivore moves if space is unoccupied or moves to a Food Cell and eats.
     */
    protected void move() {
        Cell[] cells = getCell().getNeighbors();
        ArrayList<Cell> foodCells = new ArrayList<>();
        ArrayList<Cell> emptyCells = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            Inhabitant inhabitant = cells[i].getInhabitant();
            if (inhabitant == null) {
                emptyCells.add(cells[i]);
            } else if (!inhabitant.canBeEattenByHerbivore() 
                    || inhabitant.canBeEattenByHerbivore()
                    || !inhabitant.canBeEattenByCarnivore()) {
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
     * Setting the new born turn taken to true so it will not do anything after it
     * born.
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
        return false;
    }
}