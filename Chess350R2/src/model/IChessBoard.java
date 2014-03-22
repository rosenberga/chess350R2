package model;

/*****************************************************************
 * This interface provides a uniform view of a Chess board 
 * to the IChessPiece interface.
 *
 * @author Zachary Kurmas
 *****************************************************************/

public interface IChessBoard {

   /*****************************************************************
    * the number of rows on the chess board 
    * (a standard game has 8 rows).
    *
    * @return the number of rows on the chess board.
    *****************************************************************/
   int numRows();

   /*****************************************************************
    * the number of columns on the chess board 
    * (a standard game has 8 columns).
    *
    * @return the number of columns on the chess board
    *****************************************************************/
   int numColumns();

   /*****************************************************************
    * Return the ChessPiece object at location [row, column].
    *
    * @param row    the row (numbered 0 through numRows -1)
    * @param column the column (numbered 0 through numColumns -1)
    * @return the ChessPiece object at location [row, column].
    *****************************************************************/
   IChessPiece pieceAt(int row, int column);
   
    /*****************************************************************
    * Make the specified move.  The move must be validated 
    * (i.e., this method will not verify that the move is legal).
    *
    * @param move the move to be made.
    *****************************************************************/
   void move(Move move);

   /*****************************************************************
    * Place piece at [row, column].
    *
    * @param piece  the piece
    * @param row    the row
    * @param column the column
    *****************************************************************/
   void set(IChessPiece piece, int row, int column);


   /*****************************************************************
    * Remove the piece from [row, column].
    *
    * @param row    the row
    * @param column the column
    *****************************************************************/
   void unset(int row, int column);

   
   /*****************************************************************
    * Gets the current board for the game
    * .
    * @return board the IChessPiece[][] of chess pieces that makes
    * 		up the chess board
    *****************************************************************/
    IChessPiece[][] getBoard();
    
    
    /*****************************************************************
     * Sets the board to another given board.
     * 
     * @param board an IChessPiece[][] to set this.board to
     *****************************************************************/
    void setBoard(IChessPiece[][] board);
    
    
    /*****************************************************************
     * Clears the board of any and all chess pieces.
     *****************************************************************/
    void clearBoard();

    
    /*****************************************************************
     * Copies a board and returns the copy.
     * 
     * @return a copy of board
     *****************************************************************/
    IChessPiece[][] copyBoard();
}
