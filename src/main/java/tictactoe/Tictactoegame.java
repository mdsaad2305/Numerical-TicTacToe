package tictactoe;
import boardgame.Grid;

public class Tictactoegame extends boardgame.BoardGame implements boardgame.Saveable {

    private int gameDepth;
    private String player;
    private static String[] symbols = {"X", "O"};
    private static int[] location = {1, 2, 3};

    /**
     *
     * @return returns the number of plays in the game
     */
    public int getGameDepth() {
        return gameDepth;
    }

    /**
     *
     * @param depth number of plays in the game
     */
    public void setGameDepth(int depth) {
        this.gameDepth = depth;
    }

    /**
     *
     * @return returns the current player
     */
    public String getPlayer() {
        return player;
    }

    /**
     *
     * @param turn the current player
     */
    public void setPlayer(String turn) {
        this.player = turn;
    }

    /**
     * @param wide The desired width of the board
     * @param high The desired height of teh board
     */
    public Tictactoegame(int wide, int high) {
        super(wide, high);
        setGrid(new TictactoeGrid(wide, high));
    }

    /**
     *
     * @param across across index, 1 based
     * @param down  down index, 1 based
     * @param input  String input from game
     * @return returns false
     */
    @Override
    public boolean takeTurn(int across, int down, String input) {

        boolean validInput = false;

        try{
            validateInput(input);
            //validateLocation(across,down);
            setValue(across, down, input);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        return validInput;
    }

    /**
     *
     * @param across across index, zero based
     * @param down  down index, zero based
     * @param input  int input from game
     * @return returns false
     */
    @Override
    public boolean takeTurn(int across, int down, int input) {

        boolean validInput = false;

        setValue(across, down, input);

        return validInput;
    }

    /**
     *
     * @return returns false
     */
    @Override
    public boolean isDone() {
        return false;
    }

    /**
     *
     * @return returns the winner
     */
    @Override
    public int getWinner() {

        return winner(gameDepth);

    }

    /**
     *
     * @return returns the state of the game
     */
    @Override
    public String getGameStateMessage() {

        String message;

        if (getWinner() == 1) {
            message = "Player X wins! \nGood job Player X";
        } else if(getWinner() == 2) {

            message = "Player O wins! \nNice going Player O";
        } else{
            message = "The game is a Tie\n Well played you two!";
        }

        return message;
    }

    /**
     *
     * @return returns the grid maintaining the tictactoe board
     */
    public Grid stringGrid() {
        return getGrid();
    }

    /**
     *  changing player turn
     */
    public void setTurn() {

        if(player == "X") {
            player = "O";
        } else if (player == "O") {
            player = "X";
        }
    }


    private int winner(int depth) {
        if (horizontalWinner() == 1) {
            return 1;
        } else if (horizontalWinner() == 2) {
            return 2;
        }
        if (verticalWinner() == 1) {
            return 1;
        } else if (verticalWinner() == 2) {
            return 2;
        }
        if (diagonalWinner() == 1) {
            return 1;
        } else if (diagonalWinner() == 2) {
            return 2;
        }
        if(depth == 9) {
            return 0;
        }

        return -1;
    }
    private int horizontalWinner() {

        for(int i = 1; i <= getHeight(); i++){
            if (getCell(1, i) == getCell(2, i)
                    && getCell(2, i) == getCell(3, i)
                        && getCell(3,i) == "X"){
                return 1;
            } else if (getCell(1, i) == getCell(2, i)
                    && getCell(2, i) == getCell(3, i)
                        && getCell(3,i) == "O"){
                return 2;
            }
        }

        return -1;
    }

    private int verticalWinner() {

        for (int i = 1; i <= getWidth(); i++) {
            if (getCell(i, 1) == getCell(i, 2)
                    && getCell(i, 2) == getCell(i, 3)
                    && getCell(i, 3) == "X") {
                return 1;
            } else if (getCell(i, 1) == getCell(i, 2)
                    && getCell(i, 2) == getCell(i, 3)
                    && getCell(i, 3) == "O") {
                return 2;
            }

        }
        return -1;
    }

    private int diagonalWinner() {

        if (getCell(1, 1) == getCell(2, 2)
                && getCell(2, 2) == getCell(3, 3)
                && getCell(3, 3) == "X") {
            return 1;
        } else if (getCell(3, 1) == getCell(2, 2)
                && getCell(2, 2) == getCell(1, 3)
                && getCell(1, 3) == "X") {
            return 1;
        } else if (getCell(1, 1) == getCell(2, 2)
                && getCell(2, 2) == getCell(3, 3)
                && getCell(3, 3) == "O") {
            return 2;
        } else if (getCell(3, 1) == getCell(2, 2)
                && getCell(2, 2) == getCell(1, 3)
                && getCell(1, 3) == "O") {
            return 2;
        }
        return -1;
    }

    /**
     *
     * @param input user input
     * @throws Exception
     */
    public void validateInput(String input) throws Exception{
        for(String possible: symbols){
            if(input.equals(possible)){
                return;
            }
        }
        throw new Exception("Invalid input");
    }

    /**
     *
     * @param across across the board
     * @param down down the board
     * @throws Exception
     */
    public void validateLocation(int across, int down) throws Exception{

        if(across > 3) {
            throw new Exception("out of upper bound for across");
        }
        if(across < 1) {
            throw new Exception("out of lower bound for across");
        }
        if(down > 3) {
            throw new Exception("out of upper bound for down");
        }
        if(down < 1) {
            throw new Exception("out of lower bound for down");
        }

    }

    /**
     *
     * @return returns the string for csv format
     */
    @Override
    public String getStringToSave() {
        String fileString = "";
        fileString += player;
        fileString += "\n";

        for(int i = 1; i <= getHeight(); i++){
            for(int j = 1; j <= getWidth(); j++){
                if(getCell(j,i) == " ") {
                    fileString += "";
                } else {
                    fileString += getCell(j,i);
                }
                if(j < 3) {
                    fileString += ",";
                }
            }
            if(i < 3){
                fileString += "\n";
            }
        }
        return fileString;
    }

    /**
     *
     * @param toLoad string loaded from csv file
     */
    @Override
    public void loadSavedString(String toLoad) {
    }
}
