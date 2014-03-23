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
 * @author Adam Rosenberg
 *****************************************************************/
public class AboutDialog extends JDialog {
	
	/** serial information. */
	private static final long serialVersionUID = 1L;

	/** String containing the about information. */
	private String about;
	
	/** File location for the about information. */
	private static final String ABOUT_FILE = "about.txt";
	
	/*****************************************************************
	 * Constructs a new AboutDialog.
	 * 
	 * @param parent
	 *            the JFrame that the dialog is related to.
	 * @throws FileNotFoundException
	 * 				throws FileNotFoundException if there is no
	 * 				about.txt file.
	 *****************************************************************/
	public AboutDialog(final JFrame parent) throws 
		FileNotFoundException {
		
		// call parent and create a 'modal' dialog
		super(parent, true);
		about = "";
		
		// get info from file for about
		Scanner s = new Scanner(new File(ABOUT_FILE));
			while (s.hasNextLine()) {
				about += s.nextLine() + "\n";
			}
		s.close();
		
		// create panel and add a text area to show about
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
