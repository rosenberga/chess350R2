package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChessModelTest {

	ChessModel mainmodel, model;
	ChessBoard mainboard, board;
	Move move1;
	
	//******************************************************Test Promotion
	@Test
	public void testPromotionToRook() {
		ChessModel mainmodel = new ChessModel();
		ChessBoard mainboard = new ChessBoard();
		mainboard.clearBoard();
		Move move1 = new Move(6,0,7,0);
		mainmodel.promotion(mainboard, move1, new Rook(Player.WHITE));
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).type(),"Rook");
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).player(),Player.WHITE);
	}

	@Test
	public void testPromotionToKnight() {
		ChessModel mainmodel = new ChessModel();
		ChessBoard mainboard = new ChessBoard();
		mainboard.clearBoard();
		Move move1 = new Move(6,0,7,0);
		mainmodel.promotion(mainboard, move1, new Knight(Player.WHITE));
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).type(),"Knight");
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).player(),Player.WHITE);
	}

	@Test
	public void testPromotionToBishop() {
		ChessModel mainmodel = new ChessModel();
		ChessBoard mainboard = new ChessBoard();
		mainboard.clearBoard();
		Move move1 = new Move(6,0,7,0);
		mainmodel.promotion(mainboard, move1, new Bishop(Player.WHITE));
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).type(),"Bishop");
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).player(),Player.WHITE);
	}

	@Test
	public void testPromotionToQueen() {
		// promote pawn at space 8 on board
		ChessModel mainmodel = new ChessModel();
		ChessBoard mainboard = new ChessBoard();
		mainboard.clearBoard();
		Move move1 = new Move(6,0,7,0);
		mainboard.set(new Pawn(Player.BLACK), 6, 0);
		mainmodel.move(move1, mainboard);
		assertEquals (mainmodel.pieceAt(mainboard, 7, 0).type(),"Queen");
	}
	
	@Test
	public void testPromotionToQueen2() {
		ChessModel mainmodel = new ChessModel();
		ChessBoard mainboard = new ChessBoard();
		mainboard.clearBoard();
		Move move1 = new Move(6,0,7,0);
		mainmodel.promotion(mainboard, move1, new Queen(Player.WHITE));
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).type(),"Queen");
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).player(), Player.WHITE);
		
		// test to make sure king and pawn doesnt work
		mainmodel.promotion(mainboard, move1, new King(Player.WHITE));
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).type(),"Queen");
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).player(), Player.WHITE);
		
		mainmodel.promotion(mainboard, move1, new Pawn(Player.WHITE));
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).type(),"Queen");
		assertEquals(mainmodel.pieceAt(mainboard, 7, 0).player(), Player.WHITE);
	}
	//******************************************************Test Check		
		@Test
		public void testCheck1() {
			// make sure king can be moved
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.BLACK), 0, 0);
			mainboard.set(new Rook(Player.WHITE), 5, 0);
			move1 = new Move(0,0,0,1);
			mainboard.move(move1);
			assertEquals (mainmodel.pieceAt(mainboard, 0, 1).type(),"King");
		}
		
		@Test
		public void testCheck2() {
			// make sure king must be moved
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.BLACK), 0, 0);
			mainboard.set(new Rook(Player.WHITE), 5, 0);
			mainboard.set(new Rook(Player.BLACK), 7, 7);
			mainmodel.setTurns(1);
			move1 = new Move(7,7,0,7);
			mainmodel.setTurns(1); // so its black's turn
			assertFalse (mainmodel.isValidMove(move1, mainboard));
		}

		@Test
		public void testCheck3() {
			// use black piece to take white piece checking black king
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			//System.out.println(mainmodel.pieceAt(mainboard, 1,0).type());
			mainboard.clearBoard();
			mainboard.set(new King(Player.BLACK), 0, 0);
			mainboard.set(new Rook(Player.WHITE), 5, 0);
			mainboard.set(new Rook(Player.BLACK), 5, 5);
			move1 = new Move(5,5,5,0);
			mainmodel.setTurns(1); // so its black's turn
			assertTrue (mainmodel.isValidMove(move1, mainboard));
		}
		
		@Test
		public void testCheck4() {
			// use black piece to block white piece checking black king
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new King(Player.BLACK), 0, 0);
			mainboard.set(new Rook(Player.WHITE), 5, 0);
			mainboard.set(new Rook(Player.BLACK), 4, 5);
			move1 = new Move(4,5,4,0);
			mainmodel.setTurns(1); // so its black's turn
			assertTrue (mainmodel.isValidMove(move1, mainboard));
		}

		//******************************************************Test Checkmate
		@Test
		public void testCheckMate() {
			// test to see if the game is over
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			move1 = new Move(6,5,5,5); // move white pawn
			mainmodel.move(move1, mainboard);
			move1 = new Move(1,4,3,4); // move black pawn
			mainmodel.move(move1, mainboard);
			move1 = new Move(6,6,4,6); // move other white pawn
			mainmodel.move(move1, mainboard);
			move1 = new Move(0,3,4,7); // move black queen to checkmate white king
			mainmodel.move(move1, mainboard);
			assertTrue (mainmodel.isComplete(mainboard));
			assertFalse (mainmodel.inStaleMate(mainboard));
		}

		@Test
		public void testStalemate() {
			// player is not in check, but has no legal moves
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new Rook(Player.BLACK), 0,1);
			mainboard.set(new Rook(Player.BLACK), 0,3);
			mainboard.set(new King(Player.BLACK), 0,7);
			mainboard.set(new King(Player.WHITE), 7,2);
			assertFalse(mainmodel.isComplete(mainboard));
			assertFalse(mainmodel.inCheckMate(mainboard));
			assertFalse (mainmodel.inStaleMate(mainboard));
			assertTrue(mainmodel.isValidMove(new Move(7,2,6,2), mainboard));
			mainboard.set(new Queen(Player.BLACK), 6,0);
			assertFalse(mainmodel.isValidMove(new Move(7,2,6,2), mainboard));
			assertTrue (mainmodel.inStaleMate(mainboard));
			assertTrue(mainmodel.isComplete(mainboard));
			assertFalse(mainmodel.inCheckMate(mainboard));
		}
		
		@Test
		public void testOther(){
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			assertNotEquals(mainmodel.currentPlayer(),mainmodel.currentPlayer().next());
			mainmodel.setTurns(1);
			assertNotEquals(mainmodel.currentPlayer(),mainmodel.currentPlayer().next());
			Player[] p = Player.values();
			assertEquals(p[0]+"", "BLACK");
			assertEquals(p[1]+"", "WHITE");
			assertEquals(Player.WHITE, Player.valueOf("WHITE"));
			assertEquals(Player.BLACK, Player.valueOf("BLACK"));
		}
		
		@Test
		public void testBadEnPassant(){
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new Pawn(Player.WHITE), 4, 4);
			mainboard.set(new Pawn(Player.WHITE), 4, 2);
			mainboard.set(new Pawn(Player.WHITE), 6, 3);
			mainmodel.move(new Move(6,3,4,3), mainboard);
			assertFalse(mainmodel.getEnPassant());
			
			mainmodel = new ChessModel();
			mainboard = new ChessBoard();
			mainboard.clearBoard();
			mainboard.set(new Knight(Player.WHITE), 4, 4);
			mainboard.set(new Knight(Player.WHITE), 4, 2);
			mainboard.set(new Pawn(Player.WHITE), 6, 3);
			mainmodel.move(new Move(6,3,4,3), mainboard);
			assertFalse(mainmodel.getEnPassant());
		}
		
		@Test
		public void simpleStalemate1() {
			ChessModel model = new ChessModel();
			ChessBoard board = new ChessBoard();
			board.clearBoard();
			
			// Set up a very simple stalemate. 
			// It's white's turn and white has no legal move
			board.set(new Queen(Player.BLACK), 2,6);
			board.set(new King(Player.BLACK), 1,5);
			board.set(new King(Player.WHITE), 0,7);
			
			// We should be in stalemate
			assertTrue(model.inStaleMate(board));
		}
		
		
		@Test
		public void simpleStalemate2() {
			model = new ChessModel();
			board = new ChessBoard();
			board.clearBoard();
			
			board.set(new Pawn(Player.BLACK), 6,5);
			board.set(new King(Player.BLACK), 5,5);
			board.set(new King(Player.WHITE), 7,5);	
			
			// We should be in stalemate
			assertTrue(model.inStaleMate(board));	
		}
		
		@Test
		public void simpleStalemate3() {
			model = new ChessModel();
			board = new ChessBoard();
			board.clearBoard();
			
			board.set(new King(Player.BLACK), 0,0);
			board.set(new King(Player.WHITE), 7,7);	
			
			// We should be in stalemate
			assertTrue(model.inStaleMate(board));	
		}
		
		@Test
		public void threeStales(){
			model = new ChessModel();
			board = new ChessBoard();
			board.clearBoard();
			board.set(new King(Player.BLACK),0,7);
			board.set(new Pawn(Player.BLACK),1,6);
			board.set(new Pawn(Player.BLACK),1,7);
			board.set(new King(Player.WHITE),1,4);
			board.set(new Knight(Player.WHITE),2,7);
			model.setTurns(1);
			assertFalse(model.inStaleMate(board));
			board.set(new Bishop(Player.WHITE),7,0);
			assertTrue(model.inStaleMate(board));
			
			model = new ChessModel();
			board = new ChessBoard();
			board.clearBoard();
			board.set(new King(Player.BLACK), 4, 1);
			board.set(new Pawn(Player.WHITE), 5, 1);
			board.set(new Bishop(Player.BLACK),5,3);
			board.set(new King(Player.WHITE),6,0);
			assertFalse(model.inStaleMate(board));
			assertFalse(model.inCheckMate(board));
			assertFalse(model.isComplete(board));
			board.set(new Rook(Player.BLACK),7,1);
			assertTrue(model.inStaleMate(board));
			assertFalse(model.inCheckMate(board));
			assertTrue(model.isComplete(board));
			
			model = new ChessModel();
			board = new ChessBoard();
			board.clearBoard();
			board.set(new Knight(Player.BLACK),6,5);
			board.set(new Knight(Player.BLACK),5,5);
			board.set(new Knight(Player.BLACK),4,5);
			board.set(new King(Player.WHITE),5,6);
			board.set(new King(Player.BLACK),6,3);
			assertFalse(model.inStaleMate(board));
			assertFalse(model.inCheckMate(board));
			assertFalse(model.isComplete(board));
			board.unset(6,3);
			board.set(new King(Player.BLACK),5,4);
			assertTrue(model.inStaleMate(board));
			assertFalse(model.inCheckMate(board));
			assertTrue(model.isComplete(board));	
		}
		
		@Test
		public void tenCheckMates(){
			model = new ChessModel();
			board = new ChessBoard();
			board.clearBoard();
			board.set(new Queen(Player.BLACK),0,0);
			board.set(new Rook(Player.BLACK),1,0);
			board.set(new King(Player.BLACK), 2, 0);
			board.set(new King(Player.WHITE),0,5);
			assertTrue(model.inCheckMate(board));
			assertTrue(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			
			board.clearBoard();
			board.set(new King(Player.BLACK),0,0);
			board.set(new Rook(Player.BLACK),7,2);
			board.set(new King(Player.WHITE),7,6);
			board.set(new Pawn(Player.WHITE), 6, 7);
			board.set(new Pawn(Player.WHITE), 6, 6);
			board.set(new Pawn(Player.WHITE), 6, 5);
			assertTrue(model.inCheckMate(board));
			assertTrue(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			
			board.clearBoard();
			board.set(new King(Player.BLACK),0,4);
			board.set(new King(Player.WHITE),7,6);
			board.set(new Queen(Player.WHITE), 1, 4);
			board.set(new Knight(Player.WHITE), 3,5);
			model.setTurns(1); // to make it black's turn
			assertTrue(model.inCheckMate(board));
			assertTrue(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			model.setTurns(-1);
			
			board.clearBoard();
			board.set(new King(Player.BLACK),0,1);
			board.set(new Rook(Player.WHITE),7,5);
			board.set(new King(Player.WHITE),7,6);
			board.set(new Pawn(Player.WHITE), 6, 7);
			board.set(new Queen(Player.BLACK), 6, 6);
			board.set(new Pawn(Player.WHITE), 6, 5);
			board.set(new Bishop(Player.BLACK),0,0);
			assertTrue(model.inCheckMate(board));
			assertTrue(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			
			board.clearBoard();
			board.set(new King(Player.BLACK), 0, 1);
			board.set(new Bishop(Player.BLACK),0,0);
			board.set(new King(Player.WHITE),7,7);
			board.set(new Pawn(Player.WHITE), 6, 7);
			assertFalse(model.inCheckMate(board));
			assertFalse(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			board.set(new Bishop(Player.BLACK),1,0);
			assertTrue(model.inCheckMate(board));
			assertTrue(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			
			board.clearBoard();
			board.set(new King(Player.BLACK),0,1);
			board.set(new Rook(Player.WHITE),7,5);
			board.set(new King(Player.WHITE),7,6);
			board.set(new Pawn(Player.WHITE), 6, 7);
			board.set(new Pawn(Player.WHITE), 5, 6);
			board.set(new Pawn(Player.WHITE), 6, 5);
			board.set(new Bishop(Player.BLACK),5,5);
			assertFalse(model.inCheckMate(board));
			assertFalse(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			board.set(new Knight(Player.BLACK),5,7);
			assertTrue(model.inCheckMate(board));
			assertTrue(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			
			board.clearBoard();
			board.set(new King(Player.BLACK),0,3);
			board.set(new Pawn(Player.WHITE), 1, 2);
			board.set(new Pawn(Player.WHITE), 1, 3);
			board.set(new King(Player.WHITE),2,3);
			model.setTurns(1);
			assertTrue(model.inCheckMate(board));
			assertTrue(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			model.setTurns(-1);
			
			board.clearBoard();
			board.set(new King(Player.BLACK),0,3);
			board.set(new Pawn(Player.WHITE), 6,7);
			board.set(new Pawn(Player.WHITE), 6,6);
			board.set(new King(Player.WHITE),7,7);
			board.set(new Rook(Player.WHITE),7,6);
			board.set(new Knight(Player.BLACK),6,5);
			assertTrue(model.inCheckMate(board));
			assertTrue(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			
			board.clearBoard();
			model.setTurns(1);
			board.set(new King(Player.BLACK),1,7);
			board.set(new King(Player.WHITE),7,7);
			board.set(new Rook(Player.WHITE),6,7);
			board.set(new Knight(Player.WHITE),1,4);
			board.set(new Pawn(Player.BLACK), 1,5);
			board.set(new Pawn(Player.BLACK), 1,6);
			board.set(new Rook(Player.BLACK),0,5);
			assertTrue(model.inCheckMate(board));
			assertTrue(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			model.setTurns(-1);
			
			board.clearBoard();
			model.setTurns(1);
			board.set(new King(Player.BLACK),0,7);
			board.set(new King(Player.WHITE),7,7);
			board.set(new Rook(Player.WHITE),7,6);
			board.set(new Pawn(Player.BLACK), 1,5);
			board.set(new Pawn(Player.BLACK), 1,7);
			board.set(new Bishop(Player.WHITE),2,5);
			assertTrue(model.inCheckMate(board));
			assertTrue(model.isComplete(board));
			assertFalse(model.inStaleMate(board));
			model.setTurns(-1);
		}
}
