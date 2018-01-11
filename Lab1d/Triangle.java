package ca.bcit.comp2526.a1d;

/**
 * This is the triangle class.
 * 
 * @author Vinh Le
 * @version 1.0
 */
public class Triangle extends Shape {
    
    private String[][] cells;

    /**
     * This is the Triangle constructor.
     * 
     * @param width     - Triangle's width
     * @param height    - Triablge's height
     * @throws BadWidthException 
     */
    protected Triangle(int width, int height) throws BadWidthException {
        super(width, height, "t");
        
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
                System.out.print(" ");
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
        int delta = getWidth() - getHeight();
        for (i = 0; i < getHeight(); i++) {
            int numOfChar = 2 * i + 1;
            int numOfSpace = delta - i;
            for (int k = 0; k < numOfChar; k++) {
                cells[i][(k + numOfSpace)] = "@";
            }
        }
    }

    @Override
    public String getData(int i, int j) {
        return cells[i][j];
    }
}
