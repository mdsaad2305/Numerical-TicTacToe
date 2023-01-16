package numericalttt;

import boardgame.Grid;

public class NumericalGame extends boardgame.BoardGame implements boardgame.Saveable{

    private char player;
    private int gameDepth;

    /**
     * @param wide The desired width of the board
     * @param high The desired height of teh board
     */
    public NumericalGame(int wide, int high) {
        super(wide, high);
        setGrid(new NumericalGrid(wide,high));
    }

    /**Accessor method for Player
     * @return returns the player
     * */
    public char getPlayer() {
        return player;
    }

    /**Mutator method for player
     * @param myPlayer setting the player
     * */
    public void setPlayer(char myPlayer) {
        this.player = myPlayer;
    }

    /**Accessor method for gameDepth
     * @return returns the number of turns played
     * */
    public int getDepth() {
        return gameDepth;
    }

    /**mutator method for gamedepth
     * @param depth return the depth
     * */
    public void setDepth(int depth) {
        this.gameDepth = depth;
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
        setValue(across, down, input);
        return false;
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
        return false;
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
     * @return returns the winning condition
     */
    public Grid stringGrid() {
        return getGrid();
    }

    /**
     * changes the current player
     */
    public void setTurn() {
        if(player == 'O') {
            player = 'E';
        } else if (player == 'E') {
            player = 'O';
        }
    }

    /**
     *
     * @return returns winner
     */
    @Override
    public int getWinner() {
        return winner(gameDepth);
    }

    /**
     *
     * @param depth number of plays in the game
     * @return returns winner
     */
    public int winner(int depth) {
        if (horizontalWinner() == 1 && player == 'O') {
            return 1;
        } else if (horizontalWinner() == 1 && player == 'E') {
            return 2;
        }
        if (verticalWinner() == 1 && player == 'O') {
            return 1;
        } else if (verticalWinner() == 1 && player == 'E') {
            return 2;
        }
        if (diagonalWinner() == 1 && player == 'O') {
            return 1;
        } else if (diagonalWinner() == 1 && player == 'E') {
            return 2;
        }
        if(depth == 9) {
            return 0;
        }

        return -1;
    }
    private int horizontalWinner() {

        for(int i = 1; i <= getHeight(); i++){
            if (Integer.parseInt(getCell(1, i)) + Integer.parseInt(getCell(2, i))
                    + Integer.parseInt(getCell(3, i)) == 15 && getCell(1,i) != "0"
                        && getCell(2,i) != "0" && getCell(3, i) != "0"){
                return 1;
            }
        }
        return -1;
    }

    private int verticalWinner() {

        for (int i = 1; i <= getWidth(); i++) {
            if (Integer.parseInt(getCell(i, 1)) + Integer.parseInt(getCell(i, 2))
                     + Integer.parseInt(getCell(i, 3)) == 15 && getCell(i, 1) != "0"
                        && getCell(i,2) != "0" && getCell(i,3) != "0") {
                return 1;
            }
        }
        return -1;
    }

    private int diagonalWinner() {

        if (Integer.parseInt(getCell(1, 1)) + Integer.parseInt(getCell(2, 2))
                + Integer.parseInt(getCell(3, 3)) == 15 && getCell(1,1) != "0"
                    && getCell(2,2) != "0" && getCell(3,3) != "0") {
            return 1;
        } else if (Integer.parseInt(getCell(3, 1)) + Integer.parseInt(getCell(2, 2))
                + Integer.parseInt(getCell(1, 3)) == 15 && getCell(3,1) != "0"
                    && getCell(2,2) != "0" && getCell(1,3) != "0") {
            return 1;
        }
        return -1;
    }

    /**
     *
     * @return returns a string with a message of the gameState
     */
    @Override
    public String getGameStateMessage() {
        String message;

        if (getWinner() == 1) {
            message = "Player O wins! \nGood job Player O";
        } else if(getWinner() == 2) {
            message = "Player E wins! \nNice going Player E";
        } else{
            message = "The game is a Tie\n Well played you two!";
        }

        return message;
    }

    /**
     *
     * @return returns a string for csv format
     */
    @Override
    public String getStringToSave() {
        String fileString = "";
        fileString += player;
        fileString += "\n";

        for(int i = 1; i <= getHeight(); i++){
            for(int j = 1; j <= getWidth(); j++){
                if(getCell(j,i) == "0") {
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
     * @param toLoad string to load
     */
    @Override
    public void loadSavedString(String toLoad) {

    }

}
