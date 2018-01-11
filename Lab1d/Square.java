/**
 * 
 */
package ca.bcit.comp2526.a1d;

/**
 * This is Square class.
 * 
 * @author Vinh Le
 * @version 1.0
 */
public class Square extends Rectangle {

    /**
     * This is square constructor.
     * 
     * @param width                 - square width
     * @param height                - square height
     * @throws BadWidthException    - bad width exception
     */
    protected Square(int width, int height) throws BadWidthException {
        super(width, height);
        setType("s");
        setHeight(height);
    }

    /**
     * This is the method that checking the height input of square.
     * 
     * @param height                - must be equal to width
     * @throws BadWidthException    - bad width exception
     */
    public void setHeight(int height) throws BadWidthException {
        if (height != this.getWidth()) {
            throw new BadWidthException();
        } 
        super.setHeight(getWidth());
    }
}
