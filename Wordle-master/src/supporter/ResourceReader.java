package supporter;

import containers.GamePanel;
import dataClass.Record;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class ResourceReader {

    /**
     * target word
     */
    private char[] wordle;

    /**
     * ArrayList to store words read from files
     */
    private final ArrayList<String> dictionary = new ArrayList<>();

    /**
     * Default Constructor method
     */
    public ResourceReader() {
        queryWords();
        generateWordle();
    }

    /**
     * Method to query words from files or databases and then store them in the memory
     */
    public void queryWords() {
        File f = new File("src/resources/dictionary.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            while (str != null) {
                dictionary.add(str.toUpperCase());
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error");
            System.exit(-1);
        }
    }

    /**
     * A method to generate the target word from the dictionary
     */
    public void generateWordle() {
        Random random = new Random();
        wordle = dictionary.get(random.nextInt(0, dictionary.size())).toCharArray();
    }

    /**
     * Read the file to decide player's level
     * @param duration
     * @return
     */
    public double readRecord(int duration) {
        int recordNum = 0, winNum = 0;
        double rankPercent = 1.0;
        File df = new File("src/gameData/data.txt");
        try {
            FileReader dfr = new FileReader(df);
            BufferedReader dfBfr = new BufferedReader(dfr);
            String lineText = dfBfr.readLine();
            while (lineText != null) {
                Record lineRecord = new Record(lineText.split("\t"));
                if (lineRecord.getDuration() > duration) winNum++;
                recordNum++;
                lineText = dfBfr.readLine();
            }
            rankPercent =  ((double) winNum / (double) recordNum);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return rankPercent;
    }

    /**
     * Write game message to the file
     * @param isWin
     * @param gamePanel
     * @param record
     */
    public void endRecord(boolean isWin, GamePanel gamePanel, Record record) {
        File df = new File("src/gameData/data.txt");
        try {
            FileWriter dfw = new FileWriter(df, true);
            String begin = "begin: " + gamePanel.getBegin();
            String end = "end: " + gamePanel.getEnd();
            String duration = "duration: " + ((gamePanel.getEnd() - gamePanel.getBegin()) / 1000) + "s";
            String rows = "rows: " + (gamePanel.getCurrentRow() + 1);
            String state;
            if (isWin) state = "state: win";
            else  state = "state: lost";
            String content = String.join("\t", begin, end, duration, rows, state, "\n");
            dfw.write(content);
            dfw.close();
            System.out.println("Write \"" +content + "\" to file " + df);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Judge that whether the input char array char[5] input is a word.
     *
     * @return true, when it's a word; Otherwise false.
     */
    public boolean searchWord(char[] input) {
        return dictionary.contains(String.valueOf(input));
    }

    /**
     * Clear all temperate variables and initialise them
     */
    public void reset() {
        generateWordle();
    }

    /**
     * @return dictionary of all words
     */
    public ArrayList<String> getDictionary() {
        return dictionary;
    }

    /**
     * Get wordle(target word) as a char array
     * @return wordle(target word) as a char array.
     */
    public char[] getWordle() {
        return wordle;
    }

    /**
     * set wordle(target word)
     * @param wordle wordle to be set
     */
    public void setWordle(char[] wordle) {
        this.wordle = wordle;
    }
}
