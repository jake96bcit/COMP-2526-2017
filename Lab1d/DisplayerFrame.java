package ca.bcit.comp2526.a1d;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

//import ca.bcit.comp2526.a1d.solution.Shape;

/**
 * This is DisplayerFrame class.
 * 
 * @author Vinh Le
 * @version 1.0
 */
@SuppressWarnings("serial")
public class DisplayerFrame extends JFrame {
    
    /**
     * This is JButton object.
     */
    private JButton button;

    /**
     * This is the method that will display the shape in GridLayout.
     * 
     * @param paramShape - Object shape type
     */
    public void init(final Shape paramShape) {
        // code to create a grid layout with buttons that will display the
        // shapes
        setLayout(new GridLayout(paramShape.getHeight(),
                paramShape.getWidth()));
        for (int i = 0; i < paramShape.getHeight(); i++) {
            for (int j = 0; j < paramShape.getWidth(); j++) {
                String cell = paramShape.getData(i, j);
                button = new JButton(cell);
                add(button);
            }
        }
       
    }
}
