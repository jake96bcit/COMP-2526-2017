package ca.bcit.comp2526.a1d;

import javax.swing.JFrame;

/**
 * This is SwingDisplayer class.
 * 
 * @author Vinh Le
 * @version 1.0
 */
public class SwingDisplayer implements Displayer {
    
    /**
     * This is displayTable method.
     * 
     * @param shape - shapeType
     */
    private void displayTable(final Shape shape) {
        final DisplayerFrame frame;
        frame = new DisplayerFrame();
        frame.init(shape);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * This method is used to display the shape.
     */
    @Override
    public void displayShape(Shape shape) {
        shape.storeShape();
        displayTable(shape);
    }
}
