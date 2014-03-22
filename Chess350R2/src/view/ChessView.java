package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class ChessView implements IChessView{
	private static final int NUM_COLORS = 2;
	private static final int NUM_PIECES = 7;
	private static final int OWNER = 0;
	private static final int TYPE = 1;
	private static final int PIECE_INFO = 2;
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final String BLACK_SPACE = "blackSpace.png";
	private static final String WHITE_SPACE = "whiteSpace.png";
	private static final String LEGAL = "legal.png";
	private static final String SELECTED = "selected.png";
	private static final String EMPTY = "piece-1-1.png";
	
	private int cols;
	private int rows;
	
	private JPanel panel;
	private JPanel piecePanel;
	private JFrame frame;
	private ImageIcon[][] pieceImages;
	private JButton[][] pieceLabels;
	
	/** The Menu Bar */
    private JMenuBar menus;
    
   /** Game Option for Menu Bar */
    private JMenu gameMenu;
    
   /** Help Option for Menu Bar */
    private JMenu helpMenu;
    
   /** Settings Option for Menu Bar */
    private JMenu settingsMenu;
    
   /** New Game Menu Item */
    private JMenuItem newGameItem;
    
   /** Exit Menu Item */
    private JMenuItem exitItem; 
    
   /** About Menu Item */
    private JMenuItem aboutItem;
    
    /** Setting Menu Item */
    private JMenuItem settingItem;

	private boolean showLegal;
	
	public ChessView(int rows, int cols){
		super();
		this.rows = rows;
		this.cols = cols;
		frame = new JFrame();
		newGame();
		showLegal = true;
	}
	   public boolean isShowLegal() {
			return showLegal;
		}

		public void setShowLegal(boolean showLegal) {
			this.showLegal = showLegal;
		}
	private void newGame(){
		frame.dispose();
		setUpBase();
	}
	
	private void setUpBase(){
		frame = new JFrame("Chess350");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setUpMenu();
		setUpPanels();
		frame.getContentPane().add(panel);
		frame.setJMenuBar(menus);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double height = screenSize.getHeight();
		int h = (int) (height * .83);
		
		frame.setPreferredSize(new Dimension(h, h));
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private void setUpMenu(){
		
		menus = new JMenuBar();
		
	    gameMenu = new JMenu("Game");
	    settingsMenu = new JMenu("Settings");
	    helpMenu = new JMenu("Help");
	    
	    newGameItem = new JMenuItem("New Game");
        exitItem = new JMenuItem("Exit"); 
        aboutItem = new JMenuItem("About");
	    settingItem = new JMenuItem("Game Settings");
	    
	    gameMenu.add(newGameItem);
	    gameMenu.add(exitItem);
	    settingsMenu.add(settingItem);
	    helpMenu.add(aboutItem);
	    
	    menus.add(gameMenu);
	    menus.add(settingsMenu);
	    menus.add(helpMenu);
		
	}
	
	private void setUpPanels(){
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		piecePanel = new JPanel();
		setUpPiecePanel();
		panel.add(piecePanel, BorderLayout.CENTER);
	}
	
	private void setUpPiecePanel(){
		piecePanel.setLayout(new GridLayout(rows,cols));
		setUpImages();
	}
	
	private void setUpImages(){
		pieceImages = new ImageIcon[NUM_COLORS][NUM_PIECES];
		for(int i = 0; i < pieceImages.length; i++){
			for(int j = 0; j < pieceImages[i].length; j++){
				pieceImages[i][j] = new ImageIcon("piece"+i+""+j+".png","piece"+i+""+j+".png");
			}
		}
		
		pieceLabels = new JButton[rows][cols];
		for(int i = 0; i < pieceLabels.length; i++){
			for(int j = 0; j < pieceLabels[i].length; j++){
				pieceLabels[i][j] = new JButton();
				piecePanel.add(pieceLabels[i][j]);
			}
		}
		
	}

	@Override
	public void addButtonListeners(ActionListener e) {
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				pieceLabels[i][j].addActionListener(e);
			}
		}
		
	    newGameItem.addActionListener(e);
        exitItem.addActionListener(e);
        aboutItem.addActionListener(e);
	    settingItem.addActionListener(e);
	}

	@Override
	public int[] getData(ActionEvent e) {
		int[] pos = new int[PIECE_INFO];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(e.getSource() == pieceLabels[i][j]){
					pos[ROW] = i;
					pos[COL] = j;
					break;
				}
			}
		}
		return pos;
	}

	@Override
	public void setData(final int row, final int col, final int[] pieceID) {
		ImageIcon icon;
		try{
		icon = pieceImages[pieceID[OWNER]][pieceID[TYPE]];
		} catch (Exception e){
			icon = new ImageIcon(EMPTY, EMPTY);
		}
		if((row % 2 == 0 && col % 2 == 0) ||(row % 2 != 0 && col % 2 != 0)){
			icon = new CustomIcon(WHITE_SPACE, icon);
		} else {
			icon = new CustomIcon(BLACK_SPACE,icon);
		}
		pieceLabels[row][col].setIcon(icon);
	}

	@Override
	public void showMessage(String m) {
		JOptionPane.showMessageDialog(frame, m);
	}
	
	public void paintLegalMove(int row, int col,int[] pieceID){
		ImageIcon icon;
		try{
		icon = pieceImages[pieceID[OWNER]][pieceID[TYPE]];
		} catch(Exception e){
			icon = new ImageIcon(EMPTY, EMPTY);
		}
		icon = new CustomIcon(LEGAL, icon);
		pieceLabels[row][col].setIcon(icon);
	}

	public void showSelected(int row, int col, int[] pieceID) {
		ImageIcon icon = pieceImages[pieceID[OWNER]][pieceID[TYPE]];
		icon = new CustomIcon(SELECTED, icon);
		pieceLabels[row][col].setIcon(icon);
	}

	@Override
	public JMenu getSettingsMenu() {
		return settingsMenu;
	}

	@Override
	public JMenuItem getExitItem() {
		return exitItem;
	}

	@Override
	public JMenuItem getAboutItem() {
		return aboutItem;
	}

	@Override
	public JMenuItem getSettingItem() {
		return settingItem;
	}

	@Override
	public JMenuItem getNewGameItem() {
		return newGameItem;
	}

	@Override
	public void disable(ActionListener e) {
		for(int i = 0; i < pieceLabels.length; i++){
			for(int j = 0; j < pieceLabels[i].length; j++){
				pieceLabels[i][j].removeActionListener(e);
			}
		}
	}
	
	public void close() {
		frame.setVisible(false);
		frame.dispose();
	}

	@Override
	public JFrame getFrame() {
		return frame;
	}

}
