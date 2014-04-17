package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/*****************************************************************
 * The view class.
 * 
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public class ChessView implements IChessView {

	/** Number of colors in the game. */
	private static final int NUM_COLORS = 2;

	/** Number of different types of pieces. */
	private static final int NUM_PIECES = 7;

	/** Owner position in piece info int[]. */
	private static final int OWNER = 0;

	/** Piece type position in piece info int[]. */
	private static final int TYPE = 1;

	/** Size of int[] with piece info. */
	private static final int PIECE_INFO = 2;

	/** Represents the row in a JButton array. */
	private static final int ROW = 0;

	/** Represents the column in a JButton array. */
	private static final int COL = 1;

	/** Picture of black space. */
	private static final String BLACK_SPACE = "blackSpace.png";
	/** Picture of white space. */
	private static final String WHITE_SPACE = "whiteSpace.png";
	/** Picture of legal space. */
	private static final String LEGAL = "legal.png";
	/** Picture of selected space. */
	private static final String SELECTED = "selected.png";
	/** Picture of empty space. */
	private static final String EMPTY = "piece-1-1.png";
	/** Picture of last space. */
	private static final String LAST = "last.png";
	/** Ratio to use when sizing the frame. */
	private static final double RATIO = 0.83;

	/** Number of columns. */
	private int cols;
	/** number of rows. */
	private int rows;

	/** Panel to put the board on. */
	private JPanel panel;
	/** Panel to put images on. */
	private JPanel piecePanel;
	/** Frame to put panel in. */
	private JFrame frame;
	/** Array of images. */
	private ImageIcon[][] pieceImages;
	/** Array of chess-square buttons. */
	private JButton[][] pieceLabels;

	/** The Menu Bar. */
	private JMenuBar menus;

	/** Game Option for Menu Bar. */
	private JMenu gameMenu;

	/** Help Option for Menu Bar. */
	private JMenu helpMenu;

	/** Settings Option for Menu Bar. */
	private JMenu settingsMenu;

	/** New Game Menu Item. */
	private JMenuItem newGameItem;

	/** Exit Menu Item. */
	private JMenuItem exitItem;

	/** About Menu Item. */
	private JMenuItem aboutItem;

	/** Setting Menu Item. */
	private JMenuItem settingItem;

	/** Undo Menu Item. */
	private JMenuItem undoItem;

	private JMenuItem musicItem;

	/** Shows if the square is a legal move. */
	private boolean showLegal;

	/** Shows the last move. */
	private boolean showLast;

	private static final String song = "ChopinNocturneOp.9Mo.2.mp3";
	
	

	/*****************************************************************
	 * Constructor for the View.
	 * 
	 * @param row
	 *            number of rows
	 * @param col
	 *            number of columns
	 *****************************************************************/
	public ChessView(final int row, final int col) {
		super();
		this.rows = row;
		this.cols = col;
		frame = new JFrame();
		newGame();
		showLegal = true;
		showLast = true;
	}

	/*****************************************************************
	 * Returns true if the square is a legal move.
	 * 
	 * @return showlegal
	 *****************************************************************/
	public final boolean isShowLegal() {
		return showLegal;
	}

	/*****************************************************************
	 * Sets the legality of a move.
	 * 
	 * @param showLegal1
	 *            the legality
	 *****************************************************************/
	public final void setShowLegal(final boolean showLegal) {
		this.showLegal = showLegal;
	}

	public boolean isShowLast() {
		return showLast;
	}

	public final void setShowLast(final boolean showLast) {
		this.showLast = showLast;
	}

	/*****************************************************************
	 * Create a new game.
	 * 
	 *****************************************************************/
	private void newGame() {
		frame.dispose();
		setUpBase();
	}

	/*****************************************************************
	 * Builds the GUI.
	 * 
	 *****************************************************************/
	private void setUpBase() {
		frame = new JFrame("Chess350");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setUpMenu();
		setUpPanels();
		frame.getContentPane().add(panel);
		frame.setJMenuBar(menus);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double height = screenSize.getHeight();
		int h = (int) (height * RATIO);

		frame.setPreferredSize(new Dimension(h, h));
		frame.pack();
		frame.setVisible(true);
	}

	/*****************************************************************
	 * Builds the Menu Bar.
	 *****************************************************************/
	private void setUpMenu() {

		menus = new JMenuBar();

		gameMenu = new JMenu("Game");
		settingsMenu = new JMenu("Settings");
		helpMenu = new JMenu("Help");

		newGameItem = new JMenuItem("New Game");
		exitItem = new JMenuItem("Exit");
		aboutItem = new JMenuItem("About");
		settingItem = new JMenuItem("Game Settings");
		undoItem = new JMenuItem("Undo Move");
		musicItem = new JMenuItem("Stop Music");

		gameMenu.add(newGameItem);
		gameMenu.add(exitItem);
		settingsMenu.add(settingItem);
		settingsMenu.add(musicItem);
		settingsMenu.add(undoItem);
		helpMenu.add(aboutItem);

		menus.add(gameMenu);
		menus.add(settingsMenu);
		menus.add(helpMenu);

	}

	/*****************************************************************
	 * Builds the panels.
	 *****************************************************************/
	private void setUpPanels() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		piecePanel = new JPanel();
		setUpPiecePanel();
		panel.add(piecePanel, BorderLayout.CENTER);
	}

	/*****************************************************************
	 * Sets the legality of a move.
	 *****************************************************************/
	private void setUpPiecePanel() {
		piecePanel.setLayout(new GridLayout(rows, cols));
		setUpImages();
	}

	/*****************************************************************
	 * Sets up the images.
	 *****************************************************************/
	private void setUpImages() {
		pieceImages = new ImageIcon[NUM_COLORS][NUM_PIECES];
		for (int i = 0; i < pieceImages.length; i++) {
			for (int j = 0; j < pieceImages[i].length; j++) {
				pieceImages[i][j] = new ImageIcon(
						"piece" + i + "" + j + ".png", "piece" + i + "" + j
								+ ".png");
			}
		}

		pieceLabels = new JButton[rows][cols];
		for (int i = 0; i < pieceLabels.length; i++) {
			for (int j = 0; j < pieceLabels[i].length; j++) {
				pieceLabels[i][j] = new JButton();
				piecePanel.add(pieceLabels[i][j]);
			}
		}

	}

	/*****************************************************************
	 * Adds an action listener to all clickable components.
	 * 
	 * @param e
	 *            the action listener to add
	 *****************************************************************/
	@Override
	public final void addButtonListeners(final ActionListener e) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				pieceLabels[i][j].addActionListener(e);
			}
		}

		newGameItem.addActionListener(e);
		exitItem.addActionListener(e);
		aboutItem.addActionListener(e);
		settingItem.addActionListener(e);
		undoItem.addActionListener(e);
		musicItem.addActionListener(e);
	}

	/*****************************************************************
	 * Returns an int[] containing information about a chess location.
	 * 
	 * @param e
	 *            the actionevent that was created by a click event
	 * @return an int[] containing information about a chess location
	 *****************************************************************/
	@Override
	public final int[] getData(final ActionEvent e) {
		int[] pos = new int[PIECE_INFO];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (e.getSource() == pieceLabels[i][j]) {
					pos[ROW] = i;
					pos[COL] = j;
					break;
				}
			}
		}
		return pos;
	}

	/*****************************************************************
	 * Sets a new icon for a given location.
	 * 
	 * @param row
	 *            the row to change
	 * @param col
	 *            the column to change
	 * @param pieceID
	 *            an int array with info on the what to change the location to
	 *****************************************************************/
	@Override
	public final void setData(final int row, final int col, final int[] pieceID) {
		ImageIcon icon;
		try {
			icon = pieceImages[pieceID[OWNER]][pieceID[TYPE]];
		} catch (Exception e) {

			// then one of ints in the array was -1
			// thus set the icon to show an empty space
			icon = new ImageIcon(EMPTY, EMPTY);
		}

		// else it was a piece, so determine if it should be a white or
		// black background
		if ((row % 2 == 0 && col % 2 == 0) || (row % 2 != 0 && col % 2 != 0)) {
			icon = new CustomIcon(WHITE_SPACE, icon);
		} else {
			icon = new CustomIcon(BLACK_SPACE, icon);
		}
		pieceLabels[row][col].setIcon(icon);
	}

	@Override
	public final void showMessage(final String m) {
		JOptionPane.showMessageDialog(frame, m);
	}

	/*****************************************************************
	 * Sets the legality of a move.
	 * 
	 * @param row
	 *            paint at this row
	 * @param col
	 *            paint at this column
	 * @param pieceID
	 *            array of places to paint
	 *****************************************************************/
	public final void paintLegalMove(final int row, final int col,
			final int[] pieceID) {

		// shows legal moves with a green background
		ImageIcon icon;
		try {
			icon = pieceImages[pieceID[OWNER]][pieceID[TYPE]];
		} catch (Exception e) {
			icon = new ImageIcon(EMPTY, EMPTY);
		}
		icon = new CustomIcon(LEGAL, icon);
		pieceLabels[row][col].setIcon(icon);
	}

	public final void paintLastMove(final int row, final int col,
			final int[] pieceID) {
		ImageIcon icon = pieceImages[pieceID[OWNER]][pieceID[TYPE]];
		icon = new CustomIcon(LAST, icon);
		pieceLabels[row][col].setIcon(icon);
	}

	public final void paintLastMove(final int row, final int col) {
		ImageIcon icon = new ImageIcon(EMPTY, EMPTY);
		icon = new CustomIcon(LAST, icon);
		pieceLabels[row][col].setIcon(icon);
	}

	/*****************************************************************
	 * Show the legality of a move.
	 * 
	 * @param row
	 *            paint at this row
	 * @param col
	 *            paint at this column
	 * @param pieceID
	 *            array of places to paint
	 *****************************************************************/
	public final void showSelected(final int row, final int col,
			final int[] pieceID) {

		// set the JButton to have a blue background
		ImageIcon icon = pieceImages[pieceID[OWNER]][pieceID[TYPE]];
		icon = new CustomIcon(SELECTED, icon);
		pieceLabels[row][col].setIcon(icon);
	}

	/*****************************************************************
	 * Returns the exits menu item.
	 * 
	 * @return a jmenuitem that closes the program
	 *****************************************************************/
	@Override
	public final JMenuItem getExitItem() {
		return exitItem;
	}

	/*****************************************************************
	 * Returns the about menu item.
	 * 
	 * @return a jmenuitem that opens an about dialog
	 *****************************************************************/
	@Override
	public final JMenuItem getAboutItem() {
		return aboutItem;
	}

	/*****************************************************************
	 * Returns the settings menu item.
	 * 
	 * @return a jmenuitem that opens a setting dialog
	 *****************************************************************/
	@Override
	public final JMenuItem getSettingItem() {
		return settingItem;
	}

	@Override
	public final JMenuItem getUndoItem() {
		return undoItem;
	}

	/*****************************************************************
	 * Returns the new game menu item.
	 * 
	 * @return a jmenuitem that starts a new game
	 *****************************************************************/
	@Override
	public final JMenuItem getNewGameItem() {
		return newGameItem;
	}

	/*****************************************************************
	 * Removes an action listener from all JButtons.
	 * 
	 * @param e
	 *            the action listener to remove
	 *****************************************************************/
	@Override
	public final void disable(final ActionListener e) {
		for (int i = 0; i < pieceLabels.length; i++) {
			for (int j = 0; j < pieceLabels[i].length; j++) {
				pieceLabels[i][j].removeActionListener(e);
			}
		}
	}

	/*****************************************************************
	 * Get rid of the frame.
	 *****************************************************************/
	public final void close() {
		frame.setVisible(false);
		frame.dispose();
	}

	/*****************************************************************
	 * Sets the legality of a move.
	 * 
	 * @return frame the user window
	 *****************************************************************/
	@Override
	public final JFrame getFrame() {
		return frame;
	}

	@Override
	public JMenuItem getMusicItem() {
		return musicItem;
	}

	@Override
	public void playMusic() {
	}

	@Override
	public void stopMusic() {
	}

}
