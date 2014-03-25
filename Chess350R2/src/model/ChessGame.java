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
	
	private ChessStack chessStack;
	
	private static final int UNDO_TURN = -1;
	
	
	/*****************************************************************
	 * Constructs a new chess game.
	 *****************************************************************/
	public ChessGame() {
		chessStack = new ChessStack();
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
	 * Sets Board.
	 * 
	 * @param newBoard a new IChessBoard to set this.board to
	 *****************************************************************/
	public void setBoard(final IChessBoard newBoard) {
		board = newBoard;
	}

	/*****************************************************************
	 * Gets move.
	 * 
	 * @return move the move in ChessGame
	 *****************************************************************/
	public Move getMove() {
		return move;
	}

	/*****************************************************************
	 * Sets Move.
	 * 
	 * @param move1 a new move to set in the game
	 *****************************************************************/
	public void setMove(final Move move1) {
		this.move = move1;
	}
	
	/*****************************************************************
	 * Sets Move given coordinates instead of a Move.
	 * 
	 * @param coords a new move to set in the game
	 *****************************************************************/
	public void setMove(final int[] coords) {
		Move m = new Move(coords);
		setMove(m);
	}
	
	public boolean canUndo() {
		return !chessStack.empty();
	}
	
	public void undo() {
		if (canUndo()) {
			setBoard(chessStack.pop());
			getModel().setTurns(UNDO_TURN);
		}
	}
	
	public ChessStack getChessStack(){
		return chessStack;
	}
}
