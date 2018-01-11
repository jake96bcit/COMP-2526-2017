package ca.bcit.comp2526.a1d;

/**
 * This is the base class Shape.
 * 
 * @author Vinh Le  
 * @version 1.0
 */
public abstract class Shape {
    
    /**
     * This is Shape's width.
     */
    private int width;
    
    /**
     * This is Shape's height.
     */
    private int height;

    /**
     * This is Shape's type.
     */
    private String type;

    /**
     * This is Shape constructor.
     * 
     * @param width     - Width of the shape
     * @param height    - Height of the shape
     * @param type      - type is r(rectangle), t(triangle), or d(diamond)
     * @throws BadWidthException - Bad width exception
     */
    protected Shape(final int width, final int height, final String type) 
            throws BadWidthException {
        setType(type);
        setWidth(width);
        setHeight(height);
    }
    
    /**
     * This is width setter.
     * 
     * @param width - the width to set
     * @throws BadWidthException 
     */
    public void setWidth(int width) throws BadWidthException {
        if (width > 0) {
            this.width = width;
        } else {
            throw new BadWidthException("");
        }
    }

    /**
     * This is height setter.
     * 
     * @param height - the height to set
     * @throws BadWidthException 
     */
    public void setHeight(int height) throws BadWidthException {
        if (height > 0) {
            this.height = height;
        } else {
            throw new BadWidthException("");
        }
    }

    /**
     * This is type setter.
     * @param type - the type to set
     * @throws BadWidthException 
     */
    public void setType(String type) throws BadWidthException {
        if (type != null && !type.equals("")) {
            this.type = type;
        } else {
            throw new BadWidthException("");
        }

    }
    
    /**
     * This method is returning the width of shape.
     * 
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * This method is returning the height of shape.
     * 
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method is returning the type of shape.
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }
    
    /**
     * This is printing method that print out shape.
     */
    public abstract void printShape();
    
    /**
     * This is storing method that print out shape.
     */
    public abstract void storeShape();
    
    /**
     * This is the method that will return 
     * the value of the cell at row i and column j in the 2D array.
     * 
     * @param i - row number
     * @param j - column number
     * @return the value of cell[i][j]
     */
    public abstract String getData(int i, int j);
}
