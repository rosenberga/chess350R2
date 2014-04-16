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
					ss.setVisible(false);
					new ChessPresenter(g, v, false, 0);
				} else if (e.getSource() == ss.getOnePlayer()) {
					ss.changePanels();
				} else if (e.getSource() == ss.getStyleOne()){
					ChessView v = new ChessView(g.getBoard().numRows(), g.getBoard()
							.numColumns());
					ss.setVisible(false);
					new ChessPresenter(g, v, true, 0);
				} else if (e.getSource() == ss.getStyleTwo()){
					ChessView v = new ChessView(g.getBoard().numRows(), g.getBoard()
							.numColumns());
					ss.setVisible(false);
					new ChessPresenter(g, v, true, 1);
				} else if (e.getSource() == ss.getStyleThree()){
					ChessView v = new ChessView(g.getBoard().numRows(), g.getBoard()
							.numColumns());
					ss.setVisible(false);
					new ChessPresenter(g, v, true, 2);
				}
			}
		});
		while(true){
			try {
				g.getModel().playMusic();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
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
