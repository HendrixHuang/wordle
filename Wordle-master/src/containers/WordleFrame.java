package containers;

import listeners.action.GlobalActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class WordleFrame extends JFrame {
    /**
     * The start penal
     */
    private final StartPanel startPanel = new StartPanel();

    /**
     * The game penal
     */
    private final GamePanel gamePanel = new GamePanel();

    /**
     * The GlobalActionListener
     */
    private final GlobalActionListener globalActionListener = new GlobalActionListener(this);

    /**
     * Constructor initialise class itself
     */
    public WordleFrame() {
        initFrame();
    }

    /**
     * Constructor to set title by calling superclass constructor and initialise class itself
     * @param title Title of the frame
     */
    public WordleFrame(String title) {
        super(title);
        initFrame();
    }

    /**
     * Initialisation of the containers.WordleFrame instance.
     */
    public void initFrame() {
        // Set the size and position of the frame
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int width = screenWidth / 3;
        int height = (int) (screenHeight / 1.5);
        int x = screenWidth / 4;
        int y = screenHeight / 4;
        this.setSize(width, height);
        this.setBounds(x, y, width, height);
        this.setResizable(false);

        // Icon Setting
        try {
            this.setIconImage(ImageIO.read(new File("src/resources/img/alycei_coni.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        // Background Image Setting
        ImageIcon icon = new ImageIcon("src/resources/img/coverageDohna1.jpg");
        JLabel imgLabel = new JLabel(icon);
        imgLabel.setSize(this.getSize());
        imgLabel.setOpaque(true);
        imgLabel.setBackground(new Color(255, 51, 140));
        this.getLayeredPane().add(imgLabel, Integer.valueOf(Integer.MIN_VALUE));

        // Set Content Panel
        this.setContentPane(startPanel);

        // Settings
        this.setTitle("Wordle Power");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Method to get the startPanel
     * @return startPanel
     */
    public StartPanel getStartPanel() {
        return startPanel;
    }

    /**
     * Method to get the gamePanel
     * @return gamePanel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Method to get the globalActionListener
     * @return globalActionListener
     */
    public GlobalActionListener getGlobalActionListener() {
        return globalActionListener;
    }
}
