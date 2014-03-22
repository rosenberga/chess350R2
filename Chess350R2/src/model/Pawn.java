package model;

/*****************************************************************
 * A Pawn in a game of chess.
 * 
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public final class Pawn extends ChessPiece {

	/** The direction the pawn will be moving. */
	private int direction;

	/*****************************************************************
	 * Constructs a new Pawn object.
	 * 
	 * @param color
	 *            the player that owns this piece
	 *****************************************************************/
	protected Pawn(final Player color) {
		super(color);
		if (this.player() == Player.WHITE) {
			direction = -1;
		} else {
			direction = 1;
		}
	}

	
	/*****************************************************************
	 * Returns the type of ChessPiece it is.
	 * 
	 * @return the String value of the name of the ChessPiece
	 *****************************************************************/
	@Override
	public String type() {
		return "Pawn";
	}

	
	/*****************************************************************
	 * Checks if a move is valid for the chess piece.
	 * 
	 * @param move
	 *            the move to check for validity
	 * @param board
	 *            the current chess board
	 * @param model
	 *            the chess model to use
	 * @return true if the move is valid, else false
	 *****************************************************************/
	@Override
	public boolean isValidMove(final Move move, 
			final IChessBoard board, final IChessModel model) {
		if (!super.isValidMove(move, board, model)) {
			return false;
		}
		
		// check if the pawn is going backwards
		if ((move.getToRow() - move.getFromRow()) * direction < 0) {
			return false;
		}
		
		// pawn can not move forward and take a piece, 
	    // nor can it move forward through a piece
		if (move.getToColumn() == move.getFromColumn()) {
			if (model.pieceAt(board, move.getToRow(), 
					move.getToColumn()) != null) {
				return false;
			}
			if (Math.abs(move.getToRow() - move.getFromRow()) == 2) {
				if (model.pieceAt(board, move.getToRow() - direction, 
						move.getToColumn()) != null) {
					return false;
				}
			}
		}

		// Pawn can only move one square at at time.
		// In their first move, they can move two squares if they want.
		// Pawn can only move straight forward.
		// Pawn can only capture one of the enemy 
		// by approaching diagonally
		// Pawns can do en Passants too
		
		// check to see if it is their first move
		// and if they are moving 1 or 2 spaces forward
		if (!getMoved()) {
			if ((move.getToRow() - move.getFromRow()) * direction <= 2 
					&& move.getToColumn() == move.getFromColumn()) {
				return true;
			}
		}
		
		// not first move, so just moving 1 space forward
		if (board.pieceAt(move.getToRow(), move.getToColumn()) 
				== null) {
			if ((move.getToRow() - move.getFromRow()) * direction == 1
					&& move.getToColumn() == move.getFromColumn()) {
				return true;
			}
			
			// check for an en passant
			if (model.enPassant(move) && (move.getToRow() - move
					.getFromRow()) * direction == 1 && Math
						.abs(move.getToColumn() - move.
								getFromColumn()) == 1) {
				return true;
			}
		} else {
			
			// else check if they are taking a piece
			if ((move.getToRow() - move.getFromRow()) * direction == 1
					&& Math.abs(move.getToColumn() - move.
							getFromColumn()) == 1 && model.pieceAt(
									board, move.getToRow(), move.
										getToColumn()) != null) {
				return true;
			}
		}
		
		// if they code reaches here, the move is not valid
		return false;
	}
	
	
	/*****************************************************************
	 * Set the current piece to reflect it being moved.
	 * 
	 * @param moved
	 *            a boolean stating if the piece was moved
	 *****************************************************************/
	@Override
	public void setMoved(final boolean moved) {
		super.setMoved(moved);
	}
	
	
	/*****************************************************************
	 * Returns a boolean that is true or false depending on if the 
	 * piece has moved yet.
	 * 
	 * @return a boolean stating if the piece has moved yet or not
	 *****************************************************************/
	@Override
	public boolean getMoved() {
		return super.getMoved();
	}

}
