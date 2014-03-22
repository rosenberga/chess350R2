package model;

/*****************************************************************
 * Represents a chess game by having a chess model and a chess
 * board.
 * 
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public final class ChessGame {

	/** The ChessModel. */
	private IChessModel model;
	
	/** The ChessBoard. */
	private IChessBoard board;
	
	/** A Chess Move. */
	private Move move;
	
	
	/*****************************************************************
	 * Constructs a new chess game.
	 *****************************************************************/
	public ChessGame() {
		model = new ChessModel();
		board = new ChessBoard();
	}
	
	
	/*****************************************************************
	 * Gets model.
	 * 
	 * @return model the IChessModel in ChessGame
	 *****************************************************************/
	public IChessModel getModel() {
		return model;
	}
	
	
	/*****************************************************************
	 * Gets board.
	 * 
	 * @return board the IChessBoard in ChessGame
	 *****************************************************************/
	public IChessBoard getBoard() {
		return board;
	}
	
	
	/*****************************************************************
	 * Sets model.
	 * 
	 * @param newModel a new IChessModel to set this.model to
	 *****************************************************************/
	public void setModel(final IChessModel newModel) {
		model = newModel;
	}
	
	
	/*****************************************************************
	 * Gets model.
	 * 
	 * @param newBoard a new IChessBoard to set this.board to
	 *****************************************************************/
	public void setBoard(final IChessBoard newBoard) {
		board = newBoard;
	}


	public Move getMove() {
		return move;
	}


	public void setMove(Move move) {
		this.move = move;
	}
	
	public void setMove(int[] coords){
		Move m = new Move(coords);
		setMove(m);
	}
}
