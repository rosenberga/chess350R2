package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.*;
import view.*;

public class ChessPresenter implements IChessPresenter {

	private IChessView view;
	private ChessGame game;
	private static final int PV = 0;
	private static final int BV = 1;
	private static final int NV = 2;
	private static final int RV = 3;
	private static final int QV = 4;
	private static final int KV = 5;
	private static final int BLV = 0;
	private static final int WHV = 1;
	private static final int OWNER = 0;
	private static final int TYPE = 1;
	private static final int PIECE_INFO = 2;
	private static final int NULL_OWNER = -1;
	private static final int NULL_TYPE = -1;
	private static final int COORDS = 4;
	private static final int fr = 0;
	private static final int fc = 1;
	private static final int tr = 2;
	private static final int tc = 3;
	private boolean firstClick;
	private int[] coords;
	private static final int ROW = 0;
	private static final int COL = 1;

	public ChessPresenter(ChessGame g, IChessView v) {
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
			public void actionPerformed(ActionEvent e) {

				// if they clicked a button, then it was a chess position
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
					}

					if (view.getSettingItem() == e.getSource()) {
						new SettingsDialog(view.getFrame(), view);
					}

					if (view.getNewGameItem() == e.getSource()) {
						view.close();
						game = new ChessGame();
						view = new ChessView(game.getBoard().numRows(), game
								.getBoard().numColumns());
						view.getFrame().dispose();
						new ChessPresenter(game, view);
					}

					if (view.getAboutItem() == e.getSource()) {
						view.showMessage("This chess game is in the top percentage of chess games.");
					}

				}
			}
		});
	}

	private void legalMove(int[] pos, ActionListener e) {
		coords[tr] = pos[ROW];
		coords[tc] = pos[COL];
		onInput(e);
	}

	private void firstClick(int[] pos) {
		if (game.getBoard().pieceAt(pos[ROW], pos[COL]) != null) {
			if (game.getBoard().pieceAt(pos[ROW], pos[COL]).player() == game
					.getModel().currentPlayer()) {
				int count = getAndShowLegalMoves(pos[ROW], pos[COL]);
				if (count != 0) {
					firstClick = false;
					coords[fr] = pos[ROW];
					coords[fc] = pos[COL];
					view.showSelected(pos[ROW], pos[COL],
							sendPiece(pos[ROW], pos[COL]));
				}
			} else {
				view.showMessage("You do not own this piece");
			}
		}
	}

	private int getAndShowLegalMoves(int row, int col) {
		int count = 0;
		boolean legal = view.isShowLegal();
		for (int i = 0; i < game.getBoard().numRows(); i++) {
			for (int j = 0; j < game.getBoard().numColumns(); j++) {
				Move m = new Move(row, col, i, j);
				if (game.getModel().isValidMove(m, game.getBoard())) {
					if (legal) {
						view.paintLegalMove(i, j);
					}
					count++;
				}
			}
		}
		return count;
	}

	private void updateView() {
		for (int i = 0; i < game.getBoard().numRows(); i++) {
			for (int j = 0; j < game.getBoard().numColumns(); j++) {
				view.setData(i, j, sendPiece(i, j));
			}
		}
	}

	public void onInput(ActionListener a) {
		try {

			game.setMove(coords); // set the move to be made

			if (game.getModel().isValidMove(game.getMove(), game.getBoard()) == false) {
				view.showMessage("Not a valid move.");
			} else { // acceptable move
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
		}

		catch (Exception e) {
			view.showMessage(e.getMessage());
		}
	}

	public int[] sendPiece(int row, int column) {
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

	public static void main(String[] args) {
		ChessGame g = new ChessGame();
		ChessView v = new ChessView(g.getBoard().numRows(), g.getBoard()
				.numColumns());
		new ChessPresenter(g, v);
	}
}
