package components;

import javax.swing.*;
import java.awt.*;


public class VerSpacer extends JPanel {

    /**
     * len unit
     */
    private int unit = 2;

    /**
     * per unit length
     */
    private int perLen = 100;

    /**
     * Default spacer with 2 unit and 100 per(unit)Len.
     */
    public VerSpacer() {
        initialise();
    }

    /**
     * Spacer with indicated number of unit and 100 per(unit)Len.
     *
     * @param unit len unit to be set
     */
    public VerSpacer(int unit) {
        this.unit = unit;
        initialise();
    }

    /**
     * Spacer with indicated number of unit and indicated per(unit)Len.
     *
     * @param unit   len unit to be set
     * @param perLen per unit length to be set
     */
    public VerSpacer(int unit, int perLen) {
        this.unit = unit;
        this.perLen = perLen;
        initialise();
    }

    /**
     * Initialise HorSpacer instance.
     */
    public void initialise() {
        this.setLayout(new GridLayout(unit, 1, 5, perLen));
        for (int i = 0; i < unit; i++) {
            JLabel spacer = new JLabel();
            spacer.setOpaque(false);
            this.add(spacer);
        }
        this.setOpaque(false);
    }
}
