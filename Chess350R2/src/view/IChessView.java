package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import model.IChessPiece;

/*****************************************************************
 * The Interface for the view class.
 * 
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public interface IChessView {

	/** Close the GUI. */
	void close();
	
	/***************************************************************** 
	 * Add listeners to materials and implement actionPerformed. 
	 * 
	 * @param e the actionlistener variable
	  *****************************************************************/
	void addButtonListeners(ActionListener e);
	
	/*****************************************************************
	 * Retrieve coordinate data from the board. 
	 * 
	 * @param e the actionevent variable
	 * @return int[]
	  *****************************************************************/
	int[] getData(ActionEvent e); // gives four coordinates of move
	
	/*****************************************************************
	 *  Set the coordinates into the board. 
	 *
	 * @param row the new row
	 * @param col the new column
	 * @param pieceID all the pieces
	  *****************************************************************/
	void setData(int row, int col, int[] pieceID);
	
	/*****************************************************************
	 * Mark the squares to show what is legal. 
	 * 
	 * @param row the new row
	 * @param col the new column
	 * @param pieceID all the pieces
	 *****************************************************************/
	void paintLegalMove(int row, int col, int[] pieceID);
	
	/*****************************************************************
	 * Returns the JMenuItem About field of the view.
	 * 
	 * @return JMenuItem
	 *****************************************************************/
	JMenuItem getAboutItem();
	
	/*****************************************************************
	 * Returns the JMenuItem Settings field of the view.
	 * 
	 * @return JMenuItem
	 *****************************************************************/
	JMenuItem getSettingItem();
	
	/*****************************************************************
	 * Returns the JMenuItem New Game field of the view.
	 * 
	 * @return JMenuItem
	 *****************************************************************/
	JMenuItem getNewGameItem();
	
	/*****************************************************************
	 * Returns the JMenuItem Exit field of the view.
	 * 
	 * @return JMenuItem
	 *****************************************************************/
	JMenuItem getExitItem();
	
	JMenuItem getUndoItem();
	
	/*****************************************************************
	 * Shows selected piece's square.
	 * 
	 * @param i coordinate x
	 * @param j coordinate y
	 * @param sendPiece the array of pieces to send
	 *****************************************************************/
	void showSelected(int i, int j, int[] sendPiece);
	
	/*****************************************************************
	 * Disables the board.
	 * 
	 * @param actionListener disables all squares
	 *****************************************************************/
	void disable(ActionListener actionListener);
	
	/*****************************************************************
	 * The squares will show legal moves if true.
	 * 
	 * @param b used to set legality
	 *****************************************************************/
	void setShowLegal(boolean b);
	
	/*****************************************************************
	 * Returns the legality of the move to the sqaure.
	 * 
	 * @return boolean
	 *****************************************************************/
	boolean isShowLegal();
	
	/*****************************************************************
	 * Returns the JFrame field of the view.
	 * 
	 * @return JFrame
	 *****************************************************************/
	JFrame getFrame();

	void paintLastMove(int fromRow, int fromColumn, int[] sendPiece);

	boolean isShowLast();

	void setShowLast(boolean selected);

	void paintLastMove(int fromRow, int fromColumn);

	JMenuItem getMusicItem();

	void playMusic();

	void stopMusic();
	
	/*****************************************************************
	 * Show the message of the game. 
	 * 
	 * @param message the text to display
	 * @param status the game status
	 *****************************************************************/
	void showMessage(String status, String message);

	JMenuItem getSaveItem();

	JMenuItem getLoadItem();
	void updateWhiteGrave(int index, int[] pieceID);
	
	void updateBlackGrave(int index, int[] pieceID);
	
    void clearGraves();
}
