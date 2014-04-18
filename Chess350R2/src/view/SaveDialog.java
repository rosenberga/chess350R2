package view;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/*****************************************************************
 * A Dialog to show about information for a project.
 *
 * @version 1.0
 * @author Chris Mahoney
 *****************************************************************/
public class SaveDialog extends JDialog {

	/** serial information. */
	private static final long serialVersionUID = 1L;

	/** String containing the about information. */
	private String save;

	/** File location for the about information. */
	private static final String SAVE_FILE = "save.txt";

	/*****************************************************************
	 * Constructs a new AboutDialog.
	 *
	 * @param parent
	 *            the JFrame that the dialog is related to.
	 * @throws FileNotFoundException
	 * 				throws FileNotFoundException if there is
	 * 				no about.txt file.
	 *****************************************************************/
	public SaveDialog(final JFrame parent) throws
		FileNotFoundException {

		// call parent and create a 'modal' dialog
		super(parent, true);
		save = "";

		// get info from file for about
		Scanner s = new Scanner(new File(SAVE_FILE));
			while (s.hasNextLine()) {
				save += s.nextLine() + "\n";
			}
		s.close();

		// create panel and add a text area to show about
		JPanel panel = new JPanel();
		JTextArea saveText = new JTextArea(save);
		saveText.setWrapStyleWord(true);
		saveText.setEditable(false);
		panel.add(saveText);
		getContentPane().add(panel, BorderLayout.CENTER);

		// set Dialog Settings
		setTitle("Game saved");
		pack();
		setVisible(true);
	}
}
