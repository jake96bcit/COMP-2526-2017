package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.JPanel;

/**
 * This is Animal class, which is the parent class of Herbivore, Carnivore, and
 * Omnivore.
 * 
 * @author Vinh Le
 * @version 1.0
 */
@SuppressWarnings("serial")
public abstract class Animal extends JPanel 
    implements Inhabitant, Serializable {

    /**
     * Maximum hunger number (For Herbivore only).
     */
    static final int MAX_IDLE = 10;

    /**
     * Cell object.
     */
    private Cell cell;
    
    /**
     * Hunger of life form.
     */
    private int hunger;
    
    /**
     * Turn taken of life form.
     */
    private boolean turnTaken;

    /**
     * Constructor for objects of type Herbivore.
     * 
     * @param location
     *            the cell to instantiate this Herbivore on
     */
    public Animal(Cell location) {
        if (location == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        setCell(location);
        setHunger(1);
        setTurnTaken(false);
    }

    /**
     * Initializes the Herbivore.
     */
    public void init() {
        setOpaque(false);
        attach(getCell());
    }

    /**
     * Animal takes its turn.
     */
    public abstract void takeTurn();

    /**
     * Animal is ready to take the next turn.
     */
    public void resetTurn() {
        setTurnTaken(false);
    }

    /**
     * This method always return false because a Herbivore can't eat another
     * Herbivore.
     * 
     * @return false
     */
    public abstract boolean canBeEattenByHerbivore();
    
    /**
     * This method return false if the animal is an Carnivore
     * , and true for every other lifeforms (Except Plants).
     * 
     * @return false - if it's a Carnivore
     *          true - for Omnivore, Herbivore
     */
    public abstract boolean canBeEattenByCarnivore();
    
    /**
     * This method return false if the animal is an Omnivore
     * , and true for every other lifeforms.
     * 
     * @return false - if it's a Omnivore
     *          true - for Carnivore, Herbivore or Plant
     */
    public abstract boolean canBeEattenByOmnivore();

    /**
     * Reproducing method which will born more life form if
     * it matched the life form requierments.
     */
    public abstract void reproduce();

    /**
     * Animal moves if space is unoccupied or moves to a Food Cell and eats.
     */
    protected abstract void move();

    /**
     * Animal eats the Food when on the same cell as the Food. Resets its
     * hunger back to 0(full stomach).
     * 
     * @param newCell
     *            the cell with the plant to eat
     */
    protected void eat(Cell newCell) {
        newCell.removeInhabitant();
        setHunger(0);

        // before jump to cell2, save oldCell
        Cell oldCell = this.getCell();
        attach(newCell);

        // make sure oldCell remove reference to inhabitant
        oldCell.removeInhabitant();
    }

    /**
     * Animal can't find Food so it move to a randome cell and
     * increase the hunger by 1.
     * 
     * @param newCell
     *            the cell destination
     */
    protected void moveToEmpty(Cell newCell) {
        // before jump to newCell, save oldCell
        Cell oldCell = this.getCell();
        attach(newCell);

        // make sure oldCell remove reference to inhabitant
        oldCell.removeInhabitant();
        // oldCell.repaint();
    }

    /**
     * Animal dies.
     */
    protected void die() {
        detach();
    }

    /**
     * Attach to new cell.
     * @param newCell - newCell 
     */
    public void attach(Cell newCell) {
        this.setCell(newCell);
        this.getCell().setInhabitant(this);
    }

    @Override
    public void detach() {
        // before remove reference, save the oldCell
        Cell oldCell = this.getCell();
        oldCell.removeInhabitant();
        // this.cell.remove(this);
        // oldCell.repaint();
    }

    /**
     * Get color of an animal.
     * 
     * @return null - animal do not have any color
     */
    public Color getColor() {
        return null;
    }

    /**
     * turnTaken accessor method.
     * 
     * @return true or false
     */
    public boolean isTurnTaken() {
        return turnTaken;
    }

    /**
     * turnTaken setter method.
     * 
     * @param turnTaken - turnTaken
     */
    public void setTurnTaken(boolean turnTaken) {
        this.turnTaken = turnTaken;
    }

    /**
     * Hunger's accessor method.
     * @return the animal hunger
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Hunger setter method.
     * @param hunger the hunger to set
     */
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    /**
     * Cell accessor method.
     * @return the cell
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * Cell setter method.
     * @param cell the cell to set
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
