package model;

/*****************************************************************
 * A Rook in a game of chess.
 * 
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public final class Rook extends ChessPiece {

	
	/*****************************************************************
	 * Constructs a new Rook object.
	 * 
	 * @param color
	 *            the player that owns this piece
	 *****************************************************************/
	protected Rook(final Player color) {
		super(color);
	}

	
	/*****************************************************************
	 * Returns the type of ChessPiece it is.
	 * 
	 * @return the String value of the name of the ChessPiece
	 *****************************************************************/
	@Override
	public String type() {
		return "Rook";
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
		
		// changing rows
		if (move.getToColumn() == move.getFromColumn()) {
			return checkForPieceInPath(move.getFromRow(), 
					move.getToRow(), move.getFromColumn(), 
						false, board);
		}
		
		// changing columns
		if (move.getToRow() == move.getFromRow()) {
			return checkForPieceInPath(move.getFromColumn(), 
					move.getToColumn(), move.getFromRow(), 
						true, board);
		}
		return false;
	}
	
	/*****************************************************************
	 * Returns a boolean that is true or false depending on if the 
	 * piece has moved yet.
	 * 
	 * @param from where they are moving from
	 * @param to where they are moving to
	 * @param same what is being held constant
	 * @param row if the row is held constant
	 * @param board the chess board they are moving on
	 * @return true if there is no piece in the path, else false
	 *****************************************************************/
	private boolean checkForPieceInPath(final int from, final int to, 
			final int same, final boolean row, 
				final IChessBoard board) {
		
		// get the direction they are movings
		int direction;
		if (from < to) {
			direction = 1;
		} else {
			direction = -1;
		}
		
		// loop from where they are to where they are going
		for (int i = from + direction; i != to; i += direction) {
			
			// get the piece at the position
			if (row) {
				if (board.pieceAt(same, i) != null) {
					return false;
				}
			} else {
				if (board.pieceAt(i, same) != null) {
					return false;
				}
			}
		}
		return true;
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
