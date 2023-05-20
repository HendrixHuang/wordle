package containers;

import components.HorSpacer;
import components.JStyleButton;
import components.VerSpacer;

import javax.swing.*;
import java.awt.*;


public class StartPanel extends JPanel {

    /**
     * Button to start game
     */
    private final JStyleButton startBtn = new JStyleButton("Start");

    /**
     * Button to modify settings, not yet finished
     */
    private final JStyleButton settingBtn = new JStyleButton("Settings");

    /**
     * Button to see the ranks, not yet finished
     */
    private final JStyleButton rankBtn = new JStyleButton("Ranks");

    /**
     * Button to exit the game
     */
    private final JStyleButton exitBtn = new JStyleButton("Exit");

    /**
     * JLabel to show the game Title
     */
    private final JLabel gameTitle = new JLabel();

    /**
     * Center JPanel to contains the four buttons above
     */
    private JPanel centerPanel;

    /**
     * Spacer to horizontal modify layout
     */
    private HorSpacer westHorSpacer;

    /**
     * Spacer to horizontal modify layout
     */
    private HorSpacer eastHorSpacer;

    /**
     * Spacer to vertical modify layout
     */
    private VerSpacer southVerSpacer;

    /**
     * Default constructor method
     */
    public StartPanel() {
        super();
        initialise();
    }

    /**
     * Initialise startPanel instance
     */
    private void initialise() {
        this.setLayout(new BorderLayout(this.getWidth() / 10, this.getHeight() / 10));

        gameTitle.setMinimumSize(new Dimension(200, 150));
        gameTitle.setFont(new Font("Arial Black", Font.PLAIN, 72));
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("src/resources/img/Wordle.png");
        gameTitle.setIcon(icon);
        gameTitle.setOpaque(false);

        centerPanel = new JPanel();
//        centerPanel.setMinimumSize(new Dimension(100, 150));
        centerPanel.setMaximumSize(new Dimension(100, 150));
        centerPanel.setLayout(new GridLayout(5, 1, 10, 15));
        centerPanel.add(new VerSpacer(2, 50));
        centerPanel.add(startBtn);
        centerPanel.add(settingBtn);
        centerPanel.add(rankBtn);
        centerPanel.add(exitBtn);

        this.add(BorderLayout.NORTH, gameTitle);
        this.add(BorderLayout.CENTER, centerPanel);
        westHorSpacer = new HorSpacer();
        this.add(BorderLayout.WEST, westHorSpacer);
        eastHorSpacer = new HorSpacer();
        this.add(BorderLayout.EAST, eastHorSpacer);
        southVerSpacer = new VerSpacer();
        this.add(BorderLayout.SOUTH, southVerSpacer);

        // Set Opaque false
        this.setOpaque(false);
        centerPanel.setOpaque(false);
    }

    /**
     * Get startBtn as a JStyleButton instance.
     * @return startBtn as a JStyleButton
     */
    public JStyleButton getStartBtn() {
        return startBtn;
    }

    /**
     * Get settingBtn as a JStyleButton instance.
     * @return settingBtn as a JStyleButton instance.
     */
    public JStyleButton getSettingBtn() {
        return settingBtn;
    }

    /**
     * Get rankBtn as a JStyleButton instance.
     * @return rankBtn as a JStyleButton
     */
    public JStyleButton getRankBtn() {
        return rankBtn;
    }

    /**
     * Get exitBtn as a JStyleButton instance.
     * @return exitBtn as a JStyleButton
     */
    public JStyleButton getExitBtn() {
        return exitBtn;
    }

    /**
     * Get gameTile as a JLabel instance.
     * @return gameTile as a JLabel
     */
    public JLabel getGameTitle() {
        return gameTitle;
    }

    /**
     * Get centerPanel as a JPanel instance.
     * @return centerPanel as a JPanel instance
     */
    public JPanel getCenterPanel() {
        return centerPanel;
    }

    /**
     * Set centerPanel as another JPanel instance.
     * @param centerPanel another JPanel instance to be set
     */
    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    /**
     * Get westHorSpacer as a HorSpacer instance.
     * @return westHorSpacer
     */
    public HorSpacer getWestHorSpacer() {
        return westHorSpacer;
    }

    /**
     * Set westHorSpacer as another HorSpacer instance.
     * @param westHorSpacer another HorSpacer instance to be set
     */
    public void setWestHorSpacer(HorSpacer westHorSpacer) {
        this.westHorSpacer = westHorSpacer;
    }

    /**
     * Get eastHorSpacer as a HorSpacer instance.
     * @return eastHorSpacer
     */
    public HorSpacer getEastHorSpacer() {
        return eastHorSpacer;
    }

    /**
     * Set eastHorSpacer as another HorSpacer instance.
     * @param eastHorSpacer another HorSpacer instance to be set
     */
    public void setEastHorSpacer(HorSpacer eastHorSpacer) {
        this.eastHorSpacer = eastHorSpacer;
    }

    /**
     * Get southVerSpacer as a VerSpacer instance.
     * @return southVerSpacer
     */
    public VerSpacer getSouthVerSpacer() {
        return southVerSpacer;
    }

    /**
     * Set southVerSpacer as another VerSpacer instance.
     * @param southVerSpacer another VerSpacer instance to be set
     */
    public void setSouthVerSpacer(VerSpacer southVerSpacer) {
        this.southVerSpacer = southVerSpacer;
    }
}
