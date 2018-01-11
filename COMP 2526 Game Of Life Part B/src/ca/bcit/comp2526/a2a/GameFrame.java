package ca.bcit.comp2526.a2a;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * GameFrame.
 * @author  BCIT
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {
    
    /**
     * This is world object.
     */
    private final World world;

    /**
     * This is the constructor of GameFrame.
     * @param w - world 
     */
    public GameFrame(final World w) {
        world = w;
    }

    /**
     * Generating 25x25 grid layout with mouse listerner.
     */
    public void init() {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(),
                                 world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row,
                                    col));
            }
        }

        addMouseListener(new TurnListener(this));
    }

    /**
     * Taking turn method.
     */
    public void takeTurn() {
        world.takeTurn();
        repaint();
    }
}
