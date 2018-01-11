package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;

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
    private boolean turnTaken;

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
        turnTaken = false;
    }

    /**
     * Initializes the Plant.
     */
    public void init() {
        setOpaque(false);
    }
    
    /**
     * Setting the turnTaken.
     */
    public void setTurnTaken() {
        turnTaken = true;
    }

    /**
     * Reserved for future implementation (reproduction growth etc).
     */
    public void takeTurn() {
        if (!turnTaken) {
            spread();
        }
    }

    /**
     * Plants spreads if there are at lest 2 plants next to it 
     * and there is empty space.
     */
    private void spread() {
        Cell[] cells = cell.getNeighbors();
        Inhabitant inhabitant;
        ArrayList<Cell> emptyCells = new ArrayList<>();
        int plantCount = 0;
        // Pre-screening to classify the adjacent cells
        for (int i = 0; i < cells.length; i++) {
            inhabitant = cells[i].getInhabitant();
            if (inhabitant == null) {
                emptyCells.add(cells[i]);
            } else if (inhabitant.canBeEattenByHerbivore()) {
                plantCount++;
            }
        }
        int emptyCellSize = emptyCells.size();
        int randIndex;
        if (plantCount >= 2 && emptyCellSize > 2) {
            if (emptyCellSize == 1) {
                randIndex = 0;
            } else {
                randIndex = RandomGenerator.nextNumber(emptyCellSize - 1);
            }
            Cell chosenCell = emptyCells.get(randIndex);
            Plant plant = new Plant(chosenCell);
            plant.setTurnTaken();
            chosenCell.addLife(plant);
            chosenCell.init();
        }

    }

    /**
     * This method always return true because the plant is always be eattan by a
     * Herbivore.
     * 
     * @return true
     */
    public boolean canBeEattenByHerbivore() {
        return true;
    }

    /**
     * Draws the Plant.
     * 
     * @return Plant Color
     */
    public Color getColor() {
        return Color.GREEN;
    }

    /**
     * Reseting the turntaken of plants.
     */
    public void resetTurn() {
        turnTaken = false;
    }


    @Override
    public void detach() {
        this.cell.removeInhabitant();
    }

    @Override
    public boolean canBeEattenByCarnivore() {
        return false;
    }

    @Override
    public boolean canBeEattenByOmnivore() {
        return true;
    }

}
