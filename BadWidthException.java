package ca.bcit.comp2526.a1d;

/**
 * BadWidthException.
 *
 * @author BCIT
 * @version 2017
 */
@SuppressWarnings("serial")
public class BadWidthException extends Exception {

    /**
     * Creates an object of type BadWidthException.
     */
    public BadWidthException() {
    }

    /**
     * Creates an object of type BadWidthException.
     * @param message the message stored in the exception
     */
    public BadWidthException(String message) {
        super(message);
    }

    /**
     * Creates an object of type BadWidthException.
     * @param cause the cause of the exception
     */
    public BadWidthException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
