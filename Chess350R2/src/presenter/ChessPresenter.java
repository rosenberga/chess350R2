package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JButton;
import model.ChessGame;
import model.ComputerPlayer;
import model.IChessPiece;
import model.Move;
import model.Player;
import view.AboutDialog;
import view.ChessView;
import view.IChessView;
import view.SettingsDialog;

/*****************************************************************
 * A Presenter of information to the Model and View.
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public class ChessPresenter implements IChessPresenter, Serializable {

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
	/** 1v1 or 1vCPU? */
	private boolean onePlayer;
	/** Offensive or defensive CPU or neither? */
	private int cpuStyle;
	/** CPU moves randomly. */
	private static final int RANDOM = 0;
	/** CPU moves offensively. */
	private static final int ATTACK = 1;
	/** CPU moves defensively. */
	private static final int DEF = 2;

	/*****************************************************************
	 * A constructor for the Presenter.
	 *
	 * @param g
	 *            the game variable
	 * @param v
	 *            the view variable
	 * @param single
	 *            CPU or two humans
	 * @param style
	 *            CPU's playstyle
	 *****************************************************************/
	public ChessPresenter(final ChessGame g, final IChessView v,
			final boolean single, final int style) {
		game = g;
		view = v;
		firstClick = true;
		coords = new int[COORDS];
		onePlayer = single;
		cpuStyle = style;
		playMusic();
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
						if (game.getBoard().pieceAt(
								pos[ROW],
								pos[COL])
								== null) {
							legalMove(pos, this);
						} else {
							if (game.getBoard()
								  .pieceAt(
							     pos[ROW],
							     pos[COL])
								 .player()
						!= game.getModel()
						.currentPlayer()) {
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
					} else if (view.getSettingItem()
							== e.getSource()) {
						new SettingsDialog(
								view.getFrame(),
								view);
					} else if (view.getNewGameItem()
							== e.getSource()) {
						stopMusic();
						view.close();
						game = new ChessGame();
						view = new ChessView(
								game.getBoard().
								numRows(), game
								.getBoard().
								numColumns());
						new ChessPresenter(game,
								view, onePlayer,
								cpuStyle);
					} else if (view.getAboutItem()
							== e.getSource()) {
						try {
							new AboutDialog(view.
								getFrame());
						} catch (
						FileNotFoundException e1) {
							e1.printStackTrace();
						}
					} else if (view.getUndoItem()
							== e.getSource()) {
						undo();
					} else if (view.getMusicItem()
							== e.getSource()) {
						if (view.getMusicItem().
								getText().
								equals(
								"Stop Music")) {
							view.getMusicItem().
							setText("Play Music");
							stopMusic();
						} else {
							view.getMusicItem().
							setText("Stop Music");
							playMusic();
						}
					} else if (view.getSaveItem()
							== e.getSource()) {
						try {
							saveGame();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else if (view.getLoadItem()
							== e.getSource()) {
						try {
							loadGame(
							"chessSave.ser");
						} catch (
						 ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}

				}
			}
		});
	}

    /*****************************************************************
	 * Plays background music.
	 *
	 *
	 *****************************************************************/
	private void playMusic() {
		view.playMusic();
	}

    /*****************************************************************
	 * Stops background music.
	 *
	 *
	 *****************************************************************/
	private void stopMusic() {
		view.stopMusic();
	}

    /*****************************************************************
	 * Saves the board state to a file.
	 * @throws IOException
	 *****************************************************************/
	private void saveGame() throws IOException {
		try {
			// Create an array containing our board data to be saved
			Object[] data = {game, onePlayer, cpuStyle};
			File file = new File("chessSave.ser");
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(data);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*****************************************************************
	 * Loads a game state from a file.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *
	 *
	 *****************************************************************/
	public final void loadGame(final String fileLocation)
			throws IOException, ClassNotFoundException {
		try {
			view.close();
			stopMusic();
			FileInputStream in = new FileInputStream(fileLocation);
			ObjectInputStream s = new ObjectInputStream(in);
			Object[] data = (Object[]) s.readObject();
			s.close();
			ChessGame cg = (ChessGame) data[0];
			boolean one = (boolean) data[1];
			int cpu = (int) data[2];
			view = new ChessView(cg.getBoard().
					numRows(),
					cg.getBoard().numColumns());
			new ChessPresenter(cg, view, one, cpu);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
			if (game.getBoard().pieceAt(pos[ROW], pos[COL]).player()
					== game
					.getModel().currentPlayer()) {
				int count = getAndShowLegalMoves(pos[ROW],
						pos[COL]);
				if (count != 0) {
					firstClick = false;
					coords[FR] = pos[ROW];
					coords[FC] = pos[COL];
					view.showSelected(pos[ROW], pos[COL],
							sendPiece(pos[ROW],
								pos[COL]));
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
				if (game.getModel().isValidMove(m,
						game.getBoard())) {
					if (legal) {
						view.paintLegalMove(i, j,
							sendPiece(i, j));
					}
					count++;
				}
			}
		}
		return count;
	}

	/*****************************************************************
	 * Calls for the action.
	 *****************************************************************/
	private void updateView() {
		for (int i = 0; i < game.getBoard().numRows(); i++) {
			for (int j = 0; j < game.getBoard().numColumns(); j++) {
				view.setData(i, j, sendPiece(i, j));
			}
		}
		showLastMove();
		reboundGraves();
	}

	/*****************************************************************
	 * Visually shows the user the last move performed.
	 *****************************************************************/
	private void showLastMove() {
		if (view.isShowLast()) {
			Move m = game.getLastMove();
			if (m != null) {
			view.paintLastMove(m.getFromRow(), m.getFromColumn());
			view.paintLastMove(m.getToRow(), m.getToColumn(),
					sendPiece(m.getToRow(),
							m.getToColumn()));
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

		game.setMove(coords); // set the move to be made
		boolean wasL = false;
		if (game.getModel().isValidMove(game.getMove(),
				game.getBoard())) {
			game.getModel().move(game.getMove(), game.getBoard(), 
			        game.getChessStack());
			game.pushMove(game.getMove());
		    wasL = true;
		}
		afterMove(a);
        if (wasL && onePlayer && !game.getModel().isComplete(game.getBoard())) {
            computerMove(a);
            afterMove(a);
        }
	}

	/*****************************************************************
	 * Work to be done after a piece has been moved.
	 *
	 * @param a
	 *            the action listener
	 *****************************************************************/
	private void afterMove(final ActionListener a) {
		updateView();
		if (game.getModel().inCheckMate(game.getBoard())) {
			String winner;
			if (game.getModel().currentPlayer() == Player.BLACK) {
				winner = "WHITE";
			} else {
				winner = "BLACK";
			}
			view.showMessage("Checkmate", winner + " wins!");
		} else if (game.getModel().inStaleMate(game.getBoard())) {
			view.showMessage("Stalemate", "The Game "
					+ "ended in a checkmate.");
		}
	}

	/*****************************************************************
	 * Computer thinks of a move.
	 *
	 * @param al
	 *            the action listener
	 *****************************************************************/
	private void computerMove(final ActionListener al) {
		// it is now the computer players turn
		ComputerPlayer cp = new ComputerPlayer();
		Move m;
		if (cpuStyle == RANDOM) {
			m = cp.getRandomMove(game.getModel(), game.getBoard());
		} else if (cpuStyle == ATTACK) {
			m = cp.getAttackMove(game.getModel(), game.getBoard());
		} else { //(cpuStyle == DEF)
			m = cp.getDefensiveMove(game.getModel(),
					game.getBoard());
		}
		game.pushMove(m);
		game.getModel().move(m, game.getBoard(), game.getChessStack());
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
	 * Undoes the move just performed.
	 *****************************************************************/
	public final void undo() {
		if (onePlayer) {
			game.undo2();
			updateView();
		} else {
			game.undo();
			updateView();
		}
		updateGraves();
	}

	/*****************************************************************
	 * Calls for the action.
	 *****************************************************************/
	public final void reboundGraves() {
		//if (game.getModel().getWhiteGrave().size() > 0) {
			for (int i = 0; i < game.getModel().
					getWhiteGrave().size(); i++) {
				view.updateWhiteGrave(i,
						sendWhitePiece(game.getModel().
						getWhiteGravePiece(i)));
			}
		//}
		//if (game.getModel().getBlackGrave().size() > 0) {
			for (int i = 0; i < game.getModel().
					getBlackGrave().size(); i++) {
				view.updateBlackGrave(i,
						sendBlackPiece(game.getModel().
						getBlackGravePiece(i)));
			}
		//}
	}

	/*****************************************************************
	 * Sends the white piece.
	 * @return int[] returns an array of ints.
	 *****************************************************************/
	public final int[] sendWhitePiece(final IChessPiece toSend) {
		int[] pieceNum = new int[PIECE_INFO];
		Player p;
		try {
			p = toSend.player();
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

		String type = toSend.type();
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
	 * Sends the black piece.
	 * @return int[] returns an array of ints.
	 *****************************************************************/
	public final int[] sendBlackPiece(final IChessPiece toSend) {
		int[] pieceNum = new int[PIECE_INFO];
		Player p = toSend.player();
		try {
			p = toSend.player();
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

		String type = toSend.type();
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
     * Updates graves when an undo is done
     *****************************************************************/
	private void updateGraves() {
	    view.clearGraves();
	    reboundGraves();
	}
}
