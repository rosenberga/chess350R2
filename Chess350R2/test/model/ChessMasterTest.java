package model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.*;

public class ChessMasterTest {

	ChessModel mainmodel, model;
	ChessBoard mainboard, board;
	Move move1;
	
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
	
	@Test
	public void test() {
		ChessBoard board = new ChessBoard();
		
		// make sure all the pieces are there
		// while also testing the piece at function
		for(int i = 0; i < 8; i++){
			IChessPiece piece = board.pieceAt(1,i);
			assertNotNull(piece);
			assertEquals(piece.type(),"Pawn");
			assertEquals(piece.player(),Player.BLACK);
			
			piece  = board.pieceAt(6,i);
			assertNotNull(piece);
			assertEquals(piece.type(),"Pawn");
			assertEquals(piece.player(),Player.WHITE);
			if(i == 0){
				for(int j = 0; j < 8; j++){
					piece = board.pieceAt(i,j);
					assertNotNull(piece);
					assertEquals(piece.player(),Player.BLACK);
					assertTrue(pieceCheck(piece,i,j));
				}
			}
			if(i == 7){
				for(int j = 0; j < 8; j++){
					piece = board.pieceAt(i,j);
					assertNotNull(piece);
					assertEquals(piece.player(),Player.WHITE);
					assertTrue(pieceCheck(piece,i,j));
				}
			}
		}
		
		// tests board for nulls
		for(int i = 0; i < 8; i++){
			for(int j = 2; j < 6; j++){
				assertNull(board.pieceAt(j, i));
			}
		}
		
		// test piece at with bad data
		assertNull(board.pieceAt(8, 4));
		assertNull(board.pieceAt(-1, 4));
		assertNull(board.pieceAt(4, 8));
		assertNull(board.pieceAt(4, -1));
		
		// make sure the rows and columns are right
		assertEquals(8,board.numRows());
		assertEquals(8,board.numColumns());
		
		// test clear board
		board.clearBoard();
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				assertNull(board.pieceAt(j, i));
			}
		}
		
		// tests copy board, set board, and getBoard
		ChessBoard b2 = new ChessBoard();
		board.setBoard(b2.copyBoard());
		IChessPiece[][] b3 = board.getBoard();
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				IChessPiece p1 = board.pieceAt(i, j);
				IChessPiece p2 = b2.pieceAt(i,j);
				IChessPiece p3 = b3[i][j];
				if (p1 == null) {
						assertEquals(p1,p2);
						assertEquals(p2,p3);
				}
				else{
					assertEquals(p1.type(),p2.type());
					assertEquals(p2.type(),p3.type());
					assertEquals(p1.player(),p2.player());
					assertEquals(p2.player(),p3.player());
				}
			}
		}
		
		board = new ChessBoard();
		
		// test bad sets and unsets to show it doesnt break
		board.set(new Pawn(Player.WHITE), -1, 0);
		board.set(new Pawn(Player.WHITE), 0, -1);
		board.set(new Pawn(Player.WHITE), 0, 8);
		board.set(new Pawn(Player.WHITE), 8, 0);
		
		board.unset(-1, 0);
		board.unset(0, -1);
		board.unset(0, 8);
		board.unset(8, 0);
		
		// test set
		assertNull(board.pieceAt(4, 4));
		board.set(new Pawn(Player.WHITE), 4, 4);
		assertEquals(board.pieceAt(4, 4).type(),"Pawn");
	    assertEquals(board.pieceAt(4, 4).player(),Player.WHITE);
		
		assertNotEquals(board.pieceAt(0,0).type(),"Pawn");
		assertNotEquals(board.pieceAt(7,7).type(),"Pawn");
		board.set(new Pawn(Player.WHITE), 0,0);
		assertEquals(board.pieceAt(0,0).type(),"Pawn");
		assertEquals(board.pieceAt(0,0).player(),Player.WHITE);
		board.set(new Pawn(Player.WHITE), 7,7);
		assertEquals(board.pieceAt(7,7).type(),"Pawn");
		assertEquals(board.pieceAt(7,7).player(),Player.WHITE);
		
		// test unset
		assertNotNull(board.pieceAt(7, 6));
		board.unset(7, 6);
		assertNull(board.pieceAt(7, 6));
		
		assertNotNull(board.pieceAt(0, 5));
		board.unset(0,5);
		assertNull(board.pieceAt(0,5));
		
		// test move
		board = new ChessBoard();
		board.move(new Move(1,0,2,0));
		assertNotNull(board.pieceAt(2, 0));
		assertNull(board.pieceAt(1, 0));
		
		board.move(new Move(7,0,6,0));
		assertNotNull(board.pieceAt(6, 0));
		assertNull(board.pieceAt(7, 0));
		
		board.move(new Move(6,0,5,0));
		assertNotNull(board.pieceAt(5, 0));
		assertNull(board.pieceAt(6, 0));
	}
	private boolean pieceCheck(IChessPiece piece, int row, int column){
		if (column == 0 || column == 7) {
			return piece.type().equals("Rook");
		}  else if (column == 1 || column == 6) {
			return piece.type().equals("Knight");
		} else if (column == 2 || column == 5) {
			return piece.type().equals("Bishop");
		} else if (column == 3) {
			return piece.type().equals("Queen");
		}
		return piece.type().equals("King");
	}
	
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
			
			//******************************************************Test King
			@Test
			public void testKing1() {
				// move the king diagonal 1 spaces
				mainmodel = new ChessModel();
				mainboard = new ChessBoard();
				mainboard.set(new King(Player.BLACK), 2,0);
				move1 = new Move(2,0,3,1);
				mainmodel.setTurns(1); // so its black's turn
				assertTrue (mainmodel.isValidMove(move1, mainboard));
			}

			@Test
			public void testKing2() {
				// move the king right 1 spaces
				mainmodel = new ChessModel();
				mainboard = new ChessBoard();
				mainboard.set(new King(Player.BLACK), 2,0);
				move1 = new Move(2,0,2,1);
				mainmodel.setTurns(1); // so its black's turn
				assertTrue (mainmodel.isValidMove(move1, mainboard));
			}
			@Test
			public void testKing3() {
				// move the king diagonal 2 spaces
				mainmodel = new ChessModel();
				mainboard = new ChessBoard();
				mainboard.set(new King(Player.BLACK), 2,0);
				move1 = new Move(2,0,4,2);
				assertFalse (mainmodel.isValidMove(move1, mainboard));
			}

			@Test
			public void testKing4() {
				// move the king right 2 spaces
				mainmodel = new ChessModel();
				mainboard = new ChessBoard();
				mainboard.set(new King(Player.BLACK), 2,0);
				move1 = new Move(2,0,2,2);
				assertFalse (mainmodel.isValidMove(move1, mainboard));
			}

			@Test
			public void testKing5() {
				// take a white piece
				mainmodel = new ChessModel();
				mainboard = new ChessBoard();
				mainboard.set(new King(Player.BLACK), 2,0);
				mainboard.set(new Pawn(Player.WHITE), 3,0);
				move1 = new Move(2,0,3,0);
				mainmodel.setTurns(1); // so its black's turn
				assertTrue (mainmodel.isValidMove(move1, mainboard));
			}

			@Test
			public void testKing6() {
				// attempt to take black piece
				mainmodel = new ChessModel();
				mainboard = new ChessBoard();
				mainboard.set(new King(Player.BLACK), 2,0);
				mainboard.set(new Pawn(Player.BLACK), 3,0);
				move1 = new Move(2,0,3,0);
				assertFalse(mainmodel.isValidMove(move1, mainboard));
			}
			
			//******************************************************Test Castling
				@Test
				public void testCastling1() {
					// make sure it can be done
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.clearBoard();
					mainboard.set(new King(Player.BLACK), 0,4);
					mainboard.set(new Rook(Player.BLACK), 0,7);
					//castle now
					move1 = new Move(0,4,0,6);
					mainmodel.move(move1, mainboard);
					assertEquals(mainboard.pieceAt(0, 6).type(),"King");
					assertEquals(mainboard.pieceAt(0, 5).type(),"Rook");
				}

				@Test
				public void testCastling2() {
					// make sure can't be done if rook moved
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.clearBoard();
					mainboard.set(new King(Player.BLACK), 0, 4);
					mainboard.set(new Rook(Player.BLACK), 0, 7);
					move1 = new Move(0,7,1,7);
					mainmodel.move(move1, mainboard);
					move1 = new Move(1,7,0,7);
					mainmodel.move(move1, mainboard);
					//castle now
					move1 = new Move(0,4,0,6);
					assertFalse (mainmodel.isValidMove(move1, mainboard));
				}
				
				@Test
				public void testCastling3() {
					// make sure can't be done if king moved
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.clearBoard();
					mainboard.set(new King(Player.BLACK), 0, 4);
					mainboard.set(new Rook(Player.BLACK), 0, 7);
					move1 = new Move(0,4,1,4);
					mainmodel.move(move1, mainboard);
					move1 = new Move(1,4,0,4);
					mainmodel.move(move1, mainboard);
					//castle now
					move1 = new Move(0,4,0,6);
					assertFalse (mainmodel.isValidMove(move1, mainboard));
				}
				
				@Test
				public void testCastling4() {
					// make sure can't be done if piece is in the way
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.clearBoard();
					mainboard.set(new King(Player.BLACK), 0, 4);
					mainboard.set(new Rook(Player.BLACK), 0, 7);
					mainboard.set(new Bishop(Player.BLACK), 0, 5);
					move1 = new Move(0,4,0,6);
					assertFalse (mainmodel.isValidMove(move1, mainboard));
				}
				
				@Test
				public void testCastling5() {
					// make sure can't be done if piece is in the way
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.clearBoard();
					mainboard.set(new King(Player.BLACK), 0, 4);
					mainboard.set(new Rook(Player.BLACK), 0, 7);
					mainboard.set(new Knight(Player.BLACK), 0, 6);
					move1 = new Move(0,4,0,6);
					assertFalse (mainmodel.isValidMove(move1, mainboard));
				}
				
				@Test
				public void testCastling6(){
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.clearBoard();
					mainboard.set(new King(Player.WHITE), 7,4);
					mainboard.set(new Rook(Player.WHITE), 7,7);
					mainboard.set(new Rook(Player.WHITE), 7,0);
					assertTrue(mainmodel.isValidMove(new Move(7,4,7,6), mainboard));
					mainmodel.move(new Move(7,4,7,6), mainboard);
					assertNotNull(mainmodel.pieceAt(mainboard, 7, 6));
					assertNotNull(mainmodel.pieceAt(mainboard, 7, 5));
				}
				
				@Test
				public void testCastling7(){
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.clearBoard();
					mainboard.set(new King(Player.WHITE), 7,4);
					mainboard.set(new Rook(Player.WHITE), 7,7);
					mainboard.set(new Rook(Player.WHITE), 7,0);
					mainboard.set(new Queen(Player.WHITE), 7,5);
					assertFalse(mainmodel.isValidMove(new Move(7,4,7,6), mainboard));
				}
				
				@Test
				public void testCastling8(){
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.clearBoard();
					mainboard.set(new King(Player.WHITE), 7,4);
					mainboard.set(new Rook(Player.WHITE), 7,7);
					mainboard.set(new Rook(Player.WHITE), 7,0);
					mainboard.set(new Queen(Player.BLACK), 0,5);
					mainboard.set(new King(Player.BLACK),0,6);
					assertFalse(mainmodel.isValidMove(new Move(7,4,7,6), mainboard));		
				}

				@Test
				public void testCastling9(){
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.clearBoard();
					mainboard.set(new King(Player.WHITE), 7,4);
					mainboard.set(new Rook(Player.WHITE), 7,7);
					mainboard.set(new Rook(Player.WHITE), 7,0);
					assertTrue(mainmodel.isValidMove(new Move(7,4,7,2), mainboard));
					mainmodel.move(new Move(7,4,7,2), mainboard);
					assertNotNull(mainmodel.pieceAt(mainboard, 7, 2));
					assertNotNull(mainmodel.pieceAt(mainboard, 7, 3));
				}
				
				@Test
				public void testCastling10(){
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.clearBoard();
					mainboard.set(new King(Player.BLACK), 0,4);
					mainboard.set(new Rook(Player.BLACK), 0,7);
					mainboard.set(new Rook(Player.BLACK), 0,0);
					mainmodel.setTurns(1); // so its black's turn
					assertTrue(mainmodel.isValidMove(new Move(0,4,0,6), mainboard));
					mainmodel.move(new Move(0,4,0,6), mainboard);
					assertNotNull(mainmodel.pieceAt(mainboard, 0, 6));
					assertNotNull(mainmodel.pieceAt(mainboard, 0, 5));
				}
				
				@Test
				public void testCastling11(){
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.clearBoard();
					mainboard.set(new King(Player.BLACK), 0,4);
					mainboard.set(new Rook(Player.BLACK), 0,7);
					mainboard.set(new Rook(Player.BLACK), 0,0);
					mainmodel.setTurns(1); // so its black's turn
					assertTrue(mainmodel.isValidMove(new Move(0,4,0,2), mainboard));
					mainmodel.move(new Move(0,4,0,2), mainboard);
					assertNotNull(mainmodel.pieceAt(mainboard, 0, 2));
					assertNotNull(mainmodel.pieceAt(mainboard, 0, 3));
				}
				
				//******************************************************Test Knight
				@Test
				public void testKnight1() {
					// move the knight L-move up spaces
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.set(new Knight(Player.BLACK), 2,0);
					move1 = new Move(2,0,4,1);
					mainmodel.setTurns(1); // so its black's turn
					assertTrue (mainmodel.isValidMove(move1, mainboard));
					mainmodel.move(move1, mainboard);
					assertTrue(mainmodel.pieceAt(mainboard, 4, 1).getMoved());
					mainmodel.setTurns(1); // so its black's turn
					assertTrue(mainmodel.isValidMove(new Move(4,1,5,3), mainboard));
				}

				@Test
				public void testKnight2() {
					// move the knight right 3 spaces
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.set(new Knight(Player.BLACK), 2,0);
					move1 = new Move(2,0,2,3);
					assertFalse (mainmodel.isValidMove(move1, mainboard));
				}

				@Test
				public void testKnight3() {
					// move the knight diagonal 1 spaces
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.set(new Knight(Player.BLACK), 2,0);
					move1 = new Move(2,0,3,1);
					assertFalse(mainmodel.isValidMove(move1, mainboard));
				}

				@Test
				public void testKnight4() {
					// take a white piece
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.set(new Knight(Player.BLACK), 2,0);
					mainboard.set(new Knight(Player.WHITE), 4,1);
					move1 = new Move(2,0,4,1);
					mainmodel.setTurns(1); // so its blacks turn
					assertTrue (mainmodel.isValidMove(move1, mainboard));
				}

				@Test
				public void testKnight5() {
					// attempt to take black piece
					mainmodel = new ChessModel();
					mainboard = new ChessBoard();
					mainboard.set(new Knight(Player.BLACK), 2,0);
					mainboard.set(new Knight(Player.BLACK), 4,1);
					move1 = new Move(2,0,4,1);
					assertFalse (mainmodel.isValidMove(move1, mainboard));
				}
				
				@Test
				public void test_() {
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
					
					@Test
					public void queensWreakingHavocTest() {
						ChessModel model = new ChessModel();
						ChessBoard board = new ChessBoard();
						Move move;
						
						// Move Pawns to free the Queens
						model.move(new Move(6,4,5,4), board);
						model.move(new Move(1,4,2,4), board);
						
						// Queens wreaking havoc
						assertTrue(model.isValidMove(new Move(7,3,3,7), board));
						model.move(new Move(7,3,3,7), board);
						assertTrue(model.isValidMove(new Move(0,3,4,7), board));
						model.move(new Move(0,3,4,7), board);
						assertTrue(model.isValidMove(new Move(3,7,1,7), board));
						model.move(new Move(3,7,1,7), board);
						assertTrue(model.isValidMove(new Move(4,7,6,7), board));
						model.move(new Move(4,7,6,7), board);
						assertTrue(model.isValidMove(new Move(1,7,0,6), board));
						model.move(new Move(1,7,0,6), board);
						assertTrue(model.isValidMove(new Move(6,7,7,6), board));
						model.move(new Move(6,7,7,6), board);
						assertTrue(model.isValidMove(new Move(0,6,1,6), board));
						model.move(new Move(0,6,1,6), board);
						assertTrue(model.isValidMove(new Move(7,6,6,6), board));
						model.move(new Move(7,6,6,6), board);
						assertTrue(model.isValidMove(new Move(1,6,4,3), board));
						model.move(new Move(1,6,4,3), board);
						assertTrue(model.isValidMove(new Move(6,6,3,3), board));
						model.move(new Move(6,6,3,3), board);
						assertTrue(model.isValidMove(new Move(4,3,1,0), board));
						model.move(new Move(4,3,1,0), board);
						assertTrue(model.isValidMove(new Move(3,3,6,0), board));
						model.move(new Move(3,3,6,0), board);
						assertTrue(model.isValidMove(new Move(1,0,1,1), board));
						model.move(new Move(1,0,1,1), board);
						assertTrue(model.isValidMove(new Move(6,0,6,1), board));
						model.move(new Move(6,0,6,1), board);
						
						//Right rooks move out of the corners so 
						//we can attempt the queen to capture a left rook
						//with the complete diagonal across the board
						
						model.move(new Move(7,7,4,7), board);		
						model.move(new Move(0,7,3,7), board);
						
						assertTrue(model.isValidMove(new Move(1,1,7,7), board));
						model.move(new Move(1,1,7,7), board);
						assertTrue(model.isValidMove(new Move(6,1,0,7), board));
						model.move(new Move(6,1,0,7), board);
						
						//Across the board Queens capturing rooks
						assertTrue(model.isValidMove(new Move(7,7,0,0), board));
						model.move(new Move(7,7,0,0), board);
						assertTrue(model.isValidMove(new Move(0,7,7,0), board));
						model.move(new Move(0,7,7,0), board);
						
						
						// Queen takes Queen
						assertTrue(model.isValidMove(new Move(0,0,7,0), board));
						model.move(new Move(0,0,7,0), board);
						
						// Clears the board, set up Queens to move around the board
						board.clearBoard();
						
						board.set(new Queen(Player.BLACK), 0,0);
						board.set(new King(Player.BLACK), 1,2);
						board.set(new Queen(Player.WHITE), 7,7);
						board.set(new King(Player.WHITE), 6,2);
						
						//Queens spinning clockwise
						assertTrue(model.isValidMove(new Move(0,0,0,7), board));
						model.move(new Move(0,0,0,7), board);
						assertTrue(model.isValidMove(new Move(7,7,7,0), board));
						model.move(new Move(7,7,7,0), board);
						assertTrue(model.isValidMove(new Move(0,7,7,7), board));
						model.move(new Move(0,7,7,7), board);
						assertTrue(model.isValidMove(new Move(7,0,0,0), board));
						model.move(new Move(7,0,0,0), board);
						assertTrue(model.isValidMove(new Move(7,7,7,0), board));
						model.move(new Move(7,7,7,0), board);
						assertTrue(model.isValidMove(new Move(0,0,0,7), board));
						model.move(new Move(0,0,0,7), board);
						assertTrue(model.isValidMove(new Move(7,0,7,7), board));
						model.move(new Move(7,0,7,7), board);
						assertTrue(model.isValidMove(new Move(0,7,0,0), board));
						model.move(new Move(0,7,0,0), board);
						
						//Queens spinning counter-clockwise
						assertTrue(model.isValidMove(new Move(7,7,0,7), board));
						model.move(new Move(7,7,0,7), board);
						assertTrue(model.isValidMove(new Move(0,0,7,0), board));
						model.move(new Move(0,0,7,0), board);
						assertTrue(model.isValidMove(new Move(0,7,0,0), board));
						model.move(new Move(0,7,0,0), board);
						assertTrue(model.isValidMove(new Move(7,0,7,7), board));
						model.move(new Move(7,0,7,7), board);
						assertTrue(model.isValidMove(new Move(0,0,7,0), board));
						model.move(new Move(0,0,7,0), board);
						assertTrue(model.isValidMove(new Move(7,7,0,7), board));
						model.move(new Move(7,7,0,7), board);
						assertTrue(model.isValidMove(new Move(7,0,7,7), board));
						model.move(new Move(7,0,7,7), board);
						assertTrue(model.isValidMove(new Move(0,7,0,0), board));
						model.move(new Move(0,7,0,0), board);
					}
					
					@Test
					public void queenFoolsMateStuff(){
						//Use Queen to force a checkmate
						ChessBoard cb = new ChessBoard();
						ChessModel game = new ChessModel();
						Move move;
						// Fool's Mate 		 
						move = new Move(6, 5, 5, 5);
						game.move(move, cb);
						
						
						move = new Move(1, 4, 3, 4);
						game.move(move, cb);
						
						
						move = new Move(6, 6, 4, 6);
						game.move(move, cb);
						
						//Move Black Queen to checkmate White
						assertTrue(game.isValidMove(new Move(0, 3, 4, 7), cb));
						move = new Move(0, 3, 4, 7);
						game.move(move, cb);
						

						// Trying to move a Queen illegally (piece jump + after checkmate)
						move = new Move(7, 3, 5, 3);
						assertFalse(game.isValidMove(move, cb));
						

						// Checking for checkmate
						assertTrue(game.inCheckMate(cb));
					}
					
					@Test
					public void testQueen1() {
						// move the queen diagonal 3 spaces
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Queen(Player.BLACK), 2,0);
						move1 = new Move(2,0,5,3);
						mainmodel.setTurns(1); // so its black's turn
						assertTrue (mainmodel.isValidMove(move1, mainboard));
					}

					@Test
					public void testQueen2() {
						// move the queen right 3 spaces
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Queen(Player.BLACK), 2,0);
						move1 = new Move(2,0,2,3);
						mainmodel.setTurns(1); // so its black's turn
						assertTrue (mainmodel.isValidMove(move1, mainboard));
					}

					@Test
					public void testQueen3() {
						// move the queen in up l-move
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Queen(Player.BLACK), 2,0);
						move1 = new Move(2,0,4,1);
						assertFalse (mainmodel.isValidMove(move1, mainboard));
					}

					@Test
					public void testQueen4() {
						// move the queen in up 2 spaces
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Queen(Player.BLACK), 2,0);
						move1 = new Move(2,0,4,0);
						mainmodel.setTurns(1); // so its black's turn
						assertTrue (mainmodel.isValidMove(move1, mainboard));
					}

					@Test
					public void testQueen5() {
						// take a white piece
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Queen(Player.BLACK), 2,0);
						mainboard.set(new Queen(Player.WHITE), 5,3);
						move1 = new Move(2,0,5,3);
						mainmodel.setTurns(1); // so its black's turn
						assertTrue (mainmodel.isValidMove(move1, mainboard));
					}

					@Test
					public void testQueen6() {
						// attempt to take black piece
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Queen(Player.BLACK), 2,0);
						mainboard.set(new Rook(Player.BLACK), 5,3);
						move1 = new Move(2,0,5,3);
						assertFalse(mainmodel.isValidMove(move1, mainboard));
					}

					@Test
					public void testQueen7() {
						// attempt to move through black piece
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Queen(Player.BLACK), 2,0);
						mainboard.set(new Rook(Player.BLACK), 4,2);
						move1 = new Move(2,0,5,3);
						assertFalse (mainmodel.isValidMove(move1, mainboard));
						mainboard.clearBoard();
						mainboard.set(new Rook(Player.BLACK), 3,0);
						mainboard.set(new Queen(Player.WHITE), 2,0);
						mainboard.set(new Rook(Player.BLACK), 2,1);
						assertFalse(mainmodel.isValidMove(new Move(2,0,4,0), mainboard));
						assertFalse(mainmodel.isValidMove(new Move(2,0,2,5), mainboard));
						assertFalse(mainmodel.pieceAt(mainboard,2,0).getMoved());
					}
					
					@Test
					public void testRook1() {
						// move the rook up 3 spaces
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						//mainboard.clearBoard();
						mainboard.set(new Rook(Player.BLACK), 2,0);
						//System.out.println(mainmodel.pieceAt(mainboard, 2,0).type());
						move1 = new Move(2,0,5,0);
						mainmodel.setTurns(1); // so its black's turn
						assertTrue (mainmodel.isValidMove(move1, mainboard) == true);
						//mainmodel.move(move1, mainboard);
						//System.out.println(mainmodel.pieceAt(mainboard, 5,0).type());
					}

					@Test
					public void testRook2() {
						// move the rook right 2 spaces
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Rook(Player.BLACK), 2,0);
						move1 = new Move(2,0,2,2);
						mainmodel.setTurns(1); // so its black's turn
						assertTrue (mainmodel.isValidMove(move1, mainboard) == true);
					}

					@Test
					public void testRook3() {
						// move the rook diagonal 3 spaces
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Rook(Player.BLACK), 2,0);
						move1 = new Move(2,0,5,3);
						assertFalse (mainmodel.isValidMove(move1, mainboard));
					}

					@Test
					public void testRook4() {
						// take a white piece
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Rook(Player.BLACK), 2,0);
						mainboard.set(new Rook(Player.WHITE), 3,0);
						move1 = new Move(2,0,3,0);
						mainmodel.setTurns(1); // so its black's turn
						assertTrue (mainmodel.isValidMove(move1, mainboard));
					}

					@Test
					public void testRook5() {
						// attempt to take black piece
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Rook(Player.BLACK), 2,0);
						mainboard.set(new Rook(Player.BLACK), 3,0);
						move1 = new Move(3,0,2,0);
						assertFalse (mainmodel.isValidMove(move1, mainboard));
					}

					@Test
					public void testRook6() {
						// attempt to move through a black piece
						mainmodel = new ChessModel();
						mainboard = new ChessBoard();
						mainboard.set(new Rook(Player.BLACK), 2,0);
						mainboard.set(new Rook(Player.BLACK), 3,0);
						move1 = new Move(2,0,4,0);
						assertFalse (mainmodel.isValidMove(move1, mainboard));
						mainboard.clearBoard();
						mainboard.set(new Rook(Player.BLACK), 3,0);
						mainboard.set(new Rook(Player.WHITE), 2,0);
						mainboard.set(new Rook(Player.BLACK), 2,1);
						assertFalse(mainmodel.isValidMove(new Move(2,0,4,0), mainboard));
						assertFalse(mainmodel.isValidMove(new Move(2,0,2,5), mainboard));
					}

}
