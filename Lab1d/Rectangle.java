package ca.bcit.comp2526.a1d;

/**
 * This is Rectangle class.
 * 
 * @author Vinh Le
 * @version 1.0
 */
public class Rectangle extends Shape {
    
    /**
     * This is the 2D array that used to store shape (Rectangle).
     */
    private String[][] cells;
    
    /**
     * This is the constructor of Rectangle.
     * 
     * @param width     - Rectangle's width
     * @param height    - Rectangle's height
     * @throws BadWidthException 
     */
    protected Rectangle(int width, int height) throws BadWidthException {
        super(width, height, "r");
    }
    
    /**
     * This method is printing a rectangle.
     */
    public void printShape() {
        for (int i = 0; i < getHeight(); i++) {
            System.out.printf("%s", "*");
            for (int j = 1; j < getWidth(); j++) {
                System.out.printf("%s", "*");
            }
            System.out.println("");
        }
    }
    
    /**
     * This method is used to store the shape in the 2D array.
     */
    public void storeShape() {
        cells = new String[getHeight()][getWidth()];
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                cells[i][j] = "*";
            }
        } 
    }
    
    /**
     * T.
     * 
     * @param i - s
     * @param j - s
     * @return Data
     */
    public String getData(int i, int j) {
        return cells[i][j];
    }
}
