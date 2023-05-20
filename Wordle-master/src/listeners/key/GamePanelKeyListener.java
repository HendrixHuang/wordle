package listeners.key;

import containers.GamePanel;
import containers.WordleFrame;
import dataClass.Record;
import supporter.Judgement;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Title:        GamePanelKeyListener.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description A KeyListener implements KeyListener interface for GamePanel class.
 **/
public class GamePanelKeyListener implements KeyListener {
    private GamePanel gamePanel;

    public GamePanelKeyListener() {}

    public GamePanelKeyListener(GamePanel owner) {
        setGamePanel(owner);
        getGamePanel().addKeyListener(this);
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }



    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * actions depend on return operation codes
     *
     * @param actionCode return operation codes from InputProcessor
     */
    public void actionDependOnInput(int actionCode) {
        switch (actionCode) {
            case 0:
                break;
            case 1:
            case 2:
                gamePanel.refresh();
                break;
            case 12: {
                String str = String.valueOf(gamePanel.getInputProcessor().getInput()) + " is not a word!";
                JOptionPane.showMessageDialog(gamePanel,
                        str, "Not A Word!", JOptionPane.ERROR_MESSAGE);
                break;
            }
            case 13: {
                String title = "Not Enough Characters!";
                String text = "Current input is " + String.valueOf(gamePanel.getInputProcessor().getInput()) +
                        ", length is " + gamePanel.getInputProcessor().getIdx() + ".\n" + title;
                JOptionPane.showMessageDialog(gamePanel,
                        text, title, JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case 14: {
                String title = "Empty Input!";
                String text = "Please type your keyboard to input.";
                JOptionPane.showMessageDialog(gamePanel, text, title, JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case 15: {
                gamePanel.getWordleLogic().logicCore(gamePanel.getInputProcessor().getInput());
                gamePanel.lineRefresh();
                if (! gamePanel.keyListener.endJudge()) {
                    gamePanel.getInputProcessor().clearOperation();
                    gamePanel.setCurrentRow(gamePanel.getCurrentRow() + 1);
                }
                break;
            }
        }
    }

    /**
     * Use Judgement to judge whether a game has ended.
     *
     * @return whether to do continue operation.
     */
    public boolean endJudge() {
        if (Judgement.isPlayerWin(gamePanel.getTable(), gamePanel.getCurrentRow())) {
            gamePanel.setEnd(System.currentTimeMillis());
            int duration = (int) ((gamePanel.getEnd() - gamePanel.getBegin()) / 1000);
            Record winRecord = new Record();
            gamePanel.getResourceReader().endRecord(true, gamePanel, winRecord);
            String winMessage = "Game win in " + (gamePanel.getCurrentRow() + 1) + " row(s) within " +
                    duration + "s.\n" + "You have beaten " +
                    ((Double) (gamePanel.getResourceReader().readRecord(duration) * 100)).intValue() +
                    " percentages of players";
            String title = "Cheers!";
            int choice = JOptionPane.showConfirmDialog(gamePanel,
                    winMessage, title, JOptionPane.YES_NO_OPTION);
            ((WordleFrame) gamePanel.getTopLevelAncestor()).getGlobalActionListener().backToStart();
            gamePanel.clear();
            return true;
        }

        if (Judgement.isFocusEnd(gamePanel.getCurrentRow())) {
            gamePanel.setEnd(System.currentTimeMillis());
            Record lostRecord = new Record();
            gamePanel.getResourceReader().endRecord(false, gamePanel, lostRecord);
            String lostMessage = "Wordle: " + String.valueOf(gamePanel.getWordleLogic().getWordle()) +
                    ".\nLevel: Gamer.\n Game Lost.";
            int choice = JOptionPane.showConfirmDialog(gamePanel,
                    lostMessage, "Game lost", JOptionPane.YES_NO_OPTION);
            ((WordleFrame) gamePanel.getTopLevelAncestor()).getGlobalActionListener().backToStart();
            gamePanel.clear();
            return true;
        }
        return false;
    }
}
