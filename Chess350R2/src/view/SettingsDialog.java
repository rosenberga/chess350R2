/*
 * 
 */
package view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class SettingsDialog extends JDialog implements ActionListener {
	
	/** A JCheckBox to check to show legal moves. */
	private JCheckBox showLegal;
	
	/** The OK Button. */
	private JButton okButton;
	
	/** The IChessView to affect. */
	private IChessView view;
	
	/** Number of columns per grid. */
	private static final int COLS = 1;
	
	/** Number of rows in grid. */
	private static final int ROWS = 1;
	
	public SettingsDialog(final JFrame parent, final IChessView v) {
		
		// call parent and create a 'modal' dialog
		super(parent, true);
		this.view = v;
		
		okButton = new JButton("Ok");
		showLegal = new JCheckBox("Show Legal Moves");
		showLegal.setSelected(view.isShowLegal());
		
		// create new panel and add components to it
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(ROWS, COLS));
		panel.add(showLegal);
		panel.add(okButton);
		
		// add panels to dialog
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(okButton, BorderLayout.SOUTH);
		
		okButton.addActionListener(this);
		
		// set Dialog Settings
		setTitle("Chess350 Settings");
		pack();
		setVisible(true);
	}

	/******************************************************************
	 * Determines which button was clicked and responds to it.
	 * 
	 * @param e the action event that was just fired
	 ******************************************************************/
	public final void actionPerformed(final ActionEvent e) {
		
		// get the selected value for showLegal and set the
		// boolean showLegal in view to that
		view.setShowLegal(showLegal.isSelected());
		
		// dispose the dialog
		dispose();
	}
}
