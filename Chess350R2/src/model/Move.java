package model;

import java.io.Serializable;

/**
 * Packages the four components of a move into a single object. (Instance
 * variables are public because this object is a simple container.)
 *
 * @author Zachary Kurmas
 */

public final class Move implements Serializable {

	/** The row and column the piece is moving from and moving to. */
	private int fromRow, fromColumn, toRow, toColumn;
	/** A coordinate in array for move. */
	private final int zero = 0;
	/** A coordinate in array for move. */
	private final int one = 1;
	/** A coordinate in array for move. */
	private final int two = 2;
	/** A coordinate in array for move. */
	private final int three = 3;
	/** A length of array for move. */
	private final int four = 4;

	/*****************************************************************
	 * Constructs a new Move.
	 *
	 * @param fR
	 *            the row the piece is moving from
	 * @param fC
	 *            the column the piece is moving from
	 * @param tR
	 *            the row the piece is moving to
	 * @param tC
	 *            the column the piece is moving to
	 *****************************************************************/
	public Move(final int fR, final int fC, final int tR,
			final int tC) {
		this.setFromRow(fR);
		this.setFromColumn(fC);
		this.setToRow(tR);
		this.setToColumn(tC);
	}

	/*****************************************************************
	 * Constructor for the Move class given coordinates.
	 *
	 * @param coords the coordinates for the move
	 *****************************************************************/
	public Move(final int[] coords) {
		if (coords.length == four) {
			this.setFromRow(coords[zero]);
			this.setFromColumn(coords[one]);
			this.setToRow(coords[two]);
			this.setToColumn(coords[three]);
		} else {
			this.setFromRow(-1);
			this.setFromColumn(-1);
			this.setToRow(-1);
			this.setToColumn(-1);
		}
	}


	/*****************************************************************
	 * Gets fromRow.
	 *
	 * @return fromRow
	 *****************************************************************/
	public int getFromRow() {
		return fromRow;
	}


	/*****************************************************************
	 * Sets fromRow.
	 *
	 * @param fR the row being moved from
	 *****************************************************************/
	public void setFromRow(final int fR) {
		this.fromRow = fR;
	}


	/*****************************************************************
	 * Gets fromColumn.
	 *
	 * @return fromColumn
	 *****************************************************************/
	public int getFromColumn() {
		return fromColumn;
	}


	/*****************************************************************
	 * Sets fromColumn.
	 *
	 * @param fC the column being moved from
	 *****************************************************************/
	public void setFromColumn(final int fC) {
		this.fromColumn = fC;
	}


	/*****************************************************************
	 * Gets toRow.
	 *
	 * @return toRow
	 *****************************************************************/
	public int getToRow() {
		return toRow;
	}


	/*****************************************************************
	 * Sets toRow.
	 *
	 * @param tR the row being moved to
	 *****************************************************************/
	public void setToRow(final int tR) {
		this.toRow = tR;
	}


	/*****************************************************************
	 * Gets toColumn.
	 *
	 * @return toColumn
	 *****************************************************************/
	public int getToColumn() {
		return toColumn;
	}


	/*****************************************************************
	 * Sets toColumn.
	 *
	 * @param tC the column being moved to
	 *****************************************************************/
	public void setToColumn(final int tC) {
		this.toColumn = tC;
	}
}
