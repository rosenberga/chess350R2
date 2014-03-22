package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PawnTest {

	ChessModel mainmodel;
	ChessBoard mainboard;
	Move move1;
	
	@Test
	public void test() {
		ChessModel model = new ChessModel();
		ChessBoard board = new ChessBoard();
		
		// test pawn movement in a general game of chess
		assertTrue(model.isValidMove(new Move(6,0,4,0), board));
		model.move(new Move(6,0,4,0), board);
		assertTrue(model.isValidMove(new Move(1,7,3,7), board));
		model.move(new Move(1,7,3,7), board);
		assertTrue(model.isValidMove(new Move(4,0,3,0), board));
		model.move(new Move(4,0,3,0), board);
		assertTrue(model.isValidMove(new Move(3,7,4,7), board));
		model.move(new Move(3,7,4,7), board);
		assertTrue(model.isValidMove(new Move(6,6,4,6), board));
		model.move(new Move(6,6,4,6), board);
		
		// tests en passant
		assertTrue(model.isValidMove(new Move(4,7,5,6), board));
		model.move(new Move(4,7,5,6), board);
		assertNull(model.pieceAt(board, 4, 6));
		assertEquals(model.pieceAt(board, 5,6).type(),"Pawn");
		assertEquals(model.pieceAt(board, 5,6).player(),Player.BLACK);
		assertTrue(model.isValidMove(new Move(6,5,5,6), board));
		
		// test capture
		model.move(new Move(6,5,5,6), board);
		assertEquals(model.pieceAt(board, 5,6).type(),"Pawn");
		assertEquals(model.pieceAt(board, 5,6).player(),Player.WHITE);
		assertTrue(model.isValidMove(new Move(1,1,3,1), board));
		model.move(new Move(1,1,3,1), board);
		assertTrue(model.isValidMove(new Move(3,0,2,1), board));
		model.move(new Move(3,0,2,1), board);
		
		// test en passant
		assertNull(model.pieceAt(board, 3,1));
		assertEquals(model.pieceAt(board, 2,1).type(),"Pawn");
		assertEquals(model.pieceAt(board, 2,1).player(),Player.WHITE);
		
		assertTrue(model.isValidMove(new Move(1,6,2,6), board));
		assertTrue(model.isValidMove(new Move(1,6,3,6), board));
		model.move(new Move(1,6,3,6), board);
		assertTrue(model.isValidMove(new Move(6,1,4,1), board));
		assertTrue(model.isValidMove(new Move(6,1,5,1), board));
		model.move(new Move(6,1,5,1), board);
		assertTrue(model.isValidMove(new Move(1,3,3,3), board));
		model.move(new Move(1,3,3,3), board);
		assertTrue(model.isValidMove(new Move(5,1,4,1), board));
		model.move(new Move(5,1,4,1), board);
		assertTrue(model.isValidMove(new Move(3,3,4,3), board));
		model.move(new Move(3,3,4,3), board);
		
		// tests capture
		assertEquals(model.pieceAt(board, 1,2).type(),"Pawn");
		assertEquals(model.pieceAt(board, 1,2).player(),Player.BLACK);
		assertEquals(model.pieceAt(board, 2,1).type(),"Pawn");
		assertEquals(model.pieceAt(board, 2,1).player(),Player.WHITE);
		assertTrue(model.isValidMove(new Move(2,1,1,0), board));
		assertTrue(model.isValidMove(new Move(2,1,1,2), board));
		assertEquals(model.pieceAt(board, 1,2).player(),Player.BLACK);
		model.move(new Move(2,1,1,2), board);
		assertTrue(model.pieceAt(board, 1,2).type().equals("Pawn"));
		assertEquals(model.pieceAt(board, 1,2).player(),Player.WHITE);
		assertEquals(model.pieceAt(board, 1, 5).type(),"Pawn");
		assertEquals(model.pieceAt(board, 1, 5).player(),Player.BLACK);
		
		// tests general pawn movement
		assertFalse(model.isValidMove(new Move(1,5,1,6), board));
		assertFalse(model.isValidMove(new Move(1,5,1,4), board));
		assertFalse(model.isValidMove(new Move(1,5,0,5), board));
		assertFalse(model.isValidMove(new Move(1,5,4,6), board));
		assertTrue(model.isValidMove(new Move(1,5,2,5), board));
		model.move(new Move(1,5,2,5), board);
		assertTrue(model.isValidMove(new Move(4,1,3,1), board));
		model.move(new Move(4,1,3,1), board);
		assertTrue(model.isValidMove(new Move(2,5,3,5), board));
		model.move(new Move(2,5,3,5), board);
		assertTrue(model.isValidMove(new Move(3,1,2,1), board));
		model.move(new Move(3,1,2,1), board);
		assertTrue(model.isValidMove(new Move(1,0,2,1), board));
		assertTrue(model.pieceAt(board, 2,1).type().equals("Pawn"));
		assertEquals(model.pieceAt(board, 2,1).player(),Player.WHITE);
		model.move(new Move(1,0,2,1), board);
		assertTrue(model.pieceAt(board, 2,1).type().equals("Pawn"));
		assertEquals(model.pieceAt(board, 2,1).player(),Player.BLACK);
		
		// tests that will test bad pawn movement
		model = new ChessModel();
		board = new ChessBoard();
		assertFalse(model.isValidMove(new Move(6,0,7,0), board));
		assertFalse(model.isValidMove(new Move(6,0,3,0), board));
		assertFalse(model.isValidMove(new Move(6,0,6,1), board));
		assertFalse(model.isValidMove(new Move(6,0,6,-1), board));
		assertFalse(model.isValidMove(new Move(6,0,5,1), board));
		assertFalse(model.isValidMove(new Move(6,0,5,-1), board));
		assertFalse(model.isValidMove(new Move(6,3,5,2), board));
		assertFalse(model.isValidMove(new Move(6,3,5,4), board));
		assertTrue(model.isValidMove(new Move(6,0,5,0), board));
		assertTrue(model.isValidMove(new Move(6,0,4,0), board));
		board.set(new Pawn(Player.BLACK), 5,0);
		assertFalse(model.isValidMove(new Move(6,0,5,0), board));
		assertFalse(model.isValidMove(new Move(6,0,4,0), board));
		model.setTurns(1);
		assertFalse(model.isValidMove(new Move(1,0,0,0), board));
		assertFalse(model.isValidMove(new Move(1,0,4,0), board));
		assertFalse(model.isValidMove(new Move(1,0,1,1), board));
		assertFalse(model.isValidMove(new Move(1,0,1,-1), board));
		assertFalse(model.isValidMove(new Move(1,0,2,1), board));
		assertFalse(model.isValidMove(new Move(1,0,2,-1), board));
		assertFalse(model.isValidMove(new Move(1,3,2,4), board));
		assertFalse(model.isValidMove(new Move(1,3,2,2), board));
		assertTrue(model.isValidMove(new Move(1,0,2,0), board));
		assertTrue(model.isValidMove(new Move(1,0,3,0), board));
		board.set(new Pawn(Player.WHITE), 2, 0);
		assertFalse(model.isValidMove(new Move(1,0,2,0), board));
		assertFalse(model.isValidMove(new Move(1,0,3,0), board));
		model.setTurns(-1);
		board.unset(5, 0);
		board.unset(2, 0);
		
		// tests moving two spaces up after first move
		assertTrue(model.isValidMove(new Move(6,0,5,0), board));
		model.move(new Move(6,0,5,0), board);
		assertTrue(model.isValidMove(new Move(1,0,2,0), board));
		model.move(new Move(1,0,2,0), board);
		assertFalse(model.isValidMove(new Move(5,0,3,0), board));
		
		// this should fail because it is not black's turn to move
		assertFalse(model.isValidMove(new Move(2,0,3,0), board));
		model.setTurns(1);
		assertFalse(model.isValidMove(new Move(2,0,4,0), board));
		
		// reset and test bad en passant
		model = new ChessModel();
		board = new ChessBoard();
		model.move(new Move(6,0,4,0), board);
		model.move(new Move(1,1,2,1), board);
		model.move(new Move(4,0,3,0), board);
		model.move(new Move(2,1,3,1), board);
		assertFalse(model.isValidMove(new Move(3,0,2,1), board));
		
		// tests that pawn cant take piece sideways
		assertFalse(model.isValidMove(new Move(3,0,3,1), board));
		
	}
	//******************************************************Test Pawn
		@Test
		public void testPawn1() {
			// move the pawn up 1 space
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			move1 = new Move(1,0,2,0);
			mainmodel.setTurns(1); // so its black's turn
			assertTrue (mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public void testPawn2() {
			// move the pawn up 2 spaces first turn
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new Pawn(Player.BLACK), 1,0);
			//System.out.println(mainmodel.pieceAt(mainboard, 0,1).type());
			move1 = new Move(1,0,3,0);
			//mainmodel.move(move1, mainboard);
			//System.out.println(mainmodel.pieceAt(mainboard, 0,3).type());
			mainmodel.setTurns(1); // so its black's turn
			assertTrue (mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public void testPawn3() {
			// move the pawn up 2 spaces second turn
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			move1 = new Move(1,0,2,0);
			mainmodel.move(move1, mainboard); // move black pawn
			//move1 = new Move(6,7,5,7);
			//mainmodel.move(move1, mainboard); // move white pawn
			move1 = new Move(2,0,4,0);		  // move black pawn again 2 spaces
			assertFalse (mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public void testPawn4() {
			// move the pawn diagonal 1 space
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			move1 = new Move(1,0,2,1);
			assertFalse (mainmodel.isValidMove(move1, mainboard));
		}
		
		@Test
		public void testPawn5() {
			// move the pawn diagonal 1 to take piece
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Rook(Player.WHITE), 2,1);
			move1 = new Move(1,0,2,1);
			mainmodel.setTurns(1); // so its black's turn
			assertTrue (mainmodel.isValidMove(move1, mainboard));
		}
		
		@Test
		public void testPawn6() {
			// move the pawn up 1 space to take piece
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Pawn(Player.BLACK), 1, 0);
			//System.out.println(mainmodel.pieceAt(mainboard, 1,0).type());
			mainboard.set(new Rook(Player.WHITE), 2,0);
			//System.out.println(mainmodel.pieceAt(mainboard, 2,0).type());
			move1 = new Move(1,0,2,0);
			//mainmodel.move(move1, mainboard);
			assertFalse (mainmodel.isValidMove(move1, mainboard));
			//System.out.println(mainmodel.pieceAt(mainboard, 2,0).type());
		}
		
		@Test
		public void testPawn7() {
			// move the pawn up 1 space to take piece
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.set(new Pawn(Player.BLACK), 1, 0);
			//System.out.println(mainmodel.pieceAt(mainboard, 1,0).type());
			mainboard.set(new Rook(Player.WHITE), 3,0);
			//System.out.println(mainmodel.pieceAt(mainboard, 2,0).type());
			move1 = new Move(1,0,3,0);
			//mainmodel.move(move1, mainboard);
			assertFalse (mainmodel.isValidMove(move1, mainboard));
			//System.out.println(mainmodel.pieceAt(mainboard, 2,0).type());
		}
		
		//******************************************************Test En Passant
		@Test
		public void testEnPassant1() {
			// validate ability to use En Passant rule
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainmodel.setTurns(1);
			move1 = new Move(1,7,3,7); // move black pawn 2
			mainmodel.move(move1, mainboard);
			move1 = new Move(6,0,4,0); // move other white pawn 2
			mainmodel.move(move1, mainboard);
			move1 = new Move(3,7,4,7); // move black pawn 1
			mainmodel.move(move1, mainboard);
			//cheat death with doomed white pawn
			move1 = new Move(6,6,4,6);
			mainmodel.move(move1, mainboard);
			//prevent cheating of death with en passant here
			move1 = new Move(4,7,5,6);
			//System.out.println(mainmodel.pieceAt(mainboard, 4,7).type());
			//mainmodel.move(move1, mainboard);
			//System.out.println(mainmodel.pieceAt(mainboard, 5,6).type());
			//System.out.println(mainboard.getBoard()[4][6]);
			assertTrue (mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public void testEnPassant2() {
			// make sure unusable beyond correct conditions
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainmodel.setTurns(1);
			move1 = new Move(1,7,3,7); // move black pawn 2
			mainmodel.move(move1, mainboard);
			move1 = new Move(6,0,4,0); // move  other white pawn 2
			mainmodel.move(move1, mainboard);
			move1 = new Move(3,7,4,7); // move black pawn 1
			mainmodel.move(move1, mainboard);
			//cheat death with doomed white pawn
			move1 = new Move(6,6,4,6);
			mainmodel.move(move1, mainboard);
			move1 = new Move(0,7,1,7); // move black rook
			mainmodel.move(move1, mainboard);
			move1 = new Move(4,0,5,0); // move other white pawn
			mainmodel.move(move1, mainboard);
			//try to prevent cheating of death of doomed white pawn
			move1 = new Move(4,7,5,6);
			assertFalse (mainmodel.isValidMove(move1, mainboard)); // en passant not allowed
			
		}
}
