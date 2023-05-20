package listeners.action;

import containers.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanelActionListener implements ActionListener {

    /**
     * This listener now oly serve for the only GamePanel
     */
    private final GamePanel gamePanel;

    /**
     * Initialise GamePanelActionListener instance by storing the user instance GamePanel
     * @param gamePanel the user instance
     */
    public GamePanelActionListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initialise();
    }

    /**
     * Initialisation of GamePanelActionListener instance
     */
    private void initialise() {
        gamePanel.getBackBtn().addActionListener(this);
        gamePanel.getResetBtn().addActionListener(this);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gamePanel.getBackBtn() || e.getSource() == gamePanel.getResetBtn()) {
            gamePanel.clear();
        }
        if (e.getSource() == gamePanel.getResetBtn()) {
            gamePanel.clear();
            gamePanel.requestFocus();
        }
    }
}
