package model;

/*****************************************************************
 * A Knight in a game of chess.
 * 
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public final class Knight extends ChessPiece {

	/*****************************************************************
	 * Constructs a new Knight object.
	 * 
	 * @param color
	 *            the player that owns this piece
	 *****************************************************************/
	protected Knight(final Player color) {
		super(color);
	}
	

	/*****************************************************************
	 * Returns the type of ChessPiece it is.
	 * 
	 * @return the String value of the name of the ChessPiece
	 *****************************************************************/
	@Override
	public String type() {
		return "Knight";
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
			
		// Knight can jump over another chess piece or pieces
		// Can move two squares either forward, backward, left, or right
		// and then left or right one square.

		if (Math.abs(move.getFromRow() - move.getToRow()) == 2) {
			if (Math.abs(move.getFromColumn()
					- move.getToColumn()) == 1) {
				return true;
			}
		}

		if (Math.abs(move.getFromColumn() - move.getToColumn()) == 2) {
			if (Math.abs(move.getFromRow() - move.getToRow()) == 1) {
				return true;
			}
		}
		
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
