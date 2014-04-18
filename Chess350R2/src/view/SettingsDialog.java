/*
 * 
 */
package view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*****************************************************************
 * A SettingsDialog where in game settings can be affected.
 *
 * @version 1.0
 * @author Adam Rosenberg
 *****************************************************************/
public class SettingsDialog extends JDialog implements ActionListener {

	/** A SerialId. */
	private static final long serialVersionUID = 171985604033309420L;

	/** A JCheckBox to check to show legal moves. */
	private JCheckBox showLegal;

	/** Showing last move. */
	private JCheckBox showLast;

	/** The OK Button. */
	private JButton okButton;

	/** The IChessView to affect. */
	private IChessView view;

	/** Number of columns per grid. */
	private static final int COLS = 1;

	/** Number of rows in grid. */
	private static final int ROWS = 0;

    /*****************************************************************
     * Constructs a new SettingsDisalog.
     *
     * @param parent
     * 			the JFrame that owns this JDialog
     * @param v
     * 			an IChessView that this JDialog affects
     *****************************************************************/
	public SettingsDialog(final JFrame parent, final IChessView v) {

		// call parent and create a 'modal' dialog
		super(parent, true);
		super.setLocationRelativeTo(parent);
		this.view = v;

		okButton = new JButton("Ok");
		showLegal = new JCheckBox("Show Legal Moves");
		showLegal.setSelected(view.isShowLegal());
		showLast = new JCheckBox("Show Last Moves");
		showLast.setSelected(view.isShowLast());

		// create new panel and add components to it
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(ROWS, COLS));
		panel.add(showLegal);
		panel.add(showLast);

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
	@Override
	public final void actionPerformed(final ActionEvent e) {

		// get the selected value for showLegal and set the
		// boolean showLegal in view to that
		view.setShowLegal(showLegal.isSelected());
		view.setShowLast(showLast.isSelected());
		// dispose the dialog
		dispose();
	}
}
