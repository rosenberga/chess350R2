package model;

/*****************************************************************
 * A King in a game of chess.
 * 
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public final class King extends ChessPiece {

	
	/*****************************************************************
	 * Constructs a new King object.
	 * 
	 * @param color
	 *            the player that owns this piece
	 *****************************************************************/
	protected King(final Player color) {
		super(color);
	}

	
	/*****************************************************************
	 * Returns the type of ChessPiece it is.
	 * 
	 * @return the String value of the name of the ChessPiece
	 *****************************************************************/
	@Override
	public String type() {
		return "King";
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

		// King can only move one square at a time but he can move
		// forward, backward, left, right, and diagonally.
		if (Math.abs(move.getToColumn() - move.getFromColumn()) <= 1
				&& Math.abs(move.getToRow() - move.getFromRow()) <= 1) {
			return true;
		}
		
		// check for castle too
		if (!getMoved()) {
			
			// check if the castle if to the right or is to the left
			if ((Math.abs(move.getToColumn() 
					- move.getFromColumn()) == 2) && move.getFromRow() == move.getToRow()) {
				IChessPiece piece;
				if (move.getToColumn() > move.getFromColumn()) {
					
					// moving to the right
					piece = board.pieceAt(move.getFromRow(), 
							board.numColumns() - 1);
				} else {
					
					// else moving to the left
					piece = board.pieceAt(move.getFromRow(), 0);
				}
				
				// check to make sure the rook is there 
				// and its has the same owner
				if (piece == null
						|| !piece.type().equals("Rook")
							|| piece.player() != this.player() 
								|| piece.getMoved()) {
					return false;
				} else {
					
					// make sure the king is not in check
					if (model.inCheck(board)) {
						return false;
					}
					return !movingThroughCheckOrAnotherPiece(
							move, board, model);
				}
			}
		}
		return false;
	}

	
	/*****************************************************************
	 * Checks to see if the king is moving through check
	 * or another piece when castling.
	 * 
	 * @param move the move to be made
	 * @param board the current IChessBoard
	 * @param model the IChessModel to use when checking
	 * @return true if the king is moving through check or another
	 * 		piece, else false
	 *****************************************************************/
	private boolean movingThroughCheckOrAnotherPiece(final Move move,
			final IChessBoard board, final IChessModel model) {
		
		// create a new board
		IChessBoard fakeBoard = new ChessBoard();
		fakeBoard.setBoard(board.copyBoard());
		
		// get the direction
		int direction;
		if (move.getFromColumn() < move.getToColumn()) {
			direction = 1;
		} else {
			direction = -1;
		}
		
		// move through the fakeboard checking if the position is
		// occupied or if the position would put the king in check
		for (int i = move.getFromColumn() + direction; 
				i != move.getToColumn(); i += direction) {
			if (model.pieceAt(fakeBoard, move.getFromRow(), i) 
					!= null) {
				return true;
			}
			fakeBoard.set(this, move.getFromRow(), i);
			fakeBoard.unset(move.getFromRow(), i - direction);
			if (model.inCheck(fakeBoard)) {
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
