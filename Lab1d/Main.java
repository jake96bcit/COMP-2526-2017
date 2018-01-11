package ca.bcit.comp2526.a1d;

import java.util.Map;
import java.util.HashMap;

/**
 * Displays the specified shape with the specified width/height values in the
 * specified manner (console or JFrame).
 * 
 * @author Vinh Le
 * @version 1.0
 */
public final class Main {
    /**
     * The smallest value to width/height.
     */
    private static final int MIN_VALUE = 1;

    /**
     * The largest value to width/height.
     */
    private static final int MAX_VALUE = 100;

    /**
     * Disallow the creation of any Main objects.
     */
    private Main() {
    }

    /**
     * The entry point to the program.
     *
     * @param argv
     *            the command line args. argv[0] - the type (r,t,d,s,Rt) argv[1]
     *            - the width value (> 1 && < 100) argv[2] - the height value (>
     *            1 && < 100) argv[3] - the displayer type (ConsoleDisplayer,
     *            SwingDisplayer)
     */
    public static void main(final String[] argv) {
        final int expectedArgs = 4;
        final int typeArg = 0;
        final int widthArg = 1;
        final int heightArg = 2;
        final int displayerArg = 3;

        final ShapeType type;
        final Shape shape;
        final Displayer displayer;
        final int width;
        final int height;

        if (argv.length != expectedArgs) {
            usage("Wrong number of arguments");
        }
        
        type = getType(argv[typeArg]);
        width = getNumber(argv[widthArg]);
        height = getNumber(argv[heightArg]);
        
        shape = getShape(type, width, height);
        //shape.printShape();
        displayer = getDisplayer(argv[displayerArg]);
        displayer.displayShape(shape);
        
        // get the type, width and height of the shape
        // the get the shape to display
        // get the displayer to be used (console or gui)
        // then display the shape with that displayer
        // note the "is-a" action allowing all shapes to be a shape
        // and all displayers to be a displayer
        // note then the polymorphic action of the displayer
        // and the drawing of the shape

    }

    /**
     * Creates the desired shape given the type, width and height.
     * 
     * @param type
     *            defines the type of shape to create width gives the width to
     *            be used - certain shapes must have odd width size height gives
     *            the height to be used - not used for all shapes
     * @param width - Shape's width
     * @param height - Shape's height
     * @return shape desired shape or NULL if unable to create
     **/
    public static Shape getShape(final ShapeType type, final int width,
            final int height) {
        try {
            switch (type) {
            case RECTANGLE:
                return new Rectangle(width, height);
            case TRIANGLE:
                return new Triangle(width, height);
            case DIAMOND:
                return new Diamond(width, height);
            case RTRIANGLE:
                return new RTriangle(width, height);
            case SQUARE:
                return new Square(width, height);
            default:
                return null;
            }
        } catch (BadWidthException b) {
            usage("Bad Width");
            return null;
        }
    }

    /**
     * Determines the desired enum type of shape from the string provided.
     * 
     * @param str
     *            a single letter defining the shape
     * @return type an enum type representing the shape
     **/
    public static ShapeType getType(final String str) {
        final Map<String, ShapeType> shapeMap;
        final ShapeType type;
        // COMP 2526 NOTE: much smaller than an if/else or
        // switch :-)
        shapeMap = new HashMap<String, ShapeType>();
        shapeMap.put("r", ShapeType.RECTANGLE);
        shapeMap.put("t", ShapeType.TRIANGLE);
        shapeMap.put("d", ShapeType.DIAMOND);
        shapeMap.put("s", ShapeType.SQUARE);
        shapeMap.put("Rt", ShapeType.RTRIANGLE);

        type = shapeMap.get(str);

        if (type == null) {
            usage("No such shape: " + str);
        }

        return (type);
    }

    /**
     * Converts the supplied string into an int. If the string is not a valid
     * int then exit the program. To be valid the string must be an integer and
     * be > MIN_VALUE and < MAX_VALUE.
     *
     * @param str
     *            the string to convert
     * @return the converted number
     */
    public static int getNumber(final String str) {
        int val;

        try {
            val = Integer.parseInt(str);

            if (val < MIN_VALUE || val > MAX_VALUE) {
                usage("Value out of range: " + val);
            }
        } catch (NumberFormatException ex) {
            usage("Not a number: " + str);
            val = 0;
        }

        return (val);
    }

    /**
     * Creates the appropriate displayer.
     *
     * @param name
     *            the class name of the displayer to create
     * @return the newly created displayer
     */
    public static Displayer getDisplayer(final String name) {
        // COMP 2526 NOTE: This is part of "reflection"
        // What we are doing is converting the string
        // into an instance of a class at runtime!
        try {
            return ((Displayer) Class.forName(name).newInstance());
        } catch (final ClassNotFoundException ex) {
            usage("Cannot find class: " + name);
        } catch (final InstantiationException ex) {
            System.err.println("Error creating: " + name);
            System.exit(1);
        } catch (final IllegalAccessException ex) {
            System.err.println(name + " must have a public,"
                    + " no-arg, constructor");
            System.exit(1);
        }

        return (null);
    }

    /**
     * Display the usage message and exit the program.
     * 
     * @param msg - warning messages
     */
    public static void usage(final String msg) {
        System.err.println(msg);
        System.err.println("Usage: Main <type> <start> <stop> <displayer>");
        System.err.println("\tWhere <type> is one of: r, t, d, s, Rt");
        System.err.println("\tand <width> is between 1 and 100");
        System.err.println("\tand <height> is between 1 and 100");
        System.err.println("\tand <displayer> is one of:"
                + " ConsoleDisplayer, SwingDisplayer");
        System.err.println("\tTriangle and Diamond must "
                + "have uneven width, height not used");
        System.err.println("\tSquare only uses width");
        System.exit(1);
    }
}
