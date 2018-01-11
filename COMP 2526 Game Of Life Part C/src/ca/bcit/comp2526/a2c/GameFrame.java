package ca.bcit.comp2526.a2c;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * GameFrame.
 * 
 * @author Vinh Le
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

    /**
     * This is world object.
     */
    private World world;
    private JPanel panel;
    private JPanel panel1;
    private JButton save;
    private JButton load;

    /**
     * This is the constructor of GameFrame.
     * 
     * @param w
     *            - world
     */
    public GameFrame(final World w) {
        world = w;
        panel = new JPanel();
    }

    /**
     * Generating 25x25 grid layout with mouse listerner.
     */
    public void init() {
        Save saving = new Save();
        Load loading = new Load();

        setTitle("Assignment 2c");
        panel.setLayout(
                new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                panel.add(world.getCellAt(row, col));
            }
        }
        add(panel, BorderLayout.CENTER);
        addMouseListener(new TurnListener(this));
        
        panel1 = new JPanel();
        save = new JButton("Save");
        load = new JButton("Load");
        panel1.add(save);
        panel1.add(load);
        add(panel1, BorderLayout.NORTH);

        save.addActionListener(saving);
        load.addActionListener(loading);
    }
    
    /**
     * Reiniting the game frame when loading saved game.
     */
    public void reinit() {
        setTitle("Assignment 2c");
        panel.setLayout(
                new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                panel.add(world.getCellAt(row, col));
            }
        }
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Taking turn method.
     */
    public void takeTurn() {
        world.takeTurnUsingLinkedList();
        repaint();
    }

    /**
     * Save button inner class which will make save button work.
     * 
     * @author Vinh Le
     * @version 1.0
     */
    public class Save implements ActionListener {
        private JFileChooser fileChooser = 
                new JFileChooser(System.getProperty("user.dir"));

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                fileChooser.setSelectedFile(new File(""));
                int fileSaved = fileChooser.showSaveDialog(GameFrame.this);
                if (fileSaved == JFileChooser.APPROVE_OPTION) {
                    File savedGame = fileChooser.getSelectedFile();
                    FileOutputStream outputFile = 
                            new FileOutputStream(savedGame);
                    ObjectOutputStream outputObject = 
                            new ObjectOutputStream(outputFile);
                    outputObject.writeObject(world);
                    outputObject.close();
                    outputFile.close();
                }
            } catch (IOException exception) {
                exception.getMessage();
            }
        }
    }

    /**
     * This is the inner class which using for loading saved games.
     * @author Vinh Le
     * @version 1.0
     * 
     */
    public class Load implements ActionListener {
        private JFileChooser fileChooser;
        private World loadedWorld;

        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser = new JFileChooser(System.getProperty("user.dir"));
            try {
                int fileOpened = fileChooser.showOpenDialog(GameFrame.this);

                if (fileOpened == JFileChooser.APPROVE_OPTION) {
                    FileInputStream fileLoaded = 
                            new FileInputStream(fileChooser.getSelectedFile());
                    ObjectInputStream inputObject = 
                            new ObjectInputStream(fileLoaded);
                    loadedWorld = (World) inputObject.readObject();
                    inputObject.close();
                    fileLoaded.close();
                }
            } catch (IOException exception) {
                exception.getMessage();
            } catch (ClassNotFoundException exception) {
                exception.getMessage();
            }

            world = loadedWorld;
            panel.removeAll();
            reinit();
            world.reinit();
            GameFrame.this.repaint();
        }
    }
}
