package numericalttt;

import boardgame.Grid;
import saveload.SaveLoad;
import java.util.Scanner;

public class NumericalTextUI {
    private NumericalGame numberGame = new NumericalGame(3,3);
    private Grid textGrid;
    private Scanner scanInt = new Scanner(System.in);
    private int horizontal;
    private int vertical;
    private String input;
    private SaveLoad saveLoadFile;


    public NumericalTextUI(){
        numberGame = new NumericalGame(3,3);
        textGrid = numberGame.stringGrid();
    }

    private void playGame() {
        int depth = 0;
        numberGame.setDepth(depth);
        numberGame.setPlayer('O');
        while(numberGame.getWinner() == -1){
            gameInput();
            if(horizontal == 0) {
                break;
            }
            numberGame.takeTurn(horizontal,vertical,input);
            numberGame.setTurn();
            depth += 1;
            numberGame.setDepth(depth);
        }
        if(numberGame.getWinner() == -1) {
            System.out.println("Enter a filename for a csv file");
            String fileName = scanInt.next();
            saveLoadFile = new SaveLoad();
            saveLoadFile.saveToFile(fileName, numberGame);
        } else {
            numberGame.setTurn();
            System.out.println(textGrid.getStringGrid());
            System.out.println(numberGame.getGameStateMessage());
        }
    }

    private void gameInput() {
        System.out.println("Hey! seems like it is your turn " + numberGame.getPlayer() + "\n");
        System.out.println(textGrid.getStringGrid());
        System.out.println("Enter a number from 1-3 for horizontal navigation or 0 for saving game: ");
        horizontal = scanInt.nextInt();
        if(horizontal != 0) {
            System.out.println("Enter a number from 1-3 for vertical navigation: ");
            vertical = scanInt.nextInt();
            System.out.println("Enter the number you want to input: ");
            input = scanInt.next();
        }
    }

    public static void main(String[] args){
        NumericalTextUI myUi = new NumericalTextUI();
        myUi.playGame();
    }
}

