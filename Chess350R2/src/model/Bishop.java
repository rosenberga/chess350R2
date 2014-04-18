package model;

/*****************************************************************
 * A Bishop in a game of chess.
 *
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public final class Bishop extends ChessPiece {

	/*****************************************************************
	 * Constructs a new Bishop object.
	 *
	 * @param color the player that owns this piece
	 *****************************************************************/
	protected Bishop(final Player color) {
		super(color);
	}

	/*****************************************************************
	 * Returns the type of ChessPiece it is.
	 *
	 * @return the String value of the name of the ChessPiece
	 *****************************************************************/
	@Override
	public String type() {
		return "Bishop";
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

		// make sure the move is valid for any give chess piece object
		if (!super.isValidMove(move, board, model)) {
			return false;
		}

		// the move toRow minus fromRow must be the same as the
		// move fromColumn minus toColumn fir the bishop to move
		if (Math.abs(move.getToRow() - move.getFromRow())
				== Math.abs(move.getToColumn()
						- move.getFromColumn())) {

			// get the row and column direction the piece is moving
			int directionC, directionR;
			
			if (move.getFromColumn() < move.getToColumn()) {
				directionC = 1;
			} else {
				directionC = -1;
			}
			
			if (move.getFromRow() < move.getToRow()) {
				directionR = 1;
			} else {
				directionR = -1;
			}

			// loop through the move and check if it passes a piece
			for (int column = move.getFromColumn() + directionC, 
					row = move.getFromRow() + directionR; column 
						!= move.getToColumn(); column += directionC, 
							row += directionR) {

				// for every row and column in the loop
				// check if the space is occupied
				if (board.pieceAt(row, column) != null) {
					return false;
				}
			}

			// if the code did not return false in the for loop
			// the move is valid because the path from the piece's
			// current position to the piece's new position is open
			return true;
		}

		// if the code reaches here, the move is not valid
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
	 *		piece has moved yet.
	 *
	 * @return a boolean stating if the piece has moved yet or not
	 *****************************************************************/
	@Override
	public boolean getMoved() {
		return super.getMoved();
	}
}
