package ca.bcit.comp2526.a1a;

import java.util.Collections;

/**
 * This is a class about Arithmetic Table.
 * 
 * @author Vinh Le
 * @version 1.0
 */
public class ArithmeticTable {

    /**
     * Maximum arguments being passed.
     */
    public static final int MAX_ARGS = 3;

    /**
     * Dash print times.
     */
    public static final int DASH = 7;

    /**
     * Maximum end value of the table.
     */
    public static final int MAX_TABLE_END = 100;

    /**
     * This is the 2D array name table that used to store the arithmetic table.
     */
    private double[][] table;

    /**
     * Start value of the table.
     */
    private int start;

    /**
     * End value of the table.
     */
    private int end;

    /**
     * Type of the table, multiply or add.
     */
    private TableType tableType;

    /**
     * Enum type consists of MULT and ADD.
     * 
     * @author Vinh Le
     *
     */
    public enum TableType {
        /**
         * Addition.
         */
        ADD,
        /**
         * Multiply.
         */
        MULT
    };

    /**
     * This method is using for validating command line arguments.
     * 
     * @param args
     *            - Program arguments - user input through command line
     * @return true - if the arguments passed in are valid 
     *         false - if the arguments
     *         passed in are invalid
     */
    public boolean argumentCheck(String[] args) {
        String format = "%s%n%s%n%s%n%s%n%s%n";
        if (args.length != MAX_ARGS) {
            System.err.printf(format, "Usage:  Main <type> <start> <stop>", 
                    "\tWhere <type> is one of +, \"*\"",
                    "\tand <start> is between 1 and 100", 
                    "\tand <stop> is between 1 and 100", "\tand start < stop");
            return false;
        }
        if (args[0].charAt(0) == '+') {
            tableType = TableType.ADD;
        } else {
            tableType = TableType.MULT;
        }
        int sta;
        int sto;
        try {
            sta = Integer.parseInt(args[1]);
            sto = Integer.parseInt(args[2]);
        } catch (NumberFormatException ex) {
            System.err.printf(format, "Usage:  Main <type> <start> <stop>", 
                    "\tWhere <type> is one of +, \"*\"",
                    "\tand <start> is between 1 and 100",
                    "\tand <stop> is between 1 and 100", "\tand start < stop");
            return false;
        }
        if ((sta < 1 || sta > MAX_TABLE_END) 
                || ((sto < 1 || sto > MAX_TABLE_END))) {
            System.err.printf(format, "Usage:  Main <type> <start> <stop>", 
                    "\tWhere <type> is one of +, \"*\"",
                    "\tand <start> is between 1 and 100", 
                    "\tand <stop> is between 1 and 100", "\tand start < stop");
            return false;
        }
        if (sta >= sto) {
            System.err.printf(format, "Usage:  Main <type> <start> <stop>",
                    "\tWhere <type> is one of +, \"*\"",
                    "\tand <start> is between 1 and 100",
                    "\tand <stop> is between 1 and 100", "\tand start < stop");
            return false;
        }
        start = sta;
        end = sto;
        return true;
    }

    /**
     * Creating table and calculating the table's variables.
     * 
     * @param began
     *            - Start value of table
     * @param finish
     *            - End value of table
     * @param inputTableType
     *            - Type of the table
     */
    public void createTable(int began, int finish, TableType inputTableType) {
        table = new double[finish][finish];
        int result;
        if (inputTableType == TableType.ADD) {
            for (int i = 0; i < finish; i++) {
                for (int j = 0; j < finish; j++) {
                    result = (began + i) + (began + j);
                    table[i][j] = result;
                }
            }
        } else {
            for (int i = 0; i < finish; i++) {
                for (int j = 0; j < finish; j++) {
                    result = (began + i) * (began + j);
                    table[i][j] = result;
                }
            }
        }
    }

    /**
     * This is the method that using for print out the table.
     */
    public void printTable() {
        int index = start;
        int firstIndex = start;
        for (int i = 0; (i + firstIndex - 1) <= end; i++) {
            if (i == 0) {
                if (tableType.equals(TableType.ADD)) {
                    String add = "+";
                    System.out.printf("%5s|  ", add);
                } else {
                    String mult = "*";
                    System.out.printf("%5s|  ", mult);
                }
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

    /**
     * This is the main method that used to run the program.
     * 
     * @param args
     *            - Program arguments
     */
    public static void main(String[] args) {
        ArithmeticTable table = new ArithmeticTable();
        if (table.argumentCheck(args)) {
            table.createTable(table.start, table.end, table.tableType);
            table.printTable();
        }
    }
}
