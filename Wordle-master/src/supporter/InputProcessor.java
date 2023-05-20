package supporter;

import java.awt.event.KeyEvent;
import java.util.Arrays;


public class InputProcessor {
    /**
     * Operation Codes
     */
    public final int NO_OPERATION = 0, SET_A_CHARACTER = 1, REMOVE_A_CHARACTER = 2,
            NOT_A_WORD = 12, NOT_ENOUGH = 13, EMPTY = 14, IS_A_WORD = 15;

    /**
     * user input
     */
    private final char[] input;

    /**
     * target word
     */
    public char[] wordle;

    /**
     * resourceReader to get dictionary
     */
    private final ResourceReader resourceReader;

    /**
     * current input index
     */
    private int idx = 0;

    public InputProcessor(int size, ResourceReader reader) {
        input = new char[size];
        Arrays.fill(input, ' ');
        this.resourceReader = reader;
        wordle = resourceReader.getWordle();
    }

    /**
     * @param c Character to be filtered.
     * @return Operation codes.
     */
    public int inputProcess(char c) {
        int tmpIdx = this.getIdx();
        if (c >= 'a' && c <= 'z') {
            if (tmpIdx < this.input.length) {
                return setOperation((char) (c - 32));
            }
        }
        if (c >= 'A' && c <= 'Z') {
            if (tmpIdx < this.input.length) {
                return setOperation(c);
            }
        }
        if (c == KeyEvent.VK_BACK_SPACE) {
            if (tmpIdx > 0) {
                return removeOperation();
            }
        }
        if (c == KeyEvent.VK_ENTER || c == KeyEvent.VK_SPACE) {
            if (tmpIdx == input.length)
                if (resourceReader.searchWord(input)) return IsAWord();
                else return errorNotAWord();
            else if (tmpIdx == 0) return errorEmptyInput();
            else return errorNotEnoughChar();
        }

        return noOperation();
    }

    // Operation methods

    /**
     * This method will do nothing to the current input array.
     *
     * @return NO_OPERATION code, which is the number 0, to the inputProcess method.
     */
    private int noOperation() {
        System.out.println("Nothing could have happened...");
        return NO_OPERATION;
    }

    /**
     * The setOperation(char c) method prints the set operation and the saved input phrase, idx +1,
     * and returns a code SET_A_CHARACTER.
     *
     * @param c Character to be stored.
     * @return Operation code SET_A_CHARACTER.
     */
    private int setOperation(char c) {
        input[getIdx()] = c;
        System.out.printf("Set input[%d] = %c. Current input is %s\n", getIdx(),
                input[getIdx()], String.valueOf(input));
        setIdx(getIdx() + 1);
        return SET_A_CHARACTER;
    }

    /**
     * The removeOperation(char c) method prints the remove operation and
     * the before and after removal input phrase, idx - 1,
     * and returns an operation code REMOVE_A_CHARACTER.
     *
     * @return operation code REMOVE_A_CHARACTER
     */
    private int removeOperation() {
        System.out.println("Previous input is " + String.valueOf(input) + ".");
        setIdx(getIdx() - 1);
        System.out.printf("Remove input[%d] = %c.\n", getIdx(), input[getIdx()]);
        this.input[getIdx()] = ' ';
        System.out.println("Current input is " + String.valueOf(input));
        System.out.println();
        return REMOVE_A_CHARACTER;
    }

    /**
     * Reset the game runtime variable to initial state.
     */
    public void clearOperation() {
        Arrays.fill(input, ' ');
        setIdx(0);
    }


    // Information convey methods

    /**
     * Print information that input is a word and return an information code IS_A_WORD.
     * @return information code IS_A_WORD
     */
    private int IsAWord() {
        System.out.println(String.valueOf(getInput()) + " is a word.");
        return IS_A_WORD;
    }

    // Warnings methods

    /**
     * Print information that input is not word and return an error information code NOT_A_WORD.
     * @return error information code NOT_A_WORD
     */
    private int errorNotAWord() {
        System.out.println(String.valueOf(input) + " is not a word.");
        return NOT_A_WORD;
    }

    /**
     * Print information that input has not enough characters and return an error information code NOT_ENOUGH.
     * @return
     */
    private int errorNotEnoughChar() {
        System.out.println("Not Enough Characters!");
        return NOT_ENOUGH;
    }

    /**
     * Clear all temperate variables and initialise them
     */
    public void reset() {
        clearOperation();
        wordle = resourceReader.getWordle();
    }

    /**
     * Empty error message.
     *
     * @return Error code EMPTY.
     */
    private int errorEmptyInput() {
        System.out.println("Input Empty!!!");
        return EMPTY;
    }

    // Getter and Setter methods

    /**
     * The getter method of instance variable char array input.
     * @return char array input
     */
    public char[] getInput() {
        return input;
    }

    /**
     * The getter method of instance variable idx.
     *
     * @return idx that is in [0, 5].
     */
    public int getIdx() {
        return idx;
    }

    /**
     * The setter method to set instance variable idx.
     *
     * @param idx To be set iff it's in [0, 5]. That is between 0 and 5, inclusive.
     */
    public void setIdx(int idx) {
        if (idx >= 0 && idx <= 5) {
            this.idx = idx;
        }
    }

    /**
     * The getter method of instance variable wordle.
     * @return target word
     */
    public char[] getWordle() {
        return wordle;
    }

    /**
     * The setter method to set instance variable wordle.
     *
     * @param wordle another target word
     */
    public void setWordle(char[] wordle) {
        this.wordle = wordle;
    }
}
