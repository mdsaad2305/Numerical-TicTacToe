package tictactoe;

import boardgame.Grid;
import saveload.SaveLoad;

import java.util.Scanner;

public class TextUI {

    private Tictactoegame textGame = new Tictactoegame(3,3);
    private Grid textGrid;
    private Scanner scanInt = new Scanner(System.in);
    private SaveLoad saveLoadFile;
    private int horizontal;
    private int vertical;

    /**
     * Constructor for TextUI
     */
    public TextUI(){
        textGame = new Tictactoegame(3,3);
        textGrid = textGame.stringGrid();
    }

    /**
     * method reesponsible for playing the tictactoe game
     */
    public void playGame() {
        int depth = 0;
        textGame.setGameDepth(depth);
        textGame.setPlayer("X");

        while(textGame.getWinner() == -1) {
            input();
            if(horizontal == 0) {
                break;
            }
            textGame.takeTurn(horizontal,vertical, textGame.getPlayer());
            textGame.setTurn();
            depth += 1;
            textGame.setGameDepth(depth);
        }
        if(textGame.getWinner() == -1){
            System.out.println("Enter a filename to save tictactoe game");
            String fileName = scanInt.next();
            saveLoadFile = new SaveLoad();
            saveLoadFile.saveToFile(fileName, textGame);
        }
        textGame.setTurn();
        System.out.println(textGrid.getStringGrid());
        System.out.println(textGame.getGameStateMessage());
    }

    /**
     * method responsible for asking the player for input
     */
    private void input(){
        System.out.println("Hey! seems like it is your turn Player" + textGame.getPlayer() + "\n");
        System.out.println(textGrid.getStringGrid());
        System.out.println("Enter a number from 1-3 for horizontal navigation or press 0 for save: ");
        horizontal = scanInt.nextInt();
        if(horizontal != 0) {
            System.out.println("Enter a number from 1-3 for vertical navigation: ");
            vertical = scanInt.nextInt();
        }
    }

    /**
     * main method to run the TextUI
     * @param args command line argument maybe?
     */
    public static void main(String[] args) {
        TextUI playTictactoe = new TextUI();
        playTictactoe.playGame();
    }

}
