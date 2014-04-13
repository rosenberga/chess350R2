package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ChessGame;
import view.ChessView;
import view.StartScreen;

public class StartPresenter {

	public StartPresenter() {
		final StartScreen ss = new StartScreen();
		final ChessGame g = new ChessGame();
		ss.addActionListeners(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e){
				if(e.getSource() == ss.getTwoPlayer()){
					ChessView v = new ChessView(g.getBoard().numRows(), g.getBoard()
							.numColumns());
					new ChessPresenter(g, v, false, 0);
				} else if (e.getSource() == ss.getOnePlayer()) {
					ss.changePanels();
				} else if (e.getSource() == ss.getStyleOne()){
					ChessView v = new ChessView(g.getBoard().numRows(), g.getBoard()
							.numColumns());
					new ChessPresenter(g, v, true, 0);
				} else if (e.getSource() == ss.getStyleTwo()){
					ChessView v = new ChessView(g.getBoard().numRows(), g.getBoard()
							.numColumns());
					new ChessPresenter(g, v, true, 1);
				} else if (e.getSource() == ss.getStyleThree()){
					ChessView v = new ChessView(g.getBoard().numRows(), g.getBoard()
							.numColumns());
					new ChessPresenter(g, v, true, 2);
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

		/**
		 * while (true) {
		 * 
		 * // The mp3 file must be in the chess folder MP3 music = new
		 * MP3("ChopinNocturneOp.9No.2.mp3"); music.play(); try {
		 * TimeUnit.SECONDS.sleep(136); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 * 
		 * // When adding more music, please make sure to add: //
		 * TimeUnit.SECONDS.sleep(How many seconds the song plays); // after
		 * your code that plays the song. // Otherwise, your song will play on
		 * top of another mp3 }
		 */
	}
}
