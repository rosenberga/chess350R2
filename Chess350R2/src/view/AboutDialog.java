package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class AboutDialog extends JDialog {
	private String about;
	private static final String aboutFile = "about.txt";
	public AboutDialog(JFrame parent) throws FileNotFoundException {
		
		// call parent and create a 'modal' dialog
		super(parent, true);
		about = "";
		Scanner s = new Scanner(new File(aboutFile));
			while (s.hasNextLine()){
				about += s.nextLine()+"\n";
			}
		
		JPanel panel = new JPanel();
		JTextArea aboutText = new JTextArea(about);
		aboutText.setWrapStyleWord(true);
		aboutText.setEditable(false);
		panel.add(aboutText);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		// set Dialog Settings
		setTitle("About Chess350");
		pack();
		setVisible(true);
	}
}
