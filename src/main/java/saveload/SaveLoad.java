package saveload;

import java.io.FileWriter;
import numericalttt.NumericalGame;
import tictactoe.Tictactoegame;

/**
 * Class responsible for saving and loading the file
 * @author Mohammed Vuppukar
 */
public class SaveLoad {

    /**
     *
     * @param fileName name of the file
     * @param textGame game class for numerical tictactoe
     */
    public static void saveToFile(String fileName, NumericalGame textGame){

        try{
            FileWriter saveFile = new FileWriter(fileName, false);
            saveFile.append(textGame.getStringToSave());
            saveFile.flush();
            saveFile.close();

        } catch (Exception e) {
            System.out.println("Problem while saving file");
        }

    }

    /**
     *
     * @param fileName name of the file
     * @param textGame game class for original tictactoe
     */
    public static void saveToFile(String fileName, Tictactoegame textGame){

        try{
            FileWriter saveFile = new FileWriter(fileName, false);
            saveFile.append(textGame.getStringToSave());
            saveFile.flush();
            saveFile.close();

        } catch (Exception e) {
            System.out.println("Problem while saving file");
        }

    }

}
