package model;

/*****************************************************************
 * Represents a chess model for a standard game of chess. Contains 
 * logic for knowing who the current player is, whether or not 
 * they can move en passant, and the state of the game. 
 * Also checks if moves are valid and makes valid moves.
 * 
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public final class ChessModel implements IChessModel {

	/** whether or not the player can move en passant. */
	private boolean enPassant;

	/** the position the player must be in to move en passant
			from the right. */
	private String enPassantPositionR;

	/** the position the player must be in to move en passant 
		from the left. */
	private String enPassantPositionL;

	/** the number of turns played so far. */
	private int turns;
	
	/** Column King must be in to Castle one way. */
	private static final int CAST1 = 2;
	
	/** Column King must be in to Castle another way. */
	private static final int CAST2 = 6;
	
	/** Column Rook must be in to Castle one way. */
	private static final int CAST1FROM = 0;
	
	/** Column Rook must be in to Castle another way. */
	private static final int CAST2FROM = 7;
	
	/** Column King is moving to one way. */
	private static final int CAST1TO = 3;
	
	/** Column King is moving to another way. */
	private static final int CAST2TO = 5;

	/*****************************************************************
	 * Constructs a new ChessModel.
	 *****************************************************************/
	public ChessModel() {
		turns = 0;
		enPassant = false;
		enPassantPositionR = "";
		enPassantPositionL = "";
	}
	
	/*****************************************************************
	 * Checks whether the board is en passant.
	 * 
	 * @return enPassant, whether or not the player can move enpassant
	 *****************************************************************/
	public boolean getEnPassant() {
		return enPassant;
	}

	/*****************************************************************
	 * Determines whether or not a player's move was en passant 
	 * and is so, was it legal.
	 * 
	 * @param move
	 *            the move to be made
	 * @return true if the en passant move was legal, else false
	 *****************************************************************/
	public boolean getEnPassant(final Move move) {

		// board must first be in an enPassant
		if (enPassant) {

			// the move must be from a certain position to a certain
			// position
			String enP = move.getFromRow() + "" + move.getFromColumn()
					+ "" + move.getToRow() + "" + move.getToColumn();
			if (getEnPassantPosition(true).equals(enP)) {
				return true;
			}
			if (getEnPassantPosition(false).equals(enP)) {
				return true;
			}

		}
		return false;
	}

	/*****************************************************************
	 * Set the board to being en passant or not.
	 * 
	 * @param enpassant a boolean that is true if the next player can
	 *  move enpassant else it is false
	 *****************************************************************/
	public void setEnPassant(final boolean enpassant) {
		this.enPassant = enpassant;
	}

	/*****************************************************************
	 * Returns the number of turns.
	 * 
	 * @return the number of turns played so far
	 *****************************************************************/
	public int getTurns() {
		return this.turns;
	}

	/*****************************************************************
	 * Sets the number of turns by increasing or decreasing turns.
	 * 
	 * @param t the number to increase or decrease turns by
	 *****************************************************************/
	public void setTurns(final int t) {
		turns += t;
	}

	/*****************************************************************
	 * Checks if the game is complete.
	 * 
	 * @param board
	 *            the IChessBoard to check for completion
	 * @return true if the game is completed, else false
	 *****************************************************************/
	@Override
	public boolean isComplete(final IChessBoard board) {

		// the game is done if the board is in a checkmate or stalemate
		if (inCheckMate(board)) {
			return true;
		}
		if (inStaleMate(board)) {
			return true;
		}
		return false;
	}

	/*****************************************************************
	 * Checks to see if a move is valid.
	 * 
	 * @param move the move to check for validity
	 * @param board  the IChessBoard to check the move on
	 * @return true if the move is valid, else false
	 *****************************************************************/
	@Override
	public boolean isValidMove(final Move move,
			final IChessBoard board) {

		// check that the current player owns the piece
		if (pieceAt(board, move.getFromRow(), move.getFromColumn())
				.player() != currentPlayer()) {
			return false;
		}
		// first check to see if the move is legal for the piece
		if (pieceAt(board, move.getFromRow(), 
				move.getFromColumn()).isValidMove(move,
				board, this)) {

			// if it is, create a new board
			// and make the move
			IChessBoard fakeBoard = new ChessBoard();
			fakeBoard.setBoard(board.copyBoard());
			boardChange(fakeBoard, move);

			return !inCheck(fakeBoard);
		}
		return false;
	}

	/*****************************************************************
	 * Checks if the game is complete.
	 * 
	 * @param move the move to be made
	 * @param board the IChessBoard to make the move on
	 *****************************************************************/
	@Override
	public void move(final Move move, final IChessBoard board) {

		// make the move on the board
		boardChange(board, move);
		
		// check if the pawn moved en passant so
		// the opponents piece can be removed
		
		// if getEnPassant(move) is true, then they moved in passant
		if (getEnPassant(move)) {
			
			// get the pawn's position that was taken
			// and reset it
			int col = move.getToColumn();
			int row = move.getFromRow();
			board.unset(row, col);
		}

		// if pawn just moved two spaces, set en passant to true

			// pawn must have moved two spaces forward
			// and a pawn of the different color must be
			// to the right or to the left
			if (pieceAt(board, move.getToRow(), move.getToColumn())
					.type().equals("Pawn") && Math.abs(
							move.getToRow() - move.getFromRow(
									)) == 2) {
				int direction;
				if (currentPlayer() == Player.WHITE) {
					direction = -1;
				} else {
					direction = 1;
				}
				if (move.getToColumn() - 1 >= 0) {
					IChessPiece pawn = pieceAt(board, move.getToRow(), 
							move.getToColumn() - 1);
					if (pawn != null && pawn.type()
							.equals("Pawn")
							&& pawn.player() != currentPlayer()) {

						// if the next player can move en passant
						// set en pasant to true
						// and determine where they must move from
						// and move to
						setEnPassant(true);
						setEnPassantPosition(move.getToRow(),
								move.getToColumn() - 1, move
									.getFromRow() + direction,
										move.getFromColumn(), true);
					}
				}
				if (move.getToColumn() + 1 < board.numColumns()) {
					IChessPiece pawn = pieceAt(board, move.getToRow(), 
							move.getToColumn() + 1);
					if (pawn != null && pawn.type()
							.equals("Pawn")
							&& pawn.player() != currentPlayer()) {
						setEnPassant(true);
						setEnPassantPosition(move.getToRow(), move
								.getToColumn() + 1, move.getFromRow()
									+ direction, move.getFromColumn(),
										false);
					}
				}
			} else {

				// else the next player can not move en passant
				setEnPassant(false);
				zeroEnPassantPosition();
			}

		// check for castling
		// piece must be a king and moving two to the left or right
		if (pieceAt(board, move.getToRow(), move.getToColumn()).type()
				.equals("King")
				&& (Math.abs(move.getToColumn()
						- move.getFromColumn()) == 2)) {
			Move m = null;
			
			// if they castled, determine where the rook should move
			if (move.getToColumn() == CAST1) {
				m = new Move(move.getFromRow(), CAST1FROM, 
						move.getFromRow(), CAST1TO);
			}
			if (move.getToColumn() == CAST2) {
				m = new Move(move.getFromRow(), CAST2FROM, 
						move.getFromRow(), CAST2TO);
			}

			// move the rook
			boardChange(board, m);
		}

		// check for promotion
		if (pieceAt(board, move.getToRow(), move.getToColumn()).type()
				.equals("Pawn")
				&& (move.getToRow() == 0
					|| move.getToRow() == board.numRows() - 1)) {
			// do promotion
			// model makes it a queen,
			// but will later ask for user input to make 
			// it be different pieces
			promotion(board, move);
		}

		// set the piece to moved and increase turn by 1
		pieceAt(board, move.getToRow(), move.getToColumn())
			.setMoved(true);
		setTurns(1);
	}

	/*****************************************************************
	 * Promote a pawn to another piece.
	 * 
	 * @param board the IChessBoard to make the promotion on
	 * @param move the move that was made
	 *****************************************************************/
	public void promotion(final IChessBoard board, 
			final Move move) {

		// null the position and reset it was a new queen
		board.unset(move.getToRow(), move.getToColumn());
		board.set(new Queen(currentPlayer()), 
				move.getToRow(), move.getToColumn());
	}

	/*****************************************************************
	 * Promote a pawn to another piece.
	 * 
	 * @param board the IChessBoard to make the promotion on
	 * @param move the move that was made
	 * @param piece the piece to promote to
	 *****************************************************************/
	public void promotion(final IChessBoard board, 
			final Move move, final IChessPiece piece) {
		
		// reset the piece
		if (piece instanceof Queen) {
			board.set(new Queen(currentPlayer()),
					move.getToRow(), move.getToColumn());
		} else if (piece instanceof Bishop) {
			board.set(new Bishop(currentPlayer()),
					move.getToRow(), move.getToColumn());
		} else if (piece instanceof Rook) {
			board.set(new Rook(currentPlayer()),
					move.getToRow(), move.getToColumn());
		} else if (piece instanceof Knight) {
			board.set(new Knight(currentPlayer()),
					move.getToRow(), move.getToColumn());
		}
	}

	/*****************************************************************
	 * Changes piece positions on the board.
	 * 
	 * @param board
	 *            the IChessBoard to change
	 * @param move
	 *            the move to make
	 *****************************************************************/
	private void boardChange(final IChessBoard board, final Move move) {

		// set the piece in its new position
		// set its old position to null
		board.set(pieceAt(board, move.getFromRow(), move.getFromColumn(
				)), move.getToRow(), move.getToColumn());
		board.unset(move.getFromRow(), move.getFromColumn());
	}

	/*****************************************************************
	 * Sets the current enPassantPossition.
	 * 
	 * @param attR the attacking row
	 * @param attC the attacking column
	 * @param toR the row they must be moving to
	 * @param toC the column they must be moving to
	 * @param right if they need to attack from the right or left
	 *****************************************************************/
	private void setEnPassantPosition(final int attR, final int attC, 
			final int toR, final int toC, final boolean right) {

		// set the enPassantPosition to have 4 digits ordered:
		// attR, attC, toR, toC
		if (right) {
			enPassantPositionR = attR + "" + attC + "" + toR 
					+ "" + toC;
		} else {
			enPassantPositionL = attR + "" + attC + "" + toR 
					+ "" + toC;
		}
	}
	
	
	/*****************************************************************
	 *Resets out enPassantPosition right and left.
	 *****************************************************************/
	private void zeroEnPassantPosition() {
		enPassantPositionR = "";
		enPassantPositionL = "";
	}

	/*****************************************************************
	 * Get the enPassantPosition that the pawn must be in to move 
	 * en passant.
	 * 
	 * @param right if they are attacking from the right
	 * @return the current enPassantPosition
	 *****************************************************************/
	private String getEnPassantPosition(final boolean right) {
		if (right) {
			return enPassantPositionR;
		}
		return enPassantPositionL;
	}

	
	/*****************************************************************
	 * Finds the king's position.
	 * 
	 * @param board the IChessBoard to check for king's position
	 * @return the kings position
	 *****************************************************************/
	public int[] getKingsPos(final IChessBoard board) {
		int[] kingsP = new int[2];
		
		// find the current color's king
		for (int i = 0; i < board.numRows(); i++) {
			for (int j = 0; j < board.numColumns(); j++) {
						
				// it is the piece we want if it is not null, 
				// if it is a King object and if it is owned by the
				// current player
				if (pieceAt(board, i, j) != null && pieceAt(board,
						i, j).type().equals("King") && pieceAt(board,
								i, j).player() == currentPlayer()) {
							
					// set the row, column and break
					kingsP[0] = i;
					kingsP[1] = j;
					break;
				}
			}
		}
		return kingsP;
	}

	/*****************************************************************
	 * Checks to see if the current player is in check.
	 * 
	 * @param board the IChessBoard to check for check
	 * @return true if the board is in check, else false
	 *****************************************************************/
	
	@Override
	public boolean inCheck(final IChessBoard board) {
		int[] kingsP = getKingsPos(board);
		int kingsColumn = kingsP[1];
		int kingsRow = kingsP[0];
		

		// loop through the board and see if an opponents piece
		// can make a legal move to the king's current position
		for (int i = 0; i < board.numRows(); i++) {
			for (int j = 0; j < board.numColumns(); j++) {
				if (pieceAt(board, i, j) != null
						&& pieceAt(board, i, j).player() 
							!= currentPlayer()) {
						Move move = new Move(i, j, 
								kingsRow, kingsColumn);
						// if the move is legal, the king is in check
						if (pieceAt(board, i, j).isValidMove(
								move, board, this)) {
							return true;
						}
				}
			}
		}

		// the king is not in check
		return false;
	}

	/*****************************************************************
	 * Checks the board for a checkmate.
	 * 
	 * @param board
	 *            the IChessBoard to check for checkmate
	 * @return true if the board is in checkmate, else false
	 *****************************************************************/
	@Override
	public boolean inCheckMate(final IChessBoard board) {

		// to be in checkmate, the board must first be in check
		// and there must be no legal moves
		if (inCheck(board)) {
			return checkForNoLegalMoves(board);
		}
		return false;
	}

	/*****************************************************************
	 * Checks the board for legal moves by the current player.
	 * 
	 * @param board
	 *            the IChessBoard to check for legal moves
	 * @return false if the current player has a legal move, else true
	 *****************************************************************/
	private boolean checkForNoLegalMoves(final IChessBoard board) {

		// loop through every board position
		for (int i = 0; i < board.numRows(); i++) {
			for (int j = 0; j < board.numColumns(); j++) {

				// get the piece and continue if it is owned by the
				// current player
				IChessPiece piece = pieceAt(board, i, j);
				if (piece != null && piece.player() 
						== currentPlayer()) {

					// check every board position and see if piece
					// can move there
					for (int m = 0; m < board.numRows(); m++) {
						for (int n = 0; n < board.numColumns(); n++) {
							Move move = new Move(i, j, m, n);

							// check if the move is legal
							if (piece.isValidMove(move, board, this)) {

								// create a new board that is a
								// copy of the current board
								// and make the move
								IChessBoard fakeBoard = new 
										ChessBoard();
								fakeBoard.setBoard(board
										.copyBoard());
								boardChange(fakeBoard, move);
								
								// if they are not in check 
								// after the move,
								// it is a legal move
								if (!inCheck(fakeBoard)) {
									return false;
								}
							}
						}
					}
				}
			}
		}

		// no piece can move
		return true;
	}

	/*****************************************************************
	 * Checks if a board is in stalemate.
	 * 
	 * @param board the IChessBoard to check for stalemate
	 * @return true if the board is in stalemate, else false
	 *****************************************************************/
	@Override
	public boolean inStaleMate(final IChessBoard board) {

		// the board is in stalemate if it is not in check
		// but the current player cannot make any legal moves
		if (!inCheck(board)) {
			int sumOfPieces = 0;
			for (int m = 0; m < board.numRows(); m++) {
				for (int n = 0; n < board.numColumns(); n++){
					IChessPiece piece = pieceAt(board, m, n);
					if(piece != null) {
						sumOfPieces++;
					}
				}
			}
			if(sumOfPieces == 2)
				return true;
			else
				return checkForNoLegalMoves(board);
		}
		return false;
	}

	/*****************************************************************
	 * Returns the current player.
	 * 
	 * @return the current player whose turn it is
	 *****************************************************************/
	@Override
	public Player currentPlayer() {

		// if it White's turn if the turn count is even
		if (getTurns() % 2 == 0) {
			return Player.WHITE;
		}
		return Player.BLACK;
	}

	/*****************************************************************
	 * Gets an IChessPiece at a row,column position in a given board.
	 * 
	 * @param board the IChessBoard to get the piece from
	 * @param row the row of the board to get the piece from
	 * @param column the column of the board to get the piece from
	 * @return an IChessPiece at row,column of board
	 *****************************************************************/
	@Override
	public IChessPiece pieceAt(final IChessBoard board,
			final int row, final int column) {
		return board.pieceAt(row, column);
	}

	/*****************************************************************
	 * Checks if a move is an en passant move.
	 * 
	 * @param move
	 *            the move to check for en passant
	 * @return true if the move is en passant, else false
	 *****************************************************************/
	@Override
	public boolean enPassant(final Move move) {
		return getEnPassant(move);
	}

}
