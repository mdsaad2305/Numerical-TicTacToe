package tictactoe;

import java.util.Iterator;

/**
 * class responsible for maintaining the state of the board in tictactoe
 */
public class TictactoeGrid extends boardgame.Grid{

    /**
     *
     * @param wide width of the board
     * @param tall height of the board
     */
    public TictactoeGrid(int wide, int tall) {
        super(wide, tall);
        initializeGrid();
    }


    /**
     * initialized the board with blank spaces
     */
    public void initializeGrid(){
        for(int i = 0; i < getHeight(); i++){
            for(int j = 0; j < getWidth(); j++){
                setValue(i + 1, j + 1, " ");
            }
        }
    }

    /**
     *
     * @return returns a board in the form of a string
     */
    @Override
    public String getStringGrid(){

        String wholeBoard = "";
        String boundary = "+-+-+-+";
        String element;
        int counter = 0;

        Iterator<String> iteration = iterator();

        for(int i = 0; iteration.hasNext(); i++){
            element = iteration.next();
            wholeBoard += "|" + element;
            counter++;

            if(counter == getWidth()){
                wholeBoard += "|";
                wholeBoard += "\n";
                wholeBoard += boundary;
                wholeBoard += "\n";
                counter = 0;
            }
        }

        return wholeBoard;
    }




}
