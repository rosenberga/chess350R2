package view;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public interface IChessView {

		void close();
		void addButtonListeners(ActionListener e);
		int[] getData(ActionEvent e); // gives four coordinates of move
		void setData(int row, int col, int[] pieceID); // return board
		void showMessage(String m); // error message?
		void paintLegalMove(int row, int col, int[] pieceID);
		JMenu getSettingsMenu();
		JMenuItem getAboutItem();
		JMenuItem getSettingItem();
	        JMenuItem getNewGameItem();
	        JMenuItem getExitItem();
			void showSelected(int i, int j, int[] sendPiece);
			void disable(ActionListener actionListener);
	   void setShowLegal(boolean b);
	   boolean isShowLegal();
	JFrame getFrame();

}
