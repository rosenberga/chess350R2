package model;

import java.util.ArrayList;

/*****************************************************************
 * Objects implementing this interface represent the state of a chess game.
 * Notice that this interface is designed to maintain the game state only,
 * it does not provide any methods to control the flow of the game.
 *
 * @author Zachary Kurmas
 *****************************************************************/

public interface IChessModel {

   /*****************************************************************
    * Returns whether the game is complete.
    *
    * @param board the board to check for completion
    * @return true if complete, false otherwise.
    *****************************************************************/
   boolean isComplete(IChessBoard board);

   /*****************************************************************
    * Checks a board for the number of black pieces.
    *
    * @param board the board to check
    * @return number of black pieces
    *****************************************************************/
   int countBlacks(IChessBoard board);
   
   /*****************************************************************
    * Checks a board for the number of white pieces.
    *
    * @param board the board to check
    * @return number of black pieces
    *****************************************************************/
   int countWhites(IChessBoard board);
  
   /*****************************************************************
    * Returns whether the piece at location 
    * [move.fromRow, move.fromColumn] is allowed to move to 
    * location [move.fromRow, move.fromColumn].
    *
    * @param move a Move object describing the move to be made.
    * @param board the board to check the move on
    * @return true if the proposed move is valid, false otherwise.
    *****************************************************************/
   boolean isValidMove(Move move, IChessBoard board);

   /*****************************************************************
    * Moves the piece from location [move.fromRow, move.fromColumn] to
    * location [move.fromRow,move.fromColumn].
    *
    * @param move a Move object describing the move to be made.
    * @param board the current chess board
    *****************************************************************/
   void move(Move move, IChessBoard board);
   
   /*****************************************************************
    * Moves the piece from location [move.fromRow, move.fromColumn] to
    * location [move.fromRow,move.fromColumn].
    *
    * @param move a Move object describing the move to be made.
    * @param board the current chess board
    * @param cs a chessstack to save the board to
    *****************************************************************/
   void move(Move move, IChessBoard board, ChessStack cs);

   /*****************************************************************
    * Report whether the current player is in check.
    *
    * @param board the board to check for inCheck
    * @return true if the current player is in check, false otherwise.
    *****************************************************************/
   boolean inCheck(IChessBoard board);
   
   /*****************************************************************
    * Report whether the current player is in a check mate.
    *
    * @param board the board to check for checkmate
    * @return true if the current player is in a check mate, 
    * false otherwise.
    *****************************************************************/
   boolean inCheckMate(IChessBoard board);

   /*****************************************************************
    * Return the current player.
    *
    * @return the current player
    *****************************************************************/
   Player currentPlayer();
      
   /*****************************************************************
    * Return the ChessPiece object at location [row, column].
    *
    * @param board the board to get the piece from
    * @param row    the row (numbered 0 through numRows -1
    * @param column the column (numbered {@code 0} 
    * through {@code numColumns -1}
    * @return the {@code ChessPiece} object at location
    * {@code [row, column]}.
    *****************************************************************/
   IChessPiece pieceAt(IChessBoard board, int row, int column);
   
   /*****************************************************************
    * Checks if a move is en passant.
    * 
    * @param move the move to check for en passant
    * @return true if the move is en passant, else false
    *****************************************************************/
   	boolean enPassant(Move move);
   	
    /*****************************************************************
     * Checks if the board is in stalemate.
     * 
     * @param board the board to check for stalemate
     * @return true if the board is in stalemate, else false
     *****************************************************************/
   	boolean inStaleMate(IChessBoard board);

   	
    /*****************************************************************
     * Gets the King's position.
     * 
     * @param board the IChessBoard to check for king's position
     * @return an int[] of length 2 where int[0] is row, and
     * int[1] is column
     *****************************************************************/
   	int[] getKingsPos(IChessBoard board);

   	
    /*****************************************************************
     * Increases turn by an int.
     * 
     * @param i the amount to increase turns by
     *****************************************************************/
   	void setTurns(int i);
   	
    /*****************************************************************
     * Get a piece in the graveyard.
     * 
     * @param index of piece space
     * @return a black IChessPiece
     *****************************************************************/
   	IChessPiece getBlackGravePiece(int index);
   	
    /*****************************************************************
     * Get a piece in the graveyard.
     * 
     * @param index of piece space
     * @return a white IChessPiece
     *****************************************************************/
   	IChessPiece getWhiteGravePiece(int index);
   	
    /*****************************************************************
     * Get array of the graveyard.
     * 
     * @return an ArrayList of lost black pieces
     *****************************************************************/
   	ArrayList<IChessPiece> getBlackGrave();
   	
    /*****************************************************************
     * Get array of the graveyard.
     * 
     * @return an ArrayList of lost white pieces
     *****************************************************************/
   	ArrayList<IChessPiece> getWhiteGrave();

}
