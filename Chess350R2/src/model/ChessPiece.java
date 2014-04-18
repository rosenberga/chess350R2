package model;

import java.io.Serializable;

/*****************************************************************
 * Class that represents a chess piece.
 *
 * @author Team 7
 * @version 1.0
 *****************************************************************/
public abstract class ChessPiece implements IChessPiece, Serializable {

	/** The owner of a chess piece. */
	private Player owner;

	/** If the piece has moved yet or not. */
	private boolean moved;


	/*****************************************************************
	 * Constructs a new, unmoved chess piece.
	 *
	 * @param color the color of the chess piece
	 *****************************************************************/
	protected ChessPiece(final Player color) {
		this.owner = color;
		this.moved = false;
	}


	/*****************************************************************
	 * Gets the owner of the chess piece.
	 *
	 * @return owner the player who owns the chess piece
	 *****************************************************************/
	public final Player player() {
		return owner;
	}


	/*****************************************************************
	 * Returns the type of chess piece that it is.
	 *
	 * @return the name of the piece
	 *****************************************************************/
	public abstract String type();

	/*****************************************************************
	 * Determines is a move is valid for any given chess piece.
	 *
	 * @param m the move to check
	 * @param board the board to check the move ob
	 * @param model the model to use when checking the board
	 * @return true if the move is valid, else false
	 *****************************************************************/
	public boolean isValidMove(final Move m,
			final IChessBoard board, final IChessModel model) {

		// make sure the move is within the board
		if (m.getFromRow() < 0 || m.getFromRow() >= board.numRows()
				|| m.getToRow() < 0 || m.getToRow()
					>= board.numRows() ||
					m.getFromColumn() < 0
						|| m.getFromColumn()
						>= board.numColumns()
							|| m.getToColumn() < 0
								|| m.getToColumn()
									>= board.numColumns()) {
			return false;
		}
		// Checks if the space is different
		if (m.getFromRow() == m.getToRow()
				&& m.getFromColumn() == m.getToColumn()) {
			return false;
		}

		// Checks if the pieces are different colors
		if (board.pieceAt(m.getToRow(), m.getToColumn()) != null) {
			if (board.pieceAt(m.getToRow(),
					m.getToColumn()).player()
					== board.pieceAt(m.getFromRow(),
							m.getFromColumn()).player()) {
				return false;
			}
		}
		return true;
	}


	/*****************************************************************
	 * Sets the chess piece's moved status to true or false.
	 *
	 * @param m true if the piece has been moved, else false
	 *****************************************************************/
	public void setMoved(final boolean m) {
		this.moved = m;
	}

	/*****************************************************************
	 * Gets the piece's moved status.
	 *
	 * @return moved the boolean status of if the piece has been
	 * moved yet
	 *****************************************************************/
	public boolean getMoved() {
		return moved;
	}
}
