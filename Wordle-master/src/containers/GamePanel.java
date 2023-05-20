package containers;

import components.HorSpacer;
import components.JStyleButton;
import components.JStyleLabel;
import components.VerSpacer;
import listeners.action.GamePanelActionListener;
import listeners.key.GamePanelKeyListener;
import supporter.InputProcessor;
import supporter.ResourceReader;
import supporter.WordleLogic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


public class GamePanel extends JPanel implements KeyListener {

    /**
     * read resource from files
     */
    private ResourceReader resourceReader;

    /**
     * member to process input
     */
    private InputProcessor inputProcessor;

    /**
     * member to determine the result colours
     */
    private WordleLogic wordleLogic;

    /**
     * ActionListener for GamePanel
     */
    private GamePanelActionListener actionListener;

    public GamePanelKeyListener keyListener;

    /**
     * wordSize can be expanded in the future as a challenge,
     * currentRow is the denotation of current row of input
     */
    private int wordSize = 5, currentRow = 0;

    /**
     * Game start time and end time in system time
     */
    private long begin, end;

    /**
     * back to start panel when pressed
     */
    private final JStyleButton backBtn = new JStyleButton("Back", JStyleButton.PINK);

    /**
     * Reset all the data in game panel(restart a game)
     */
    private final JStyleButton resetBtn = new JStyleButton("Reset", JStyleButton.PINK);

    /**
     * The components to form a table to display the current and previous input and results
     */
    private final JStyleLabel[][] table = new JStyleLabel[6][5];

    /**
     * Center panel to display user inputs and results
     */
    private JPanel centerPanel;

    /**
     * North Panel to provide the two button concerned above
     */
    private JPanel northPanel;

    /**
     * Spacer to adjust components' sizes
     */
    private HorSpacer westHorSpacer;

    /**
     * Spacer to adjust components' sizes
     */
    private HorSpacer eastHorSpacer;

    /**
     * Spacer to adjust components' sizes
     */
    private VerSpacer southVerSpacer;

    /**
     * Constructor Method to initialise class itself
     */
    public GamePanel() {
        initialise();
    }

    /**
     * Initialisation of GamePanel instance
     */
    private void initialise() {
        resourceReader = new ResourceReader();
        inputProcessor = new InputProcessor(wordSize, resourceReader);
        wordleLogic = new WordleLogic(resourceReader.getWordle());
        actionListener  = new GamePanelActionListener(this);
        keyListener = new GamePanelKeyListener(this);
        this.addKeyListener(this);
        this.setLayout(new BorderLayout());

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 5, 5, 5));
        this.add(BorderLayout.CENTER, centerPanel);
        northPanel = new JPanel();
        this.add(BorderLayout.NORTH, northPanel);
        westHorSpacer = new HorSpacer(20);
        this.add(BorderLayout.WEST, westHorSpacer);
        eastHorSpacer = new HorSpacer(20);
        this.add(BorderLayout.EAST, eastHorSpacer);
        southVerSpacer = new VerSpacer(2, 20);
        this.add(BorderLayout.SOUTH, southVerSpacer);

        // backBtn Icon Settings
        initBackBtn();

        // resetBtn Icon Settings
        initResetBtn();
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                table[i][j] = new JStyleLabel();
                centerPanel.add(table[i][j]);
            }
        }
        this.setSize(600, 400);

        begin = System.currentTimeMillis();
    }

    /**
     * Initialise backBtn, backBtn icon settings
     */
    private void initBackBtn() {
        ImageIcon backBtnIcon = new ImageIcon();
        try {
            Image iconImg = ImageIO.read(new File("src/resources/img/Medico.png"));
            backBtnIcon.setImage(iconImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        backBtn.setIcon(backBtnIcon);
        backBtn.setPreferredSize(new Dimension(240, 80));
        northPanel.add(backBtn);
    }

    /**
     * Initialise resetBtn, resetBtn icon settings
     */
    private void initResetBtn() {
        ImageIcon resetBtnIcon = new ImageIcon();
        try {
            Image iconImg = ImageIO.read(new File("src/resources/img/Kikuchiyo.png"));
            resetBtnIcon.setImage(iconImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        resetBtn.setIcon(resetBtnIcon);
        resetBtn.setPreferredSize(new Dimension(240, 80));
        northPanel.add(resetBtn);
    }

    /**
     * Refresh current line JStyleLabels to display the current input
     */
    public void refresh() {
        char[] curInput = inputProcessor.getInput();
        for (int i = 0; i < 5; i++) {
            table[currentRow][i].setText(String.valueOf(curInput[i]));
        }
    }

    /**
     * Refresh the current line JStyleLabels to display the result colours.
     */
    public void lineRefresh() {
        Color[] curColour = wordleLogic.getColourRes();
        for (int i = 0; i < 5; i++) {
            table[currentRow][i].setBackground(curColour[i]);
        }
    }

    /**
     * Clear all temperate variables and initialise them.
     * When backBtn or resetBtn is pressed.
     */
    public void clear() {
        System.out.println("Start clear.");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                table[i][j].setText("");
                table[i][j].setBackground(Color.WHITE);
            }
        }
        setCurrentRow(0);
        setEnd(0);
        setBegin(System.currentTimeMillis());
        resourceReader.reset();
        inputProcessor.reset();
        wordleLogic.reset(resourceReader.getWordle());
        refresh();
        lineRefresh();
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
//        System.out.println("Released");
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     * Print the input message and deal with the input
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("\nPressed " + e.getKeyChar() + '\n' + this);
        keyListener.actionDependOnInput(inputProcessor.inputProcess(e.getKeyChar()));
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
//        System.out.println("Typed ");
    }

    public ResourceReader getResourceReader() {
        return resourceReader;
    }

    public void setResourceReader(ResourceReader resourceReader) {
        this.resourceReader = resourceReader;
    }

    public InputProcessor getInputProcessor() {
        return inputProcessor;
    }

    public void setInputProcessor(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    public WordleLogic getWordleLogic() {
        return wordleLogic;
    }

    public void setWordleLogic(WordleLogic wordleLogic) {
        this.wordleLogic = wordleLogic;
    }

    public JStyleButton getBackBtn() {
        return backBtn;
    }

    public JStyleButton getResetBtn() {
        return resetBtn;
    }

    public JStyleLabel[][] getTable() {
        return table;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JPanel getNorthPanel() {
        return northPanel;
    }

    public void setNorthPanel(JPanel northPanel) {
        this.northPanel = northPanel;
    }

    public HorSpacer getWestHorSpacer() {
        return westHorSpacer;
    }

    public void setWestHorSpacer(HorSpacer westHorSpacer) {
        this.westHorSpacer = westHorSpacer;
    }

    public HorSpacer getEastHorSpacer() {
        return eastHorSpacer;
    }

    public void setEastHorSpacer(HorSpacer eastHorSpacer) {
        this.eastHorSpacer = eastHorSpacer;
    }

    public VerSpacer getSouthVerSpacer() {
        return southVerSpacer;
    }

    public void setSouthVerSpacer(VerSpacer southVerSpacer) {
        this.southVerSpacer = southVerSpacer;
    }

    public int getWordSize() {
        return wordSize;
    }

    public void setWordSize(int wordSize) {
        this.wordSize = wordSize;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public long getBegin() {
        return begin;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

}
