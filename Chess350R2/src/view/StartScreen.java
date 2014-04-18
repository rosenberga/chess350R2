package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*****************************************************************
 * Chess350 start screen where players can load or start a new game.
 *
 * @version 1.0
 * @author Adam Rosenberg
 *****************************************************************/
public class StartScreen {

    /** The JFrame. */
    private JFrame frame;
    /** The first panel to show. */
    private JPanel firstPanel;
    /** The second panel to show. */
    private JPanel secondPanel;
    /** Button to start one player game. */
    private JButton onePlayer;
    /** Button to start two player game. */
    private JButton twoPlayer;
    /** First computer style of play. */
    private JButton styleOne;
    /** Second computer style of play. */
    private JButton styleTwo;
    /** Third computer style of play. */
    private JButton styleThree;
    /** Loads last saved game. */
    private JButton loadGame;
    /** Font size. */
    private static final int FONT_SIZE = 20;
    /** Dimension 300. */
    private static final int DIM = 300;
    /** Magic number c1. */
    private static final int C1 = 204;
    /** Magic number c2. */
    private static final int C2 = 229;
    /** Magic number c3. */
    private static final int C3 = 255;

    /*****************************************************************
     * Constructor for the class.
     * 
     *****************************************************************/
    public StartScreen() {
        frame = new JFrame("Chess350");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstPanel = new JPanel();
        secondPanel = new JPanel();
        onePlayer = new JButton("Single Player Game");
        twoPlayer = new JButton("Two Player Game");
        styleOne = new JButton("Random CPU");
        styleTwo = new JButton("Offensive CPU");
        styleThree = new JButton("Defensive CPU");
        loadGame = new JButton("Load Last Saved Game");

        firstPanel.setLayout(new GridLayout(0, 1));
        firstPanel.add(onePlayer);
        firstPanel.add(twoPlayer);
        firstPanel.add(loadGame);

        secondPanel.setLayout(new GridLayout(0, 1));
        secondPanel.add(styleOne);
        secondPanel.add(styleTwo);
        secondPanel.add(styleThree);

        frame.getContentPane().add(firstPanel);
        frame.pack();
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height
                / 2 - frame.getSize().height / 2);
        frame.setResizable(false);
        frame.setSize(DIM, DIM);

        changeButtonLook(loadGame);
        changeButtonLook(onePlayer);
        changeButtonLook(twoPlayer);
    }

    /*****************************************************************
     * Add an action listener to componen objects.
     * 
     * @param e the actionlister to add
     *****************************************************************/
    public final void addActionListeners(final ActionListener e) {
        onePlayer.addActionListener(e);
        twoPlayer.addActionListener(e);
        styleOne.addActionListener(e);
        styleTwo.addActionListener(e);
        styleThree.addActionListener(e);
        loadGame.addActionListener(e);
    }

    /*****************************************************************
     * Edits the start screen window.
     * 
     *****************************************************************/
    public final void changePanels() {
        frame.getContentPane().remove(firstPanel);
        frame.getContentPane().add(secondPanel);
        changeButtonLook(styleOne);
        changeButtonLook(styleTwo);
        changeButtonLook(styleThree);
        frame.pack();
        frame.setSize(DIM, DIM);
        frame.setResizable(false);
    }

    /*****************************************************************
     * Returns the button for having one player.
     * 
     * @return the one player button
     *****************************************************************/
    public final JButton getOnePlayer() {
        return onePlayer;
    }

    /*****************************************************************
     * Returns the button for having two player.
     * 
     * @return the two player button
     *****************************************************************/
    public final JButton getTwoPlayer() {
        return twoPlayer;
    }

    /*****************************************************************
     * Returns the button for having easy computer.
     * 
     * @return the easy conputer button
     *****************************************************************/
    public final JButton getStyleOne() {
        return styleOne;
    }

    /*****************************************************************
     * Returns the button for having medium difficulty computer.
     * 
     * @return the medium conputer button
     *****************************************************************/
    public final JButton getStyleTwo() {
        return styleTwo;
    }

    /*****************************************************************
     * Returns the button for having hard computer.
     * 
     * @return the hard conputer button
     *****************************************************************/
    public final JButton getStyleThree() {
        return styleThree;
    }

    /*****************************************************************
     * Returns the button for loading a saved game.
     * 
     * @return the loading button
     *****************************************************************/
    public final JButton getLoadGame() {
        return loadGame;
    }

    /*****************************************************************
     * Makes the window visible.
     * 
     * @param visible true if visible to user
     *****************************************************************/
    public final void setVisible(final boolean visible) {
        frame.setVisible(visible);
    }

    /*****************************************************************
     * Customize button attributes.
     * 
     * @param b the button that needs changing
     *****************************************************************/
    private void changeButtonLook(final JButton b) {
        b.setBackground(new Color(C1, C2, C3));
        b.setForeground(Color.black);
        b.setFocusPainted(false);
        b.setFont(new Font(Font.SANS_SERIF, Font.BOLD, FONT_SIZE));
    }

}
