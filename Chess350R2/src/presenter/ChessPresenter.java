package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;

import model.ChessGame;
import model.ComputerPlayer;
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
	private boolean onePlayer;
	private int cpuStyle;
	private static final int RANDOM = 0;
	private static final int ATTACK = 1;
	private static final int DEF = 2;

	/*****************************************************************
	 * A constructor for the Presenter.
	 * 
	 * @param g
	 *            the game variable
	 * @param v
	 *            the view variable
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
						if (game.getBoard().pieceAt(pos[ROW], pos[COL]) == null) {
							legalMove(pos, this);
						} else {
							if (game.getBoard().pieceAt(pos[ROW], pos[COL])
									.player() != game.getModel()
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
					} else if (view.getSettingItem() == e.getSource()) {
						new SettingsDialog(view.getFrame(), view);
					} else if (view.getNewGameItem() == e.getSource()) {
						view.close();
						game = new ChessGame();
						view = new ChessView(game.getBoard().numRows(), game
								.getBoard().numColumns());
						new ChessPresenter(game, view, onePlayer, cpuStyle);
					} else if (view.getAboutItem() == e.getSource()) {
						try {
							new AboutDialog(view.getFrame());
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					} else if (view.getUndoItem() == e.getSource()) {
						undo();
					} else if (view.getMusicItem() == e.getSource()){
						if (view.getMusicItem().getText().equals("Stop Music")){
							view.getMusicItem().setText("Play Music");
							stopMusic();
						} else {
							view.getMusicItem().setText("Stop Music");
							playMusic();
						}
					}

				}
			}
		});
	}
	
	private void playMusic(){
		view.playMusic();
	}
	private void stopMusic(){
		view.stopMusic();
	}

    /*****************************************************************
	 * Saves the board state to a file
	 * @throws IOException
	 *
	 *
	 *****************************************************************/
	private void saveGame() throws IOException {
		try {
			// Create an array containing our board data to be saved
			Object[] data = {game, view, onePlayer, cpuStyle};
			File file = new File("chessSave");
			// Create a file location and save our data there
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(data);
			oos.close();
			System.out.println("Done");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*****************************************************************
	 * Loads a game state from a file
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *
	 *
	 *****************************************************************/
	private void loadGame(String fileLocation) throws IOException, ClassNotFoundException {
		try {
			FileInputStream in = new FileInputStream(fileLocation);
			ObjectInputStream s = new ObjectInputStream(in);
			Object[] data = (Object[]) s.readObject();
			s.close();
			new ChessPresenter((ChessGame) data[0], (IChessView) data[1], (boolean) data[2], (int) data[3]);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
	 *****************************************************************/
	private void updateView() {
		for (int i = 0; i < game.getBoard().numRows(); i++) {
			for (int j = 0; j < game.getBoard().numColumns(); j++) {
				view.setData(i, j, sendPiece(i, j));
			}
		}
		showLastMove();
	}
	
	private void showLastMove(){
		if(view.isShowLast()){
			Move m = game.getLastMove();
			if (m != null){
			view.paintLastMove(m.getFromRow(), m.getFromColumn());
			view.paintLastMove(m.getToRow(), m.getToColumn(), sendPiece(m.getToRow(), m.getToColumn()));
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

		if (game.getModel().isValidMove(game.getMove(), game.getBoard())) {
			game.getModel().move(game.getMove(), game.getBoard(),
					game.getChessStack());
			game.pushMove(game.getMove());
		}
		afterMove(a);
		if (onePlayer && !game.getModel().isComplete(game.getBoard())) {
			computerMove(a);
			afterMove(a);
		}
	}

	private final void afterMove(final ActionListener a) {
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
			view.showMessage("Stalemate", "The Game ended in a checkmate.");
		}
	}

	private final void computerMove(final ActionListener al) {
		// it is now the computer players turn
		ComputerPlayer cp = new ComputerPlayer();
		Move m;
		if (cpuStyle == RANDOM) {
			m = cp.getRandomMove(game.getModel(), game.getBoard());
		} else if (cpuStyle == ATTACK) {
			m = cp.getAttackMove(game.getModel(), game.getBoard());
		} else { //(cpuStyle == DEF) 
			m = cp.getDefensiveMove(game.getModel(), game.getBoard());
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

	public void undo() {
		if(onePlayer){
			game.undo2();
			updateView();
		} else {
			game.undo();
			updateView();
		}
	}
	
}
