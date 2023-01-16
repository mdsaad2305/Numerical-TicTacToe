package mainfile;

import numericalttt.NumericView;
import numericalttt.NumericalGame;
import tictactoe.TictactoeView;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

/**
 * This class is responsible for maintaining the
 * main frame of the GUI
 * */
public class GameUI extends JFrame {
    private JPanel chooseGamePanel = new JPanel();
    private JPanel gamePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    public GameUI(){
        super(); // calling superclass constructor
        setTitle("TicTacToe games"); // title of the frame
        setLayout(new BorderLayout()); // setting the layout to border layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate program when GUI is closed
        setJMenuBar(makeCharacterMenuBar()); // adding menubar
        add(chooseGamePanel, BorderLayout.NORTH); // adding panel of buttons
        makeChooseGamePanel();
        add(gamePanel, BorderLayout.CENTER); // adding game panel
        setSize(700,500); // setting size of frame
        pack(); // packing all the contents of frame to proper size
    }

    private void makeChooseGamePanel() {
        chooseGamePanel.setLayout(new BoxLayout(chooseGamePanel, BoxLayout.X_AXIS));
        JLabel chooseGameText = new JLabel("Select a game you would like to play!");
        chooseGamePanel.add(chooseGameText);
        chooseGamePanel.add(makeTictactoeButton());
        chooseGamePanel.add(makeNumericButton());
    }

    private JButton makeTictactoeButton(){
        JButton gameButton = new JButton("Original TicTacToe");
        gameButton.addActionListener(e->tictactoe());
        return gameButton;
    }

    private JButton makeNumericButton(){
        JButton gameButton = new JButton("Numerical TicTacToe");
        gameButton.addActionListener(e->numeric()); // adding an action listener for numeric game
        return gameButton;
    }

    private void tictactoe(){
        gamePanel.removeAll();
        gamePanel.add(new TictactoeView(this)); // for playing tictactoe
        repaint();
        revalidate();
        pack();
    }

    private void numeric(){
        gamePanel.removeAll();
        gamePanel.add(new NumericView(this)); // for playing numeric tictactoe
        repaint();
        revalidate();
        pack();
    }


    /**
     *this method restarts the game
     * */
    public void restartGames(){
        gamePanel.removeAll();
        repaint();
        revalidate();
        pack();
    }

    private JMenuBar makeCharacterMenuBar(){
        JMenuBar characterMenu = new JMenuBar(); // making a menu bar
        JMenu character = new JMenu("Profile");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");

        character.add(saveItem); // adding item to menu
        character.add(loadItem); // adding item to menu
        characterMenu.add(character); // adding menu to menubar

        return characterMenu;
    }

    public static void main(String[] args){
        GameUI exampleUI = new GameUI();
        exampleUI.setVisible(true);
    }


}
