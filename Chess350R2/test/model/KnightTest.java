package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class KnightTest {

	ChessModel mainmodel;
	ChessBoard mainboard;
	Move move1;
	
	//******************************************************Test Knight
		@Test
		public final void testKnight1() {
			// move the knight L-move up spaces
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Knight(Player.BLACK), 2, 0);
			move1 = new Move(2, 0, 4, 1);
			mainmodel.setTurns(1); // so its black's turn
			assertTrue(mainmodel.isValidMove(move1, mainboard));
			mainmodel.move(move1, mainboard);
			assertTrue(mainmodel.pieceAt(mainboard, 4, 1).getMoved());
			mainmodel.setTurns(1); // so its black's turn
			assertTrue(mainmodel.isValidMove(new Move(4, 1, 5, 3), mainboard));
		}

		@Test
		public final void testKnight2() {
			// move the knight right 3 spaces
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Knight(Player.BLACK), 2, 0);
			move1 = new Move(2, 0, 2, 3);
			assertFalse(mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public final void testKnight3() {
			// move the knight diagonal 1 spaces
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Knight(Player.BLACK), 2, 0);
			move1 = new Move(2, 0, 3, 1);
			assertFalse(mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public final void testKnight4() {
			// take a white piece
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Knight(Player.BLACK), 2, 0);
			mainboard.set(new Knight(Player.WHITE), 4, 1);
			move1 = new Move(2, 0, 4, 1);
			mainmodel.setTurns(1); // so its blacks turn
			assertTrue(mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public final void testKnight5() {
			// attempt to take black piece
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Knight(Player.BLACK), 2, 0);
			mainboard.set(new Knight(Player.BLACK), 4, 1);
			move1 = new Move(2, 0, 4, 1);
			assertFalse(mainmodel.isValidMove(move1, mainboard));
		}

}
