/**
 * 
 */
package ca.bcit.comp2526.a2c;

/**
 * CouldNotRemoveException class.
 * @author Vinh Le
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class CouldNotRemoveException extends Exception {
    
    /**
     * CouldNotRemoveException constructor.
     * @param message - error message
     */
    public CouldNotRemoveException(String message) {
        super(message);
    }
}
