package numericalttt;

import boardgame.BoardGame;
import boardgame.Grid;
import boardgame.ui.PositionAwareButton;
import mainfile.GameUI;
import saveload.SaveLoad;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

public class NumericView extends JPanel{

    private GameUI mainGame;
    private PositionAwareButton[][] gameButtons;
    private JLabel gameStartMessage;
    private NumericalGame visualGame;
    private Grid visualGrid;
    private int depth;

    /**
     * constructor for NumericView
     * @param gameWindow the frame of the GUI
     * */
    public NumericView(GameUI gameWindow){
        super(); // calling superclass constructor
        visualGame = new NumericalGame(3,3);
        visualGrid = visualGame.stringGrid();
        visualGame.setPlayer('O'); // starting player is O
        depth = 0;
        setLayout(new BorderLayout()); // setting border layout
        mainGame = gameWindow;
        gameStartMessage = new JLabel("Hello Player, Let's play Numerical TicTacToe!");
        add(gameStartMessage, BorderLayout.NORTH); // message on the top
        add(tictactoeButtons(3,3), BorderLayout.CENTER); // game in the center
        add(saveLoadPanel(), BorderLayout.SOUTH); // saving and loading panel in the bottom

    }

    private JPanel tictactoeButtons(int tall, int wide){
        JPanel panel = new JPanel();
        gameButtons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(wide, tall));
        for (int y=0; y<wide; y++){
            for (int x=0; x<tall; x++){ //  setting the coordinates
                gameButtons[y][x] = new PositionAwareButton("0");
                gameButtons[y][x].setAcross(x+1);
                gameButtons[y][x].setDown(y+1);
                gameButtons[y][x].addActionListener(e->{
                    buttonLabel(e); // ask for user input and write text on button
                    setPlayerTurn(e); // change player
                    checkWinner(e); // check win conditions
                });
                panel.add(gameButtons[y][x]);
            }
        }
        return panel;
    }

    private void buttonLabel(ActionEvent e){

        String numericInput = getInput(); // getting user input
        PositionAwareButton clickedButton = (PositionAwareButton) e.getSource(); // typecasting button
        clickedButton.setText(numericInput); // putting button text
        visualGame.takeTurn(clickedButton.getAcross(), clickedButton.getDown(),
                                                    numericInput);
        depth += 1;
        visualGame.setDepth(depth);
        clickedButton.setEnabled(false); // not allow user to press same button again
    }
    private String getInput(){ // method for getting and validating user input
        String numericInput = JOptionPane.showInputDialog("Enter a number Player "
                + visualGame.getPlayer());
        if(visualGame.getPlayer() == 'O'){
            if(Integer.parseInt(numericInput) != 1 && Integer.parseInt(numericInput) != 3
                    && Integer.parseInt(numericInput) != 5 && Integer.parseInt(numericInput) != 7
                    && Integer.parseInt(numericInput) != 9){
                while(Integer.parseInt(numericInput) != 1 && Integer.parseInt(numericInput) != 3
                        && Integer.parseInt(numericInput) != 5 && Integer.parseInt(numericInput) != 7
                        && Integer.parseInt(numericInput) != 9) {
                    numericInput = JOptionPane.showInputDialog("Enter a valid input Player "
                            + visualGame.getPlayer());
                }
            }
        }
        if(visualGame.getPlayer() == 'E'){
            if(Integer.parseInt(numericInput) != 2 && Integer.parseInt(numericInput) != 4
                    && Integer.parseInt(numericInput) != 6 && Integer.parseInt(numericInput) != 8){
                while(Integer.parseInt(numericInput) != 2 && Integer.parseInt(numericInput) != 4
                        && Integer.parseInt(numericInput) != 6 && Integer.parseInt(numericInput) != 8) {
                    numericInput = JOptionPane.showInputDialog("Enter a valid input Player "
                            + visualGame.getPlayer());
                }
            }
        }
        return numericInput;
    }

    private void setPlayerTurn(ActionEvent e){ // change player turn
        visualGame.setTurn();
    }

    /* checks win conditions and also reinitializes the game if the player so chooses*/
    private void checkWinner(ActionEvent e){

        JOptionPane gamePane = new JOptionPane();
        int response = 0;

        if(visualGame.getWinner() != -1) {
            visualGame.setTurn();
            response = gamePane.showConfirmDialog(null, visualGame.getGameStateMessage(),
                                            "New Game?", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){
                visualGrid = visualGame.stringGrid();
                visualGrid.emptyGrid();
                visualGame.setPlayer('O');
                depth = 0;
                visualGame.setDepth(depth);
                for(int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        gameButtons[i][j].setText("0");
                        gameButtons[i][j].setEnabled(true);
                    }
                }
            } else {
                mainGame.restartGames(); // restart the game
            }
        }
    }

    /*adds a panel for saving and loading files*/
    private JPanel saveLoadPanel(){
        JPanel saveLoad = new JPanel();
        saveLoad.setLayout(new BoxLayout(saveLoad, BoxLayout.Y_AXIS));
        JLabel saveLable = new JLabel("Would you like to save/load a game?");
        saveLoad.add(saveLable);
        saveLoad.add(makeSaveButton());
        saveLoad.add(makeLoadButton());

        return saveLoad;
    }

    private JButton makeSaveButton(){
        JButton saveButton = new JButton("Save Game");
        saveButton.addActionListener(e->saveGame());
        return saveButton;
    }

    private void saveGame(){
        JOptionPane savePane = new JOptionPane();
        String input;
        SaveLoad saveLoadFile = new SaveLoad();

        input = savePane.showInputDialog("type the name of the file in which you want to save");
        saveLoadFile.saveToFile(input, visualGame);
    }

    /*button not used as loading not implemented*/
    private JButton makeLoadButton(){
        JButton loadButton = new JButton("Load Game");
        return loadButton;
    }

}
