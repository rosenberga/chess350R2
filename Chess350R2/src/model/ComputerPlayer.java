package model;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer {

	public ComputerPlayer(){

	}

	public final Move getRandomMove(final IChessModel model, final IChessBoard board) {

		int fr = -1;
		int fc = -1; 
		int count = -1;
		int[] from = new int[16];
		for (int i = 0; i < 16; i++) {    // initialize coordinates to -1
				from[i] = -1;
		}
		
		ArrayList<IChessPiece> field = new ArrayList<IChessPiece>(); // pieces owned by computer
		for (int i = 0; i < board.numRows(); i++) {
			for (int j = 0; j < board.numColumns(); j++) {
				if (board.pieceAt(i, j) != null){
					if (board.pieceAt(i, j).player() == model.currentPlayer()) {
						field.add(board.pieceAt(i, j));  // adding pieces owned by computer
						from[count] = i*10 + j;  // getting coordinates and storing into single int
						count++;
					}
				}
			}
		}

		Random r = new Random(field.size());
		
		while(true){
			int index = r.nextInt();
			fc = from[index] % 10;
			fr = (from[index] - fc) / 10;
			ArrayList<Move> available = new ArrayList<Move>(); // all available moves by selected piece
			for(int i = 0; i < board.numRows(); i++){
				for (int j = 0; j < board.numColumns(); j++){
					if (model.isValidMove(new Move(fr,fc,i,j), board)) {
							available.add(new Move(fr,fc,i,j));  // add move to available if possible
					}
				}
			}
			if (available.size() != 0) {
				r = new Random(available.size());
				Move m = available.get(r.nextInt());
				return m;
			}
		}
	}

	/*********************************************************
	 * Make a move focused on attacking the opponent.
	 * 
	 * @param model the set of chess rules
	 * @param board the chess board to work with
	 * @return the move to be made is returned
	 ********************************************************/
	public final Move getAttackMove(final IChessModel model, 
			final IChessBoard board) {
		//if found, return a move that will attack opponent
		//else return getRandomMove(model, board)
		int fr = -1;
		int fc = -1; 
		int count = -1;
		int[] from = new int[16];
		for (int i = 0; i < 16; i++) {    // initialize coordinates to -1
				from[i] = -1;
		}
		
		ArrayList<IChessPiece> field = new ArrayList<IChessPiece>(); // pieces owned by computer
		for (int i = 0; i < board.numRows(); i++) {
			for (int j = 0; j < board.numColumns(); j++) {
				if (board.pieceAt(i, j) != null){
					if (board.pieceAt(i, j).player() == model.currentPlayer()) {
						field.add(board.pieceAt(i, j));  // adding pieces owned by computer
						from[count] = i*10 + j;  // getting coordinates and storing into single int
						count++;
					}
				}
			}
		}

		
		while (field.size() != 0) {
			Random r = new Random(field.size());
			int index = r.nextInt();
			fc = from[index] % 10;
			fr = (from[index] - fc) / 10;
			ArrayList<Move> available = new ArrayList<Move>(); // list of available moves for selected piece
			for(int i = 0; i < board.numRows(); i++){
				for (int j = 0; j < board.numColumns(); j++){
					if (model.isValidMove(new Move(fr, fc, i, j), board)) {
						if (board.pieceAt(i, j).player() != model.currentPlayer()) { // if to row/col has enemy piece, add that spot
						    available.add(new Move(fr, fc, i, j));  // add move to available if possible
						}
					}
				}
			}
			if (available.size() != 0) {
				r = new Random(available.size());
				Move m = available.get(r.nextInt());
				return m;
			} else {
				field.remove(index);
				for (int i = index; i < (available.size() - index); i++) {
					// move coordinates back an index to allow for deleted piece in available
					from[i] = from[i+1];
				}
			}
		}
		return getRandomMove(model, board);
	}
}
