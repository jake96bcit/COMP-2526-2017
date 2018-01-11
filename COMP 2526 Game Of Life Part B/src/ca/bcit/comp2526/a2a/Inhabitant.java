package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * The interface to store all inhabitants of a particular universe.
 * All inhabitants MUST extend the JPanel class.
 * 
 * @author Vinh Le
 * @version 1.0
 */
public interface Inhabitant {
    
    /**
     * Initializes the inhabitant in the universe.
     */
    void init();
    
    /**
     * Inhabitant moves forward in time.
     */
    void takeTurn();
    
    /**
     * Removes the Inhabitant from the specified cell.
     */
    void detach();
    
    /**
     * Checking the current cell can be eatten by a herbivore or not. 
     * @return true if the current inhabitant is a plant.
     *          false if the current inhabitant is a Herbivore.
     */
    boolean canBeEattenByHerbivore();
    
    /**
     * Getting the life form color.
     * @return Color
     */
    Color getColor();
    
    /**
     * Resetting the turnTaken.
     */
    void resetTurn();

    /**
     * Checking the current cell can be eatten by a carnivore or not. 
     * @return true if the current inhabitant is a herbivore or omnivore.
     *          false if the current inhabitant is a plant.
     */
    boolean canBeEattenByCarnivore();
    
    /**
     * Checking the current cell can be eatten by an omnivore or not. 
     * @return true if the current inhabitant is a herbivore, 
     *              plant or carnivore.
     *          false if the current inhabitant is a Omnivore.
     */
    boolean canBeEattenByOmnivore();
}
