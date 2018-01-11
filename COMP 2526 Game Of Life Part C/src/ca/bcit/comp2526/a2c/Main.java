package ca.bcit.comp2526.a2c;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Main.
 * @author BCIT
 * @version 1.0
 */
public final class Main {
    
    /**
     * Magicnumber 0.80f.
     */
    public static final float AB = 0.80f;
    
    /**
     * Magicnumber 100.0f.
     */
    public static final float AC = 100.0f;
    
    /**
     * Toolkit.
     */
    private static final Toolkit TOOLKIT;

    /**
     * Initialize TOOLKIT.
     */
    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * Main's empty constructor.
     */
    private Main() {
    }

    /**
     * Main method.
     * 
     * @param argv - program arguments
     */
    public static void main(final String[] argv) {
        final GameFrame frame;
        final World world;

        RandomGenerator.reset();
        world = new World(World.MAX_ROW_COL, World.MAX_ROW_COL);
        world.init();
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Position method.
     * @param frame - frame
     */
    private static void position(final GameFrame frame) {
        final Dimension size;

        size = calculateScreenArea(AB, AB);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }

    /**
     *  centreOnScreen method.
     * @param size - Dimesion size
     * @return Point
     */ 
    public static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) 
                / 2, (screenSize.height - size.height) / 2));
    }

    /**
     * Calculate screen area method.
     * @param widthPercent - width percent
     * @param heightPercent - height percent
     * @return area
     */
    public static Dimension calculateScreenArea(final float widthPercent,
            final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;

        if ((widthPercent <= 0.0f) || (widthPercent > AC)) {
            throw new IllegalArgumentException("widthPercent cannot be " 
        + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > AC)) {
            throw new IllegalArgumentException("heightPercent cannot be " 
        + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }
}
