package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameTest {

	@Test
	public void testGame(){
		ChessGame game = new ChessGame();
		ChessModel model = new ChessModel();
		ChessBoard board = new ChessBoard();
		
		game.setModel(model);
		assertEquals(game.getModel(), model);
		
		game.setBoard(board);
		assertEquals(game.getBoard(), board);
	}
}
