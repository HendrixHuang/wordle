package supporter;

import components.JStyleLabel;

import java.awt.*;

public class Judgement {

    /**
     * Judge that whether a player has wined the game or not.
     *
     * @param table      The 2D table of JStyleLabels.
     * @param currentRow The current row of the table.
     * @return true if player has wined or false otherwise.
     */
    public static boolean isPlayerWin(JStyleLabel[][] table, int currentRow) {
        for (int i = 0; i < 5; i++) {
            if (!table[currentRow][i].getBackground().equals(Color.GREEN)) return false;
        }
        return true;
    }

    /**
     * Judge that whether a player has lost the game and should be focused to end the game.
     *
     * @param currentRow The current row of the table.
     * @return true if player has lost or false otherwise.
     */
    public static boolean isFocusEnd(int currentRow) {
        return currentRow == 5;
    }
}
