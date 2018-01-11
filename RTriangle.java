/**
 * 
 */
package ca.bcit.comp2526.a1d;

/**
 * This is Right Triangle class.
 * 
 * @author Vinh Le
 * @version 1.0
 */
public class RTriangle extends Triangle {
    
    /**
     * This is the 2D array that will be used to store the shape.
     */
    private String[][] cells;

    /**
     * This is Right Triangle constructor.
     * 
     * @param width                 - Right triangle width
     * @param height                - Right triangle height (will be ignored)
     * @throws BadWidthException    - Bad width exception
     */
    protected RTriangle(int width, int height) throws BadWidthException {
        super(width, height);
        
        //Ignore user input height, set a new height for triangle.
        setHeight((width / 2) + 1);
    }


    
    /**
     * This method is using to check does the width is an odd number or not.
     * 
     * @param newWidth - user input width
     * @throws BadWidthException - Bad width exception
     */
    public void setWidth(int newWidth) throws BadWidthException {
        if (newWidth % 2 == 0) {
            throw new BadWidthException();
        }
        super.setWidth(newWidth);
    }

    /**
     * This is the Triangle print method.
     */
    public void printShape() {
        int delta = getWidth() - getHeight();
        for (int i = 0; i < getHeight(); i++) {
            int numOfChar = 2 * i + 1;
            int numOfSpace = delta - i;
            for (int j = 0; j < numOfSpace; j++) {
                System.out.print("");
            }
            for (int k = 0; k < numOfChar; k++) {
                System.out.printf("%s", "@");
            }
            System.out.println("");
        }
    }
    
    @Override
    public void storeShape() {
        cells = new String[getHeight()][getWidth()];
        int i;
        int j;
        System.out.println(getHeight() + " " + getWidth());
        for (i = 0; i < getHeight(); i++) {
            for (j = 0; j < getWidth(); j++) {
                cells[i][j] = " ";
            }
        }
        for (i = 0; i < getHeight(); i++) {
            int numOfChar = 2 * i + 1;
            for (int k = 0; k < numOfChar; k++) {
                cells[i][(k)] = "@";
            }
        }
    }

    @Override
    public String getData(int i, int j) {
        return cells[i][j];
    }
}
