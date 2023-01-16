package numericalttt;

import java.util.Iterator;

/**
 * This class is responsible for maintaining the state of the
 * board in Numerical Tictactoe
 * */
public class NumericalGrid extends boardgame.Grid{

    /**
     * Constructor for NumericalGrid
     * */
    public NumericalGrid(int wide, int tall) {
        super(wide, tall);
        initializeGrid();
    }

    /**
     * This method initializes the board with 0s
     * */
    public void initializeGrid(){
        for(int i = 0; i < getHeight(); i++){
            for(int j = 0; j < getWidth(); j++){
                setValue(i + 1, j + 1, "0");
            }
        }
    }

    /**This method returns a string in the form of a board
     * @return returns a board in the form of a string
     * */
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
