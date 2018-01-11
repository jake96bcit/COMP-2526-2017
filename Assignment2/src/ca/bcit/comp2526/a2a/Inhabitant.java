package ca.bcit.comp2526.a2a;

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
     * Sets the Inhabitant on the specified cell.
     * @param cell the cell to set this Inhabitant on
     */
    void setCell(Cell cell);
    
    /**
     * Removes the Inhabitant from the specified cell.
     * @param cell the cell to remove this Inhabitant from
     */
    void removeCell(Cell cell);
}
