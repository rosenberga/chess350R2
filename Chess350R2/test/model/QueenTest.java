package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueenTest {

	ChessModel mainmodel = new ChessModel();
	ChessBoard mainboard = new ChessBoard();
	Move move1;
	
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

		
	

}
