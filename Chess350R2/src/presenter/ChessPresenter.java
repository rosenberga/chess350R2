package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import model.ChessGame;
import model.Move;
import model.Player;
import view.AboutDialog;
import view.ChessView;
import view.IChessView;
import view.SettingsDialog;

/*****************************************************************
 * A Presenter of information to the Model and View.
 * 
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public class ChessPresenter implements IChessPresenter {

	/** The standard row number. */
	private IChessView view;
	/** The standard row number. */
	private ChessGame game;
	/** The standard row number. */
	private static final int PV = 0;
	/** The standard row number. */
	private static final int BV = 1;
	/** The standard row number. */
	private static final int NV = 2;
	/** The standard row number. */
	private static final int RV = 3;
	/** The standard row number. */
	private static final int QV = 4;
	/** The standard row number. */
	private static final int KV = 5;
	/** The standard row number. */
	private static final int BLV = 0;
	/** The standard row number. */
	private static final int WHV = 1;
	/** The standard row number. */
	private static final int OWNER = 0;
	/** The standard row number. */
	private static final int TYPE = 1;
	/** The standard row number. */
	private static final int PIECE_INFO = 2;
	/** The standard row number. */
	private static final int NULL_OWNER = -1;
	/** The standard row number. */
	private static final int NULL_TYPE = -1;
	/** The standard row number. */
	private static final int COORDS = 4;
	/** The standard row number. */
	private static final int FR = 0;
	/** The standard row number. */
	private static final int FC = 1;
	/** The standard row number. */
	private static final int TR = 2;
	/** The standard row number. */
	private static final int TC = 3;
	/** The standard row number. */
	private boolean firstClick;
	/** The standard row number. */
	private int[] coords;
	/** The standard row number. */
	private static final int ROW = 0;
	/** The standard row number. */
	private static final int COL = 1;

	/*****************************************************************
	 * A constructor for the Presenter.
	 * 
	 * @param g
	 *            the game variable
	 * @param v
	 *            the view variable
	 *****************************************************************/
	public ChessPresenter(final ChessGame g, final IChessView v) {
		game = g;
		view = v;
		firstClick = true;
		coords = new int[COORDS];

		// set starting data
		for (int i = 0; i < game.getBoard().numRows(); i++) {
			for (int j = 0; j < game.getBoard().numColumns(); j++) {
				view.setData(i, j, sendPiece(i, j));
			}
		}

		view.addButtonListeners(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				// if they clicked a button, 
				// then it was a chess position
				if (e.getSource().getClass() == JButton.class) {
					int[] pos = view.getData(e);
					if (firstClick) {
						firstClick(pos);
					} else {
						firstClick = true;
						if (game.getBoard().pieceAt(pos[ROW], pos[COL]) 
								== null) {
							legalMove(pos, this);
						} else {
							if (game.getBoard().pieceAt(pos[ROW], 
									pos[COL]).player() != game
										.getModel().currentPlayer()) {
								legalMove(pos, this);
							} else {
								updateView();
								firstClick(pos);
							}
						}
					}
				} else {
					// they clicked a menu item
					if (view.getExitItem() == e.getSource()) {
						System.exit(0);
					}

					if (view.getSettingItem() == e.getSource()) {
						new SettingsDialog(view.getFrame(), view);
					}

					if (view.getNewGameItem() == e.getSource()) {
						view.close();
						game = new ChessGame();
						view = new ChessView(game.getBoard().numRows(), game
								.getBoard().numColumns());
						new ChessPresenter(game, view);
					}

					if (view.getAboutItem() == e.getSource()) {
						try {
							new AboutDialog(view.getFrame());
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}

				}
			}
		});
	}

	/*****************************************************************
	 * Calls for the action.
	 * 
	 * @param pos
	 *            the array for position of the piece
	 * @param e
	 *            the action listener giving functionality
	 *****************************************************************/
	private void legalMove(final int[] pos, final ActionListener e) {
		coords[TR] = pos[ROW];
		coords[TC] = pos[COL];
		onInput(e);
	}

	/*****************************************************************
	 * Called after the first click on the board.
	 * 
	 * @param pos
	 *            the array for position of the piece
	 *****************************************************************/
	private void firstClick(final int[] pos) {
		if (game.getBoard().pieceAt(pos[ROW], pos[COL]) != null) {
			if (game.getBoard().pieceAt(pos[ROW], pos[COL]).player() == game
					.getModel().currentPlayer()) {
				int count = getAndShowLegalMoves(pos[ROW], pos[COL]);
				if (count != 0) {
					firstClick = false;
					coords[FR] = pos[ROW];
					coords[FC] = pos[COL];
					view.showSelected(pos[ROW], pos[COL],
							sendPiece(pos[ROW], pos[COL]));
				}
			}
		}
	}

	/*****************************************************************
	 * Shows legal moves on the board.
	 * 
	 * @param row
	 *            the row available
	 * @param col
	 *            the column available
	 * 
	 * @return count
	 *****************************************************************/
	private int getAndShowLegalMoves(final int row, final int col) {
		int count = 0;
		boolean legal = view.isShowLegal();
		for (int i = 0; i < game.getBoard().numRows(); i++) {
			for (int j = 0; j < game.getBoard().numColumns(); j++) {
				Move m = new Move(row, col, i, j);
				if (game.getModel().isValidMove(m, game.getBoard())) {
					if (legal) {
						view.paintLegalMove(i, j, sendPiece(i, j));
					}
					count++;
				}
			}
		}
		return count;
	}

	/*****************************************************************
	 * Calls for the action.
	 * 
	 *****************************************************************/
	private void updateView() {
		for (int i = 0; i < game.getBoard().numRows(); i++) {
			for (int j = 0; j < game.getBoard().numColumns(); j++) {
				view.setData(i, j, sendPiece(i, j));
			}
		}
	}

	/*****************************************************************
	 * Calls for the action.
	 * 
	 * @param a
	 *            the action listener
	 *****************************************************************/
	public final void onInput(final ActionListener a) {
		try {

			game.setMove(coords); // set the move to be made

				if (game.getModel()
						.isValidMove(game.getMove(), game.getBoard())) {
					game.getModel().move(game.getMove(), game.getBoard());
				}
				updateView();
				if (game.getModel().isComplete(game.getBoard())
						&& !game.getModel().inStaleMate(game.getBoard())) {
					String winner;
					if (game.getModel().currentPlayer() == Player.BLACK) {
						winner = "WHITE";
					} else {
						winner = "BLACK";
					}
					view.showMessage(winner + " wins!");
					view.disable(a);
				}
				if (game.getModel().inStaleMate(game.getBoard())) {
					view.showMessage("Stalemate.");
					view.disable(a);
				}
		} catch (Exception e) {
			view.showMessage(e.getMessage());
		}
	}

	/*****************************************************************
	 * Sends piece to a new position.
	 * 
	 * @param row
	 *            the new row position of the piece
	 * @param column
	 *            the new column position of the piece
	 * 
	 * @return piecenum
	 *****************************************************************/
	public final int[] sendPiece(final int row, final int column) {
		int[] pieceNum = new int[PIECE_INFO];
		Player p;
		try {
			p = game.getBoard().pieceAt(row, column).player();
		} catch (Exception e) {
			pieceNum[OWNER] = NULL_OWNER;
			pieceNum[TYPE] = NULL_TYPE;
			return pieceNum;
		}
		if (p == Player.WHITE) {
			pieceNum[OWNER] = WHV;
		} else {
			pieceNum[OWNER] = BLV;
		}

		String type = game.getBoard().pieceAt(row, column).type();
		switch (type) {
		case "Pawn":
			pieceNum[TYPE] = PV;
			break;
		case "Bishop":
			pieceNum[TYPE] = BV;
			break;
		case "Knight":
			pieceNum[TYPE] = NV;
			break;
		case "Queen":
			pieceNum[TYPE] = QV;
			break;
		case "King":
			pieceNum[TYPE] = KV;
			break;
		default:
			pieceNum[TYPE] = RV;
			break;
		}

		return pieceNum;

	}

	/*****************************************************************
	 * Main method that starts the chess game.
	 * 
	 * @param args 
	 * 			a String array of arguments passed to the program
	 *****************************************************************/
	public static void main(final String[] args) {
		ChessGame g = new ChessGame();
		ChessView v = new ChessView(g.getBoard().numRows(), g.getBoard()
				.numColumns());
		new ChessPresenter(g, v);
	}
}
