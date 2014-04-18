package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartScreen {
	private JFrame frame;
	private JPanel firstPanel;
	private JPanel secondPanel;
	private JButton onePlayer;
	private JButton twoPlayer;
	private JButton styleOne;
	private JButton styleTwo;
	private JButton styleThree;
	private JButton loadGame;
	
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
		
		firstPanel.setLayout(new GridLayout(0,1));
		firstPanel.add(onePlayer);
		firstPanel.add(twoPlayer);
		firstPanel.add(loadGame);
		
		secondPanel.setLayout(new GridLayout(0,1));
		secondPanel.add(styleOne);
		secondPanel.add(styleTwo);
		secondPanel.add(styleThree);
		
		frame.getContentPane().add(firstPanel);
		frame.pack();
		frame.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setResizable(false);
		frame.setSize(300,300);
		
		changeButtonLook(loadGame);
		changeButtonLook(onePlayer);
		changeButtonLook(twoPlayer);
	}
	
	public final void addActionListeners(final ActionListener e){
		onePlayer.addActionListener(e);
		twoPlayer.addActionListener(e);
		styleOne.addActionListener(e);
		styleTwo.addActionListener(e);
		styleThree.addActionListener(e);
		loadGame.addActionListener(e);
	}
	
	public final void changePanels(){
		frame.getContentPane().remove(firstPanel);
		frame.getContentPane().add(secondPanel);
		changeButtonLook(styleOne);
		changeButtonLook(styleTwo);
		changeButtonLook(styleThree);
		frame.pack();
		frame.setSize(300,300);
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
	
	public void setVisible(final boolean visable){
		frame.setVisible(visable);
	}
	
	private void changeButtonLook(JButton b){
        b.setBackground(new Color(204,229,255));
        b.setForeground(Color.black);
        b.setFocusPainted(false);
        b.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 20));
	}
	
}
