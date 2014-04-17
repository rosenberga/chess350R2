package model;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer {

	private final int KING_RANK = 0;
	private final int QUEEN_RANK = 1;
	private final int ROOK_RANK = 2;
	private final int KNIGHT_RANK = 4;
	private final int BISHOP_RANK = 6;
	private final int PAWN_RANK = 8;
	private final int NUM_PIECES = 16;
	private final int ROW_TO_FRONT = 10;

	public ComputerPlayer() {

	}

	public final Move getRandomMove(final IChessModel model,
			final IChessBoard board) {

		int fr = 0;
		int fc = 0;
		int count = 0;
		int[] from = new int[NUM_PIECES];
		for (int i = 0; i < NUM_PIECES; i++) { // initialize coordinates to -1
			from[i] = -1;
		}

		ArrayList<IChessPiece> field = new ArrayList<IChessPiece>(); // pieces
																		// owned
																		// by
																		// computer
		for (int i = 0; i < board.numRows(); i++) {
			for (int j = 0; j < board.numColumns(); j++) {
				if (board.pieceAt(i, j) != null) {
					if (board.pieceAt(i, j).player() == model.currentPlayer()) {
						field.add(board.pieceAt(i, j)); // adding pieces owned
														// by computer
						from[count] = i * 10 + j; // getting coordinates and
													// storing into single int
						count++;
					}
				}
			}
		}

		Random r = new Random();

		while (true) {
			int index = r.nextInt(field.size() - 1);
			fc = from[index] % 10;
			fr = (from[index] - fc) / 10;
			ArrayList<Move> available = new ArrayList<Move>(); // all available
																// moves by
																// selected
																// piece
			for (int i = 0; i < board.numRows(); i++) {
				for (int j = 0; j < board.numColumns(); j++) {
					if (model.isValidMove(new Move(fr, fc, i, j), board)) {
						available.add(new Move(fr, fc, i, j)); // add move to
																// available if
																// possible
					}
				}
			}
			if (available.size() > 0) {
				r = new Random();
				Move m = available.get(r.nextInt(available.size()));
				return m;
			}
		}
	}

	/*********************************************************
	 * Make a move focused on attacking the opponent.
	 * 
	 * @param model
	 *            the set of chess rules
	 * @param board
	 *            the chess board to work with
	 * @return the move to be made is returned
	 ********************************************************/
	public final Move getAttackMove(final IChessModel model,
			final IChessBoard board) {
		ArrayList<String> legal = new ArrayList<String>();

		for (int i = 0; i < board.numRows(); i++) {
			for (int j = 0; j < board.numColumns(); j++) {
				if (board.pieceAt(i, j) != null) {
					if (board.pieceAt(i, j).player() == model.currentPlayer()) {
						for (int k = 0; k < board.numRows(); k++) {
							for (int l = 0; l < board.numColumns(); l++) {
								if (board.pieceAt(k, l) != null) {
									if (board.pieceAt(k, l).player() != model
											.currentPlayer()) {
										if (model.isValidMove(new Move(i, j, k,
												l), board)) {
											String s = ""+i+""+j+""+k+""+l;
											legal.add(s);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (legal.size() > 0) {
			Random r = new Random();
			int index = r.nextInt(legal.size());
			String positions = legal.get(index);
			int fr = Integer.parseInt(positions.charAt(0)+"");
			int fc = Integer.parseInt(positions.charAt(1)+"");
			int tr = Integer.parseInt(positions.charAt(2)+"");
			int tc = Integer.parseInt(positions.charAt(3)+"");
			return new Move(fr,fc,tr,tc);
		} else {
			return getRandomMove(model, board);
		}
	}

	public final Move getDefensiveMove(final IChessModel model,
			final IChessBoard board) {

		// create an array holding the locations of all the pieces
		int[] field = new int[NUM_PIECES];

		// Initialize all elements in field to zero
		for (int i = 0; i < field.length; i++) {
			field[i] = 0;
		}

		// count of pawns found
		int pawnCount = 0;

		// loop through board and find position of every piece
		for (int i = 0; i < board.numRows(); i++) {
			for (int j = 0; j < board.numColumns(); j++) {
				if (board.pieceAt(i, j) != null) {
					if (board.pieceAt(i, j).player() == model.currentPlayer()) {
						String type = board.pieceAt(i, j).type();
						int loc = (i * ROW_TO_FRONT) + j;
						switch (type) {
						case "King":
							field[KING_RANK] = loc;
							break;
						case "Queen":
							field[QUEEN_RANK] = loc;
							break;
						case "Rook":
							if (field[ROOK_RANK] == 0) {
								field[ROOK_RANK] = loc;
							} else {
								field[ROOK_RANK + 1] = loc;
							}
							break;
						case "Knight":
							if (field[KNIGHT_RANK] == 0) {
								field[KNIGHT_RANK] = loc;
							} else {
								field[KNIGHT_RANK + 1] = loc;
							}
							break;
						case "Bishop":
							if (field[BISHOP_RANK] == 0) {
								field[BISHOP_RANK] = loc;
							} else {
								field[BISHOP_RANK + 1] = loc;
							}
							break;
						default:
							field[PAWN_RANK + pawnCount] = loc;
							pawnCount++;
							break;
						}

					}
				}
			}
		}

		// loop through board and see if pieces are in danger
		for (int i = 0; i < board.numRows(); i++) {
			for (int j = 0; j < board.numColumns(); j++) {
				if (board.pieceAt(i, j) != null) {
					if (board.pieceAt(i, j).player() == model.currentPlayer()) {
						// check if the piece can take a piece in field
						for (int k = 0; k < field.length; k++) {
							if (field[k] != 0) {
								int tc = field[k] % ROW_TO_FRONT;
								int tr = (field[k] - tc) / ROW_TO_FRONT;
								Move m = new Move(i, j, tc, tr);
								if (model.isValidMove(m, board)) {

									// because this is a valid move
									// we have identified the piece in danger
									// now we must find a place where it can
									// move to
									for (int r = 0; r < board.numRows(); r++) {
										for (int c = 0; c < board.numColumns(); c++) {
											m = new Move(tr, tc, r, c);
											if (model.isValidMove(m, board)) {
												return m;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// if we reach here, either no pieces are in danger or
		// no legal def. moves can be made
		return getRandomMove(model, board);
	}
}
