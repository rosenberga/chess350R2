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

	/** Our frame. */
	private JFrame frame;
	/** Our. */
	private JPanel firstPanel;
	/** Number of colors in the game. */
	private JPanel secondPanel;
	/** Number of colors in the game. */
	private JButton onePlayer;
	/** Number of colors in the game. */
	private JButton twoPlayer;
	/** Number of colors in the game. */
	private JButton styleOne;
	/** Number of colors in the game. */
	private JButton styleTwo;
	/** Number of colors in the game. */
	private JButton styleThree;
	/** Number of colors in the game. */
	private JButton loadGame;
	
	private static final int THREEH = 300;
	
	public StartScreen(){
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
		frame.setLocation(dim.width / 2 - frame.
				getSize().width / 2,
				dim.height / 2 - frame.getSize().height / 2);
		frame.setResizable(false);
		frame.setSize(THREEH, THREEH);

		changeButtonLook(loadGame);
		changeButtonLook(onePlayer);
		changeButtonLook(twoPlayer);
	}

	public final void addActionListeners(final ActionListener e) {
		onePlayer.addActionListener(e);
		twoPlayer.addActionListener(e);
		styleOne.addActionListener(e);
		styleTwo.addActionListener(e);
		styleThree.addActionListener(e);
		loadGame.addActionListener(e);
	}

	public final void changePanels() {
		frame.getContentPane().remove(firstPanel);
		frame.getContentPane().add(secondPanel);
		changeButtonLook(styleOne);
		changeButtonLook(styleTwo);
		changeButtonLook(styleThree);
		frame.pack();
		frame.setSize(300,300);
		frame.setResizable(false);
	}
=======
>>>>>>> branch 'master' of https://github.com/rosenberga/Chess350R2.git

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
    private static final int FONT_SIZE = 20;
    private static final int DIM = 300;
    private static final int C1 = 204;
    private static final int C2 = 229;
    private static final int C3 = 255;

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

<<<<<<< HEAD
	public JButton getStyleThree() {
		return styleThree;
	}

	public JButton getLoadGame() {
		return loadGame;
	}

	public void setVisible(final boolean visable){
		frame.setVisible(visable);
	}

	private void changeButtonLook(JButton b){
        b.setBackground(new Color(204,229,255));
=======
        frame.getContentPane().add(firstPanel);
        frame.pack();
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height
                / 2 - frame.getSize().height / 2);
        frame.setResizable(false);
        frame.setSize(300, 300);

        changeButtonLook(loadGame);
        changeButtonLook(onePlayer);
        changeButtonLook(twoPlayer);
    }

    public final void addActionListeners(final ActionListener e) {
        onePlayer.addActionListener(e);
        twoPlayer.addActionListener(e);
        styleOne.addActionListener(e);
        styleTwo.addActionListener(e);
        styleThree.addActionListener(e);
        loadGame.addActionListener(e);
    }

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

    public JButton getOnePlayer() {
        return onePlayer;
    }

    public JButton getTwoPlayer() {
        return twoPlayer;
    }

    public JButton getStyleOne() {
        return styleOne;
    }

    public JButton getStyleTwo() {
        return styleTwo;
    }

    public JButton getStyleThree() {
        return styleThree;
    }

    public JButton getLoadGame() {
        return loadGame;
    }

    public void setVisible(final boolean visable) {
        frame.setVisible(visable);
    }

    private void changeButtonLook(JButton b) {
        b.setBackground(new Color(C1, C2, C3));

        b.setForeground(Color.black);
        b.setFocusPainted(false);



        b.setFont(new Font(Font.SANS_SERIF, Font.BOLD, FONT_SIZE));
    }


}
