package tictactoe;

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

/**
 * class responsible for playing the originl tictactoe
 */
public class TictactoeView extends JPanel {

    private GameUI mainGame;
    private PositionAwareButton[][] gameButtons;
    private JLabel gameStartMessage;
    private Tictactoegame visualGame;

    private Grid visualGrid;
    private int depth;

    /**
     *
     * @param gameWindow the frame of the GUI
     */
    public TictactoeView(GameUI gameWindow) {

        super();
        visualGame = new Tictactoegame(3,3);
        visualGrid = visualGame.stringGrid();
        visualGame.setPlayer("X");
        depth = 0;
        visualGame.setGameDepth(depth);
        setLayout(new BorderLayout());
        mainGame = gameWindow;
        gameStartMessage = new JLabel("Hello Player, Let's Play TicTacToe!");
        add(gameStartMessage, BorderLayout.NORTH);
        add(tictactoeButtons(3,3), BorderLayout.CENTER);
        add(saveLoadPanel(), BorderLayout.SOUTH);

    }

    private JPanel tictactoeButtons(int tall, int wide){
        JPanel panel = new JPanel();
        gameButtons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(wide, tall));
        for (int y=0; y<wide; y++){
            for (int x=0; x<tall; x++){ // assigning coordinates to the buttons
                gameButtons[y][x] = new PositionAwareButton(" ");
                gameButtons[y][x].setAcross(x+1);
                gameButtons[y][x].setDown(y+1);
                gameButtons[y][x].addActionListener(e->{
                    buttonLabel(e);
                    setPlayerTurn(e);
                    checkWinner(e);
                });
                panel.add(gameButtons[y][x]);
            }
        }
        return panel;
    }


    private void buttonLabel(ActionEvent e){
        PositionAwareButton clickedButton = (PositionAwareButton) e.getSource();
        clickedButton.setText(visualGame.getPlayer());
        visualGame.takeTurn(clickedButton.getAcross(), clickedButton.getDown(),
                                                    visualGame.getPlayer());
        depth += 1;
        visualGame.setGameDepth(depth);
        clickedButton.setEnabled(false);
    }

    private void setPlayerTurn(ActionEvent e) {
        visualGame.setTurn();
    }

    private void checkWinner(ActionEvent e) {

        JOptionPane gamePane = new JOptionPane();
        int response = 0;

        if(visualGame.getWinner() != -1) {
            response = gamePane.showConfirmDialog(null, visualGame.getGameStateMessage(),
                                        "New Game?", JOptionPane.YES_NO_OPTION);

            if(response == JOptionPane.YES_OPTION) {
                visualGrid = visualGame.stringGrid();
                visualGrid.emptyGrid();
                visualGame.setPlayer("X");
                depth = 0;
                visualGame.setGameDepth(0);
                for(int i = 0; i < 3; i++) {
                    for(int j = 0; j < 3; j++) {
                        gameButtons[i][j].setText(" ");
                        gameButtons[i][j].setEnabled(true);
                    }
                }
            } else {
                mainGame.restartGames();
            }
        }
    }
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

    private JButton makeLoadButton(){
        JButton loadButton = new JButton("Load Game");
        return loadButton;
    }


}
