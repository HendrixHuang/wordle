package components;

import javax.swing.*;
import java.awt.*;


public class JStyleButton extends JButton {
    /**
     * Colour code constance
     */
    public static final int NON = 0, PINK = 1;

    /**
     * Colour code to use
     */
    private int colorCode = NON;

    /**
     * Default constructor of special design Button
     */
    public JStyleButton() {
        super();
        initialise();
    }

    /**
     * @param text The text that the button contains.
     */
    public JStyleButton(String text) {
        super(text);
        initialise();
    }


    /**
     * Generator method to set Button text and Button color through colorCode
     * @param text
     * @param colorCode
     */
    public JStyleButton(String text, int colorCode) {
        super(text);
        setColorCode(colorCode);
        initialise();
    }

    /**
     * Initialise JStyleButton instances
     */
    public void initialise() {
        this.setOpaque(true);
        if (colorCode == 0) this.setBackground(new Color(0, 0, 0));
        if (colorCode == 1) this.setBackground(new Color(255, 0, 204));

        this.setForeground(new Color(255, 255, 255));
        this.setFont(new Font("Arial BLACk", Font.ITALIC, 16));
        this.setPreferredSize(new Dimension(100, 40));
    }

    /**
     * Getter method of instance variable colorCode.
     *
     * @return instance variable colorCode
     */
    public int getColorCode() {
        return colorCode;
    }

    /**
     * Setter method of instance variable colorCode.
     *
     * @param colorCode another color code to set
     */
    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }
}
