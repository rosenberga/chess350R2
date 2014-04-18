package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.ChessGame;
import view.ChessView;
import view.IChessView;
import view.StartScreen;

/*****************************************************************
 * A Presenter of information to the Model and View.
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public class StartPresenter {

	/*****************************************************************
	 * A constructor for the StartPresenter.
	 *****************************************************************/
	public StartPresenter() {
		final StartScreen ss = new StartScreen();
		final ChessGame g = new ChessGame();
		ss.addActionListeners(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (e.getSource() == ss.getTwoPlayer()) {
					ss.setVisible(false);
					IChessView v = new ChessView(g.
							getBoard().numRows(),
							g.getBoard()
							.numColumns());
					new ChessPresenter(g, v, false, 0);
				} else if (e.getSource() == ss.getOnePlayer()) {
					ss.changePanels();
				} else if (e.getSource() == ss.getStyleOne()) {
					ss.setVisible(false);
					IChessView v = new ChessView(g.
							getBoard().numRows(),
							g.getBoard()
							.numColumns());
					new ChessPresenter(g, v, true, 0);
				} else if (e.getSource() == ss.getStyleTwo()) {
					ss.setVisible(false);
					IChessView v = new ChessView(g.
							getBoard().numRows(),
							g.getBoard()
							.numColumns());
					new ChessPresenter(g, v, true, 1);
				} else if (e.getSource()
						== ss.getStyleThree()) {
					ss.setVisible(false);
					IChessView v = new ChessView(g.
							getBoard().numRows(),
							g.getBoard()
							.numColumns());
					new ChessPresenter(g, v, true, 2);
				} else if (e.getSource()
						== ss.getLoadGame()) {
					ss.setVisible(false);
					IChessView v = new ChessView(g.
							getBoard().numRows(),
							g.getBoard()
							.numColumns());
					ChessPresenter cp
					= new ChessPresenter(g,
							v, false, 0);
					try {
						cp.loadGame("chessSave.ser");
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}


	/*****************************************************************
	 * Main method that starts the chess game.
	 *
	 * @param args
	 *            a String array of arguments passed to the program
	 *****************************************************************/
	public static void main(final String[] args) {
		new StartPresenter();
	}
}
