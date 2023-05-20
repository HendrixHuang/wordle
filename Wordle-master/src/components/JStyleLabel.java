package components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class JStyleLabel extends JLabel {

    /**
     * Default Generator method of JStyleLabel
     */
    public JStyleLabel() {
        super();
        initLabel();
    }

    /**
     * Generator method of JStyleLabel with initialisation of content text
     * @param text content text
     */
    public JStyleLabel(String text) {
        super(text);
        initLabel();
    }

    /**
     * Initialisation of JStyleLabel,
     */
    public void initLabel() {
        this.setBackground(Color.white);
        this.setOpaque(true);
        this.setBorder(LineBorder.createBlackLineBorder());
        this.setPreferredSize(new Dimension(20, 20));
        this.setHorizontalAlignment(CENTER);
        this.setFont(new Font("Arial Black", Font.BOLD, 30));
    }
}
