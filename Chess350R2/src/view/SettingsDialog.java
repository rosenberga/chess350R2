/*
 * 
 */
package view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class SettingsDialog extends JDialog implements ActionListener{
	
	private JCheckBox showLegal;
	
	/** The OK Button */
	private JButton okButton;
	
	private IChessView view;
	
	public SettingsDialog(JFrame parent,IChessView view) {
		
		// call parent and create a 'modal' dialog
		super(parent, true);
		this.view = view;
		
		okButton = new JButton("Ok");
		showLegal = new JCheckBox("Show Legal Moves");
		showLegal.setSelected(view.isShowLegal());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(showLegal);
		panel.add(okButton);
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(okButton, BorderLayout.SOUTH);
		okButton.addActionListener(this);
		// set Dialog Settings
		setTitle("Chess350 Settings");
		setSize(100,100);
		setVisible(true);
	}

	/******************************************************************
	 * Determines which button was clicked and responds to it
	 * 
	 * @param e the action event that was just fired
	 ******************************************************************/
	public final void actionPerformed(final ActionEvent e) {
		if (showLegal.isSelected()) {
			view.setShowLegal(true);
		} else {
			view.setShowLegal(false);
		}
		dispose();
	}
}
