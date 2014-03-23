package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class KingTest {

	private ChessModel mainmodel;
	private ChessBoard mainboard;
	private Move move1;
	
	@Test
	public final void testKing1() {
		// move the king diagonal 1 spaces
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		mainboard.set(new King(Player.BLACK), 2, 0);
		move1 = new Move(2, 0, 3, 1);
		mainmodel.setTurns(1); // so its black's turn
		assertTrue(mainmodel.isValidMove(move1, mainboard));
	}

	@Test
	public final void testKing2() {
		// move the king right 1 spaces
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		mainboard.set(new King(Player.BLACK), 2, 0);
		move1 = new Move(2, 0, 2, 1);
		mainmodel.setTurns(1); // so its black's turn
		assertTrue(mainmodel.isValidMove(move1, mainboard));
	}
	@Test
	public final void testKing3() {
		// move the king diagonal 2 spaces
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		mainboard.set(new King(Player.BLACK), 2, 0);
		move1 = new Move(2, 0, 4, 2);
		assertFalse(mainmodel.isValidMove(move1, mainboard));
	}

	@Test
	public final void testKing4() {
		// move the king right 2 spaces
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		mainboard.set(new King(Player.BLACK), 2, 0);
		move1 = new Move(2, 0, 2, 2);
		assertFalse(mainmodel.isValidMove(move1, mainboard));
	}

	@Test
	public final void testKing5() {
		// take a white piece
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		mainboard.set(new King(Player.BLACK), 2, 0);
		mainboard.set(new Pawn(Player.WHITE), 3, 0);
		move1 = new Move(2, 0, 3, 0);
		mainmodel.setTurns(1); // so its black's turn
		assertTrue(mainmodel.isValidMove(move1, mainboard));
	}

	@Test
	public final void testKing6() {
		// attempt to take black piece
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		mainboard.set(new King(Player.BLACK), 2, 0);
		mainboard.set(new Pawn(Player.BLACK), 3, 0);
		move1 = new Move(2, 0, 3, 0);
		assertFalse(mainmodel.isValidMove(move1, mainboard));
	}
	
	//******************************************************Test Castling
		@Test
		public final void testCastling1() {
			// make sure it can be done
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.BLACK), 0, 4);
			mainboard.set(new Rook(Player.BLACK), 0, 7);
			//castle now
			move1 = new Move(0, 4, 0, 6);
			mainmodel.move(move1, mainboard);
			assertEquals(mainboard.pieceAt(0, 6).type(), "King");
			assertEquals(mainboard.pieceAt(0, 5).type(), "Rook");
		}

		@Test
		public final void testCastling2() {
			// make sure can't be done if rook moved
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.BLACK), 0, 4);
			mainboard.set(new Rook(Player.BLACK), 0, 7);
			move1 = new Move(0, 7, 1, 7);
			mainmodel.move(move1, mainboard);
			move1 = new Move(1, 7, 0, 7);
			mainmodel.move(move1, mainboard);
			//castle now
			move1 = new Move(0, 4, 0, 6);
			assertFalse(mainmodel.isValidMove(move1, mainboard));
		}
		
		@Test
		public final void testCastling3() {
			// make sure can't be done if king moved
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.BLACK), 0, 4);
			mainboard.set(new Rook(Player.BLACK), 0, 7);
			move1 = new Move(0, 4, 1, 4);
			mainmodel.move(move1, mainboard);
			move1 = new Move(1, 4, 0, 4);
			mainmodel.move(move1, mainboard);
			//castle now
			move1 = new Move(0, 4, 0, 6);
			assertFalse(mainmodel.isValidMove(move1, mainboard));
		}
		
		@Test
		public final void testCastling4() {
			// make sure can't be done if piece is in the way
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.BLACK), 0, 4);
			mainboard.set(new Rook(Player.BLACK), 0, 7);
			mainboard.set(new Bishop(Player.BLACK), 0, 5);
			move1 = new Move(0, 4, 0, 6);
			assertFalse(mainmodel.isValidMove(move1, mainboard));
		}
		
		@Test
		public final void testCastling5() {
			// make sure can't be done if piece is in the way
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.BLACK), 0, 4);
			mainboard.set(new Rook(Player.BLACK), 0, 7);
			mainboard.set(new Knight(Player.BLACK), 0, 6);
			move1 = new Move(0, 4, 0, 6);
			assertFalse(mainmodel.isValidMove(move1, mainboard));
		}
		
		@Test
		public final void testCastling6() {
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.WHITE), 7, 4);
			mainboard.set(new Rook(Player.WHITE), 7, 7);
			mainboard.set(new Rook(Player.WHITE), 7, 0);
			assertTrue(mainmodel.isValidMove(new Move(7, 4, 7, 6), mainboard));
			mainmodel.move(new Move(7, 4, 7, 6), mainboard);
			assertNotNull(mainmodel.pieceAt(mainboard, 7, 6));
			assertNotNull(mainmodel.pieceAt(mainboard, 7, 5));
		}
		
		@Test
		public final void testCastling7() {
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.WHITE), 7, 4);
			mainboard.set(new Rook(Player.WHITE), 7, 7);
			mainboard.set(new Rook(Player.WHITE), 7, 0);
			mainboard.set(new Queen(Player.WHITE), 7, 5);
			assertFalse(mainmodel.isValidMove(new Move(7, 4, 7, 6), mainboard));
		}
		
		@Test
		public final void testCastling8() {
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.WHITE), 7, 4);
			mainboard.set(new Rook(Player.WHITE), 7, 7);
			mainboard.set(new Rook(Player.WHITE), 7, 0);
			mainboard.set(new Queen(Player.BLACK), 0, 5);
			mainboard.set(new King(Player.BLACK), 0, 6);
			assertFalse(mainmodel.isValidMove(
					new Move(7, 4, 7, 6), mainboard));		
		}

		@Test
		public final void testCastling9() {
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.WHITE), 7, 4);
			mainboard.set(new Rook(Player.WHITE), 7, 7);
			mainboard.set(new Rook(Player.WHITE), 7, 0);
			assertTrue(mainmodel.isValidMove(new Move(7, 4, 7, 2), mainboard));
			mainmodel.move(new Move(7, 4, 7, 2), mainboard);
			assertNotNull(mainmodel.pieceAt(mainboard, 7, 2));
			assertNotNull(mainmodel.pieceAt(mainboard, 7, 3));
		}
		
		@Test
		public final void testCastling10() {
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.BLACK), 0, 4);
			mainboard.set(new Rook(Player.BLACK), 0, 7);
			mainboard.set(new Rook(Player.BLACK), 0, 0);
			mainmodel.setTurns(1); // so its black's turn
			assertTrue(mainmodel.isValidMove(new Move(0, 4, 0, 6), mainboard));
			mainmodel.move(new Move(0, 4 , 0, 6), mainboard);
			assertNotNull(mainmodel.pieceAt(mainboard, 0, 6));
			assertNotNull(mainmodel.pieceAt(mainboard, 0, 5));
		}
		
		@Test
		public final void testCastling11() {
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.BLACK), 0, 4);
			mainboard.set(new Rook(Player.BLACK), 0, 7);
			mainboard.set(new Rook(Player.BLACK), 0, 0);
			mainmodel.setTurns(1); // so its black's turn
			assertTrue(mainmodel.isValidMove(new Move(0, 4, 0, 2), mainboard));
			mainmodel.move(new Move(0, 4, 0, 2), mainboard);
			assertNotNull(mainmodel.pieceAt(mainboard, 0, 2));
			assertNotNull(mainmodel.pieceAt(mainboard, 0, 3));
		}
}
