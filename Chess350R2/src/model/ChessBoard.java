package model;

import java.io.Serializable;

/*****************************************************************
 * Represents a chess board for a standard game of chess. Stores 
 * pieces at positions on the board and can move all pieces
 * when needed.
 * 
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public final class ChessBoard implements IChessBoard, Serializable{

	/** The current board in the form of a 2D array of IChessPieces. */
	private IChessPiece[][] board;
	
	/** The standard row number. */
	private static final int ROWS = 8;
	
	/** The standard column number. */
	private static final int COLUMNS = 8;
	
	/** White Pawn Row. */
	private static final int WP = 6;
	
	/** Black Pawn Row. */
	private static final int BP = 1;
	
	/** White Pieces Row. */
	private static final int WPR = 7;
	
	/** Black Pieces Row. */
	private static final int BPR = 0;
	
	/** Kings' Column. */
	private static final int KR = 4;
	
	/** Queen's Column. */
	private static final int QR = 3;
	
	/** Rook's Left Column. */
	private static final int R1 = 0;
	
	/** Rook's Right Column. */
	private static final int R2 = 7;
	
	/** Knight's Left Column. */
	private static final int K1 = 1;
	
	/** Knight's Right Column. */
	private static final int K2 = 6;
	
	/** Bishop's Left Column. */
	private static final int B1 = 2;
	
	/** Bishop's Right Column. */
	private static final int B2 = 5;
	

	/*****************************************************************
	 * Constructs a new ChessBoard.
	 *****************************************************************/
	public ChessBoard() {

		// set the board to having 8 rows and 8 columns
		board = new IChessPiece[ROWS][COLUMNS];

		// fill the pieces of the board with appropriate chess piece
		// objects of null values
		board[BPR][R1] = new Rook(Player.BLACK);
		board[BPR][K1] = new Knight(Player.BLACK);
		board[BPR][B1] = new Bishop(Player.BLACK);
		board[BPR][QR] = new Queen(Player.BLACK);
		board[BPR][KR] = new King(Player.BLACK);
		board[BPR][B2] = new Bishop(Player.BLACK);
		board[BPR][K2] = new Knight(Player.BLACK);
		board[BPR][R2] = new Rook(Player.BLACK);
		board[WPR][R1] = new Rook(Player.WHITE);
		board[WPR][K1] = new Knight(Player.WHITE);
		board[WPR][B1] = new Bishop(Player.WHITE);
		board[WPR][QR] = new Queen(Player.WHITE);
		board[WPR][KR] = new King(Player.WHITE);
		board[WPR][B2] = new Bishop(Player.WHITE);
		board[WPR][K2] = new Knight(Player.WHITE);
		board[WPR][R2] = new Rook(Player.WHITE);

		// loop to set the pawns
		for (int i = 0; i < board[0].length; i++) {
			board[BP][i] = new Pawn(Player.BLACK);
			board[WP][i] = new Pawn(Player.WHITE);
		}
	}

	/*****************************************************************
	 * Returns the number of rows in the board.
	 * 
	 * @return the number of rows in the board
	 *****************************************************************/
	@Override
	public int numRows() {
		return board.length;
	}

	/*****************************************************************
	 * Returns the number of columns in the board.
	 * 
	 * @return the number of columns in the board
	 *****************************************************************/
	@Override
	public int numColumns() {
		return board[0].length;
	}

	/*****************************************************************
	 * Returns an IChessPiece at a given row and column.
	 * 
	 * @param row
	 *            the row of the IChessPiece to get
	 * @param column
	 *            the column of the IChessPiece to get
	 * @return the IChessPiece found at a given row and column
	 *****************************************************************/
	@Override
	public IChessPiece pieceAt(final int row, final int column) {
		if (row >= 0 && row < numRows() && column >= 0
				&& column < numColumns()) {
			return board[row][column];
		}
		return null;
	}

	/*****************************************************************
	 * Perform a move operation on the board by changing where a 
	 * piece is located in board.
	 * 
	 * @param move
	 *            the move to be made on the board
	 *****************************************************************/
	@Override
	public void move(final Move move) {

		// set the piece to its new position
		// need to get the piece at the old position so the program
		// knows what piece needs to be moved
		set(pieceAt(move.getFromRow(), move.getFromColumn()), 
				move.getToRow(), move.getToColumn());

		// set the old position to null
		unset(move.getFromRow(), move.getFromColumn());
	}

	/*****************************************************************
	 * Set a position, given by row and column, 
	 * in board to a given IChessPiece.
	 * 
	 * @param piece
	 *            the IChessPiece to set at the given position
	 * @param row
	 *            the row to set the piece at
	 * @param column
	 *            the column to set the piece at
	 *****************************************************************/
	@Override
	public void set(final IChessPiece piece, final int row,
			final int column) {
		if (row >= numRows() || row < 0 || column < 0 
				|| column >= numColumns()) {
			return;
		}
		board[row][column] = piece;
	}

	/*****************************************************************
	 * Set a given position, given by row and column, in board to null.
	 * 
	 * @param row
	 *            the row to set null
	 * @param column
	 *            the column to set null
	 *****************************************************************/
	@Override
	public void unset(final int row, final int column) {
		if (row >= numRows() || row < 0 || column < 0 
				|| column >= numColumns()) {
			return;
		}
		board[row][column] = null;
	}

	/*****************************************************************
	 * Returns the current board.
	 * 
	 * @return board the current IChessPiece[][] board
	 *****************************************************************/
	public IChessPiece[][] getBoard() {
		return board;
	}

	/*****************************************************************
	 * Set board to a given new IChessPiece[][].
	 * 
	 * @param newBoard
	 *            the IChessPiece[][] to set board to
	 *****************************************************************/
	public void setBoard(final IChessPiece[][] newBoard) {
		this.board = newBoard;
	}

	/*****************************************************************
	 * Nulls out the board.
	 *****************************************************************/
	public void clearBoard() {

		// loop through the board setting each index to null
		for (int i = 0; i < numRows(); i++) {
			for (int j = 0; j < numColumns(); j++) {
				board[i][j] = null;
			}
		}
	}

	/*****************************************************************
	 * Creates a new IChessPiece[][] that is a copy of a given board.
	 * 
	 * @return newBoard a IChessPiece[][] of this.board
	 *****************************************************************/
	public IChessPiece[][] copyBoard() {
		IChessPiece[][] newBoard = new IChessPiece[ROWS][COLUMNS];

		// loop for each row
		for (int i = 0; i < numRows(); i++) {

			// clone the array stored at index i
			// need to first get the actual IChessPiece[][] of board
			newBoard[i] = (getBoard()[i]).clone();
		}
		return newBoard;
	}

}
