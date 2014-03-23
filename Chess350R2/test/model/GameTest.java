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
	
	@Test
	public final void testMove() {
		Move m = new Move(0, 1, 2, 3);
		
		ChessGame game = new ChessGame();
		game.setMove(m);
		Move b = game.getMove();
		
		assertEquals(m.getFromRow(), b.getFromRow());
		assertEquals(m.getFromColumn(), b.getFromColumn());
		assertEquals(m.getToRow(), b.getToRow());
		assertEquals(m.getToColumn(), b.getToColumn());
		
		int[] t = {0, 1, 2, 3 };
		game.setMove(t);
		
		m = game.getMove();
		assertEquals(m.getFromRow(), 0);
		assertEquals(m.getFromColumn(), 1);
		assertEquals(m.getToRow(), 2);
		assertEquals(m.getToColumn(), 3);
		
		
	}
}
