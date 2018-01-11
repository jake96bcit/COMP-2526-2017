package ca.bcit.comp2526.a1d;

/**
 * This is the class that will print out the shape in the console.
 * 
 * @author Vinh Le
 * @version 1.0
 */
public class ConsoleDisplayer implements Displayer {
    
    /**
     * This is displayTable method.
     * 
     * @param shape - shapeType
     */
    private void displayTable(final Shape shape) {
        System.out.println("console");
        shape.printShape();
    }

    /**
     * This is the method that is used to display the shape.
     */
    @Override
    public void displayShape(Shape shape) {
        displayTable(shape);
    }
}
