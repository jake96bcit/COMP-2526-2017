package ca.bcit.comp2526.a1d;

/**
 * This is Diamond class.
 * 
 * @author Vinh Le
 * @version 1.0
 */
public class Diamond extends Shape {

    /**
     * This is the 2D array that used to store shape (Rectangle).
     */
    private String[][] cells;

    /**
     * This is the Diamond constructor.
     * 
     * @param width
     *            - Diamond's width
     * @param height
     *            - Diamond's height
     * @throws BadWidthException - Bad width exception
     */
    protected Diamond(int width, int height) throws BadWidthException {
        super(width, height, "d");
        this.setWidth(width);
        // Ignore user input height, set a new height for diamond.
        setHeight(width);
    }

    /**
     * This method is using to check does the width is an odd number or not.
     * 
     * @param newWidth
     *            - user input width
     * @throws BadWidthException
     *             - Bad width exception
     */
    public void setWidth(int newWidth) throws BadWidthException {
        if ((newWidth % 2) == 0) {
            throw new BadWidthException();
        }
        super.setWidth(newWidth);
    }

    /**
     * This method is printing out the diamond shape.
     */
    public void printShape() {
        for (int i = 0; i < getHeight(); i++) {
            int numOfChar;
            int numOfSpace;
            if (i < (getHeight() / 2 + 1)) {
                numOfChar = 2 * i + 1;
                numOfSpace = (getHeight() / 2) - i;
            } else {
                numOfChar = 2 * (getWidth() - i) - 1;
                numOfSpace = i - (getHeight() / 2);
            }
            for (int j = 0; j < numOfSpace; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < numOfChar; k++) {
                System.out.printf("%s", "#");
            }
            System.out.println("");
        }
    }

    /**
     * This is the method that is used to store a Diamond into a 2D array.
     */
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
            int numOfChar;
            int numOfSpace;
            if (i < (getHeight() / 2 + 1)) {
                numOfChar = 2 * i + 1;
                numOfSpace = (getHeight() / 2) - i;
            } else {
                numOfChar = 2 * (getWidth() - i) - 1;
                numOfSpace = i - (getHeight() / 2);
            }
            for (int k = 0; k < numOfChar; k++) {
                cells[i][(k + numOfSpace)] = "#";
            }
        }
    }

    /**
     * This is the method that will return 
     * the value of the cell at row i and column j in the 2D array.
     * 
     * @return cells[i][j]
     */
    @Override
    public String getData(int i, int j) {
        return cells[i][j];
    }
}
