package listeners.action;

import components.JStyleButton;
import containers.GamePanel;
import containers.StartPanel;
import containers.WordleFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GlobalActionListener implements ActionListener {
    private final WordleFrame frame;
    private StartPanel startPanel;
    private GamePanel gamePanel;

    /**
     * Generator to get the frame to listen
     * @param frame in this program is always WordleFrame
     */
    public GlobalActionListener(WordleFrame frame) {
        this.frame = frame;
        initialise();
    }

    /**
     * Initialise the instance
     */
    public void initialise() {
        this.startPanel = frame.getStartPanel();
        this.gamePanel = frame.getGamePanel();

        // Add all button to the GlobalActionListener
        addPanelBtn(startPanel.getCenterPanel().getComponents());
        addPanelBtn(gamePanel.getNorthPanel().getComponents());
    }

    /**
     * Adding GlobalActionListener to JButtons through array of components from panels.
     * @param components Array of components from panels.
     */
    public void addPanelBtn(Component[] components) {
        for (Component component : components) {
            if (component.getClass() == JButton.class || component.getClass() == JStyleButton.class) {
                ((JButton) component).addActionListener(this);
            }
        }
    }

    /**
     * Change the panel to the frame.getGamePanel().
     */
    public void gameStart() {
        frame.getStartPanel().setVisible(false);
        frame.setContentPane(frame.getGamePanel());
        frame.getGamePanel().setVisible(true);
        frame.getGamePanel().requestFocusInWindow();
        gamePanel.setBegin(System.currentTimeMillis());
        System.out.println("Start.");
    }

    /**
     * End the program.
     */
    public void gameExit() {
        System.out.println("Exit.");
        System.exit(0);
    }

    /**
     * Method to return to the main frame.
     */
    public void backToStart() {
        frame.getGamePanel().setVisible(false);
        frame.setContentPane(frame.getStartPanel());
        frame.getStartPanel().setVisible(true);
        frame.getStartPanel().requestFocusInWindow();
        System.out.println("Back to the start panel");
    }

    /**
     * Deal with the change of display panel.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // If pressed start button in StartPanel
        if (e.getSource() == startPanel.getStartBtn()) {
            gameStart();
        }

        // If pressed exit button in StartPanel
        if (e.getSource() == startPanel.getExitBtn()) {

            // A dialog to decide exit or not
            int choice = JOptionPane.showConfirmDialog(startPanel, "Do you confirm to exit?", "EXIT",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == 0) gameExit();
        }

        // // If pressed back button in GamePanel
        if (e.getSource() == gamePanel.getBackBtn()) backToStart();
    }
}
