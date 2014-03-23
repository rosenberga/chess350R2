package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChessBoardTest {

	@Test
	public void test() {
		ChessBoard board = new ChessBoard();
		
		// make sure all the pieces are there
		// while also testing the piece at function
		for (int i = 0; i < 8; i++) {
			IChessPiece piece = board.pieceAt(1, i);
			assertNotNull(piece);
			assertEquals(piece.type(), "Pawn");
			assertEquals(piece.player(), Player.BLACK);
			
			piece = board.pieceAt(6, i);
			assertNotNull(piece);
			assertEquals(piece.type(), "Pawn");
			assertEquals(piece.player(), Player.WHITE);
			if (i == 0) {
				for (int j = 0; j < 8; j++) {
					piece = board.pieceAt(i, j);
					assertNotNull(piece);
					assertEquals(piece.player(), Player.BLACK);
					assertTrue(pieceCheck(piece, i, j));
				}
			}
			if (i == 7) {
				for (int j = 0; j < 8; j++) {
					piece = board.pieceAt(i, j);
					assertNotNull(piece);
					assertEquals(piece.player(), Player.WHITE);
					assertTrue(pieceCheck(piece, i, j));
				}
			}
		}
		
		// tests board for nulls
		for (int i = 0; i < 8; i++) {
			for (int j = 2; j < 6; j++) {
				assertNull(board.pieceAt(j, i));
			}
		}
		
		// test piece at with bad data
		assertNull(board.pieceAt(8, 4));
		assertNull(board.pieceAt(-1, 4));
		assertNull(board.pieceAt(4, 8));
		assertNull(board.pieceAt(4, -1));
		
		// make sure the rows and columns are right
		assertEquals(8, board.numRows());
		assertEquals(8, board.numColumns());
		
		// test clear board
		board.clearBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				assertNull(board.pieceAt(j, i));
			}
		}
		
		// tests copy board, set board, and getBoard
		ChessBoard b2 = new ChessBoard();
		board.setBoard(b2.copyBoard());
		IChessPiece[][] b3 = board.getBoard();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				IChessPiece p1 = board.pieceAt(i, j);
				IChessPiece p2 = b2.pieceAt(i, j);
				IChessPiece p3 = b3[i][j];
				if (p1 == null) {
						assertEquals(p1, p2);
						assertEquals(p2, p3);
				} else {
					assertEquals(p1.type(), p2.type());
					assertEquals(p2.type(), p3.type());
					assertEquals(p1.player(), p2.player());
					assertEquals(p2.player(), p3.player());
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
		assertEquals(board.pieceAt(4, 4).type(), "Pawn");
	    assertEquals(board.pieceAt(4, 4).player(), Player.WHITE);
		
		assertNotEquals(board.pieceAt(0, 0).type(), "Pawn");
		assertNotEquals(board.pieceAt(7, 7).type(), "Pawn");
		board.set(new Pawn(Player.WHITE), 0, 0);
		assertEquals(board.pieceAt(0, 0).type(), "Pawn");
		assertEquals(board.pieceAt(0, 0).player(), Player.WHITE);
		board.set(new Pawn(Player.WHITE), 7, 7);
		assertEquals(board.pieceAt(7, 7).type(), "Pawn");
		assertEquals(board.pieceAt(7, 7).player(), Player.WHITE);
		
		// test unset
		assertNotNull(board.pieceAt(7, 6));
		board.unset(7, 6);
		assertNull(board.pieceAt(7, 6));
		
		assertNotNull(board.pieceAt(0, 5));
		board.unset(0, 5);
		assertNull(board.pieceAt(0, 5));
		
		// test move
		board = new ChessBoard();
		board.move(new Move(1, 0, 2, 0));
		assertNotNull(board.pieceAt(2, 0));
		assertNull(board.pieceAt(1, 0));
		
		board.move(new Move(7, 0, 6, 0));
		assertNotNull(board.pieceAt(6, 0));
		assertNull(board.pieceAt(7, 0));
		
		board.move(new Move(6, 0, 5, 0));
		assertNotNull(board.pieceAt(5, 0));
		assertNull(board.pieceAt(6, 0));
	}
	private boolean pieceCheck(IChessPiece piece, int row, int column) {
		if (column == 0 || column == 7) {
			return piece.type().equals("Rook");
		} else if (column == 1 || column == 6) {
			return piece.type().equals("Knight");
		} else if (column == 2 || column == 5) {
			return piece.type().equals("Bishop");
		} else if (column == 3) {
			return piece.type().equals("Queen");
		}
		return piece.type().equals("King");
	}

}
