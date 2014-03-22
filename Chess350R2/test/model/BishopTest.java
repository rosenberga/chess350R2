package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BishopTest {

	ChessModel mainmodel;
	ChessBoard mainboard;
	Move move1;
	
	//******************************************************Test Bishop
		@Test
		public void testBishop1() {
			// move the bishop diagonal 3 spaces
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Bishop(Player.BLACK), 2,0);
			move1 = new Move(2,0,5,3);
			mainmodel.setTurns(1); // so its black's turn
			assertTrue (mainmodel.isValidMove(move1, mainboard));
			mainmodel.move(move1, mainboard);
			assertTrue(mainmodel.pieceAt(mainboard,5,3).getMoved());
		}

		@Test
		public void testBishop2() {
			// move the bishop right 3 spaces
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Bishop(Player.BLACK), 2,0);
			move1 = new Move(2,0,2,3);
			assertFalse (mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public void testBishop3() {
			// move the bishop up 1 spaces
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Bishop(Player.BLACK), 2,0);
			move1 = new Move(2,0,3,0);
			assertFalse (mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public void testBishop4() {
			// take a white piece
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Bishop(Player.BLACK), 2,0);
			mainboard.set(new Bishop(Player.WHITE), 5,3);
			move1 = new Move(2,0,5,3);
			mainmodel.setTurns(1); // so its black's turn
			assertTrue (mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public void testBishop5() {
			// attempt to take black piece
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Bishop(Player.BLACK), 2,0);
			mainboard.set(new Bishop(Player.BLACK), 5,3);
			move1 = new Move(2,0,5,3);
			assertFalse (mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public void testBishop6() {
			// attempt to move through black piece
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Bishop(Player.BLACK), 2,0);
			mainboard.set(new Bishop(Player.BLACK), 4,2);
			move1 = new Move(2,0,5,3);
			assertFalse (mainmodel.isValidMove(move1, mainboard));
		}

}
