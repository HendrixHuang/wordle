package components;

import javax.swing.*;
import java.util.Arrays;


public class HorSpacer extends JPanel {

    /**
     * A filler to modify the size of the panel
     */
    private char[] filler;

    /**
     * unit to decide size of filler
     */
    private int unit = 40;

    /**
     * Default Generator, using 40 spaces to control the width.
     */
    public HorSpacer() {
        initialise();
    }

    /**
     * Indicate the number of units of space to control the width.
     *
     * @param unit Number of units of space.
     */
    public HorSpacer(int unit) {
        this.unit = unit;
        initialise();
    }

    /**
     * Initialise HorSpacer instance
     */
    public void initialise() {
        filler = new char[unit];
        Arrays.fill(filler, ' ');
        JLabel spacer = new JLabel(String.valueOf(filler));
        this.add(spacer);
        this.setOpaque(false);
        spacer.setOpaque(false);
    }
}
