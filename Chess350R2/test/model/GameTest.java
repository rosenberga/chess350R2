package model;


import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

	@Test
	public final void testGame() {
		ChessGame game = new ChessGame();
		ChessModel model = new ChessModel();
		ChessBoard board = new ChessBoard();
		
		game.setModel(model);
		assertEquals(game.getModel(), model);
		
		game.setBoard(board);
		assertEquals(game.getBoard(), board);
	}
}
