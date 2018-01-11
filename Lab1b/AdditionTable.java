package ca.bcit.comp2526.a1b;

import java.util.Collections;

/**
 * This is a class about Addition Table.
 * 
 * @author Vinh Le
 * @version 1.0
 */
public class AdditionTable {

    /**
     * Maximum end value of the table.
     */
    public static final int MAX_TABLE_END = 100;

    /**
     * Dash print times.
     */
    public static final int DASH = 7;
    
    /**
     * Table start value.
     */
    private int start;

    /**
     * Table end value.
     */
    private int end;

    /**
     * This is the 2D array name table that used to store the arithmetic table.
     */
    private double[][] table;

    /**
     * This is the constructor of AdditionTable.
     * 
     * @param sta
     *            - Start value
     * @param sto
     *            - End value
     */
    public AdditionTable(int sta, int sto) {
        String format = "%s%n%s%n%s%n%s%n%s%n";
        try {
            start = sta;
            end = sto;
        } catch (NumberFormatException ex) {
            System.err.printf(format, "Usage:  Main <type> <start> <stop>",
                    "\tWhere <type> is one of +, \"*\"",
                    "\tand <start> is between 1 and 100",
                    "\tand <stop> is between 1 and 100", "\tand start < stop");
        }
        if ((sta < 1 || sta > MAX_TABLE_END) 
                || ((sto < 1 || sto > MAX_TABLE_END))) {
            System.err.printf(format, "Usage:  Main <type> <start> <stop>",
                    "\tWhere <type> is one of +, \"*\"",
                    "\tand <start> is between 1 and 100",
                    "\tand <stop> is between 1 and 100", "\tand start < stop");
        }
        if (sta >= sto) {
            System.err.printf(format, "Usage:  Main <type> <start> <stop>",
                    "\tWhere <type> is one of +, \"*\"",
                    "\tand <start> is between 1 and 100",
                    "\tand <stop> is between 1 and 100", "\tand start < stop");
        }
        start = sta;
        end = sto;
        createTable();
    }

    /**
     * This method is using to create, 
     * calculate and store value into 2D array table.
     */
    private void createTable() {
        table = new double[end][end];
        int result;

        for (int i = 0; i < end; i++) {
            for (int j = 0; j < end; j++) {
                result = (start) + (start + j);
                table[i][j] = result;
            }
        }
    }
    
    /**
     * This method is using to print out the table.
     */
    public void display() {
        int index = start;
        int firstIndex = start;
        for (int i = 0; (i + firstIndex - 1) <= end; i++) {
            if (i == 0) {
                    String add = "+";
                    System.out.printf("%5s|  ", add);
            } else if (i != 0 && index <= end) {
                System.out.printf("%5d|  ", index);
                index++;
            }
            for (int j = 0; (j + firstIndex) <= end; j++) {
                if (i == 0 && start <= end) {
                    System.out.printf("%5d  ", start);
                    start++;
                } else if (i != 0) {
                    System.out.printf("%5.0f  ", table[(i - 1)][j]);

                }
            }
            System.out.println("");
            if (i == 0) {
                System.out.println(String.join("", 
                        Collections.nCopies(end * DASH, "-")));
            }
        }
    } 
}
