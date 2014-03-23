package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RookTest {

	private ChessModel mainmodel;
	private ChessBoard mainboard;
	private Move move1;
	
	@Test
	public final void testRook1() {
		// move the rook up 3 spaces
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		//mainboard.clearBoard();
		mainboard.set(new Rook(Player.BLACK), 2, 0);
		//System.out.println(mainmodel.pieceAt(mainboard, 2, 0).type());
		move1 = new Move(2, 0, 5, 0);
		mainmodel.setTurns(1); // so its black's turn
		assertTrue(mainmodel.isValidMove(move1, mainboard));
		//mainmodel.move(move1, mainboard);
		//System.out.println(mainmodel.pieceAt(mainboard, 5, 0).type());
	}

	@Test
	public final void testRook2() {
		// move the rook right 2 spaces
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		mainboard.set(new Rook(Player.BLACK), 2, 0);
		move1 = new Move(2, 0, 2, 2);
		mainmodel.setTurns(1); // so its black's turn
		assertTrue(mainmodel.isValidMove(move1, mainboard));
	}

	@Test
	public final void testRook3() {
		// move the rook diagonal 3 spaces
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		mainboard.set(new Rook(Player.BLACK), 2, 0);
		move1 = new Move(2, 0, 5, 3);
		assertFalse(mainmodel.isValidMove(move1, mainboard));
	}

	@Test
	public final void testRook4() {
		// take a white piece
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		mainboard.set(new Rook(Player.BLACK), 2, 0);
		mainboard.set(new Rook(Player.WHITE), 3, 0);
		move1 = new Move(2, 0, 3, 0);
		mainmodel.setTurns(1); // so its black's turn
		assertTrue(mainmodel.isValidMove(move1, mainboard));
	}

	@Test
	public final void testRook5() {
		// attempt to take black piece
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		mainboard.set(new Rook(Player.BLACK), 2, 0);
		mainboard.set(new Rook(Player.BLACK), 3, 0);
		move1 = new Move(3, 0, 2, 0);
		assertFalse(mainmodel.isValidMove(move1, mainboard));
	}

	@Test
	public final void testRook6() {
		// attempt to move through a black piece
		mainmodel = new ChessModel();
		mainboard = new ChessBoard();
		mainboard.set(new Rook(Player.BLACK), 2, 0);
		mainboard.set(new Rook(Player.BLACK), 3, 0);
		move1 = new Move(2, 0, 4, 0);
		assertFalse(mainmodel.isValidMove(move1, mainboard));
		mainboard.clearBoard();
		mainboard.set(new Rook(Player.BLACK), 3, 0);
		mainboard.set(new Rook(Player.WHITE), 2, 0);
		mainboard.set(new Rook(Player.BLACK), 2, 1);
		assertFalse(mainmodel.isValidMove(new Move(2, 0, 4, 0), mainboard));
		assertFalse(mainmodel.isValidMove(new Move(2, 0, 2, 5), mainboard));
	}

}
