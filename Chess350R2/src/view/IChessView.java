package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

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
     * @param e
     *            the actionlistener variable
     *****************************************************************/
    void addButtonListeners(ActionListener e);

    /*****************************************************************
     * Retrieve coordinate data from the board.
     * 
     * @param e
     *            the actionevent variable
     * @return int[]
     *****************************************************************/
    int[] getData(ActionEvent e); // gives four coordinates of move

    /*****************************************************************
     * Set the coordinates into the board.
     * 
     * @param row
     *            the new row
     * @param col
     *            the new column
     * @param pieceID
     *            all the pieces
     *****************************************************************/
    void setData(int row, int col, int[] pieceID);

    /*****************************************************************
     * Mark the squares to show what is legal.
     * 
     * @param row
     *            the new row
     * @param col
     *            the new column
     * @param pieceID
     *            all the pieces
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

    /*****************************************************************
     * Returns the undo item.
     * 
     * @return the undo item
     *****************************************************************/
    JMenuItem getUndoItem();

    /*****************************************************************
     * Shows selected piece's square.
     * 
     * @param i
     *            coordinate x
     * @param j
     *            coordinate y
     * @param sendPiece
     *            the array of pieces to send
     *****************************************************************/
    void showSelected(int i, int j, int[] sendPiece);

    /*****************************************************************
     * The squares will show legal moves if true.
     * 
     * @param b
     *            used to set legality
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

    /*****************************************************************
     * Point the last moved.
     * 
     * @param fromRow
     *            row we moved from.
     * 
     * @param fromColumn
     *            column we moved from.
     * 
     * @param sendPiece
     *            piece to send.
     *****************************************************************/
    void paintLastMove(int fromRow, int fromColumn, int[] sendPiece);

    /*****************************************************************
	 * Checks if the player wants to show last move.
	 * 
	 * @return if the player wants to show the last move
	 *****************************************************************/
    boolean isShowLast();

    /*****************************************************************
     * Sets show last to a boolean.
     * 
     * @param selected the boolean value to set showLast to
     *****************************************************************/
    void setShowLast(boolean selected);

    /*****************************************************************
     * Paints the last move.
     * 
     * @param fromRow the row to paint
     * @param fromColumn the col to paint
     *****************************************************************/
    void paintLastMove(int fromRow, int fromColumn);

    /*****************************************************************
     * Gets the music menu item.
     * 
     * @return the music menu item
     *****************************************************************/
    JMenuItem getMusicItem();

    /*****************************************************************
     * Starts to play music.
     *****************************************************************/
    void playMusic();

    /*****************************************************************
     * Stops the playing music.
     *****************************************************************/
    void stopMusic();

    /*****************************************************************
     * Show the message of the game.
     * 
     * @param message
     *            the text to display
     * @param status
     *            the game status
     *****************************************************************/
    void showMessage(String status, String message);

    /*****************************************************************
     * Returns the save menu item.
     * 
     * @return the save menu item
     *****************************************************************/
    JMenuItem getSaveItem();

    /*****************************************************************
     * Returns th load menu item.
     * 
     * @return get the load menu item
     *****************************************************************/
    JMenuItem getLoadItem();

    /*****************************************************************
     * Adds a new Icon to the White Grave.
     * 
     * @param index to add to
     * @param pieceID info about the piece to add
     *****************************************************************/
    void updateWhiteGrave(int index, int[] pieceID);

    /*****************************************************************
     * Adds a new Icon to the Black Grave.
     * 
     * @param index to add to
     * @param pieceID info about the piece to add
     *****************************************************************/
    void updateBlackGrave(int index, int[] pieceID);

    /*****************************************************************
     * Removes all icons form graves.
     *****************************************************************/
    void clearGraves();
}
