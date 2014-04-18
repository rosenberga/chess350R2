package model;

/*****************************************************************
 * Represents a chess game by having a chess model and a chess board.
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

	/** Stack of ChessBoard Class. */
	private ChessStack chessStack;

	/** Represents turn counter for undo. */
	private static final int UNDO_TURN = -1;

	/** Stack of Move Class. */
	private MoveStack moveStack;
	
	/** Magic Number 16. */
	private static final int MAGIC16 = 16;
	
	/** Magic Number 1. */
	private static final int MAGIC1 = 1;

	/*****************************************************************
	 * Constructs a new chess game.
	 *****************************************************************/
	public ChessGame() {
		chessStack = new ChessStack();
		moveStack = new MoveStack();
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
	 * @param newModel
	 *            a new IChessModel to set this.model to
	 *****************************************************************/
	public void setModel(final IChessModel newModel) {
		model = newModel;
	}

	/*****************************************************************
	 * Sets Board.
	 * 
	 * @param newBoard
	 *            a new IChessBoard to set this.board to
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
	 * @param move1
	 *            a new move to set in the game
	 *****************************************************************/
	public void setMove(final Move move1) {
		this.move = move1;
	}

	/*****************************************************************
	 * Sets Move given coordinates instead of a Move.
	 * 
	 * @param coords
	 *            a new move to set in the game
	 *****************************************************************/
	public void setMove(final int[] coords) {
		Move m = new Move(coords);
		setMove(m);
	}

	/*****************************************************************
	 * Determines if Undo Feature is possible.
	 * 
	 * @return true if chessstack is not empty
	 *****************************************************************/
	public boolean canUndo() {
		return !chessStack.empty();
	}

	/*****************************************************************
	 * Undoes the previous move.
	 * 
	 *****************************************************************/
	public void undo() {
		if (canUndo()) {
			setBoard(chessStack.pop());
			getModel().setTurns(UNDO_TURN);
			moveStack.pop();

			//Graveyard remove
			if (model.countBlacks(board) > MAGIC16) {
				model.getBlackGrave().remove(model.
						getBlackGrave().size() - MAGIC1);
			}

			if (model.countWhites(board) > MAGIC16) {
				model.getWhiteGrave().remove(model.
						getWhiteGrave().size() - MAGIC1);
			}
		}
	}

	/*****************************************************************
	 * Return the stack of ChessBoards.
	 * 
	 * @return the chessStack
	 *****************************************************************/
	public ChessStack getChessStack() {
		return chessStack;
	}

	/*****************************************************************
	 * Push a move onto the stack of moves.
	 * 
	 * @return the top of the move stack
	 *****************************************************************/
	public Move getLastMove() {
		return moveStack.peek();
	}

	/*****************************************************************
	 * Push a move onto the stack of moves.
	 * 
	 * @param m the move to push
	 *****************************************************************/
	public void pushMove(final Move m) {
		moveStack.push(m);
	}

	/*****************************************************************
	 * Undoes the previous two moves.
	 * 
	 *****************************************************************/
	public void undo2() {
		if (canUndo2()) {
			chessStack.pop();
			setBoard(chessStack.pop());
			moveStack.pop();
			moveStack.pop();
		}
	}

	/*****************************************************************
	 * Determines if Undo Feature is possible.
	 * 
	 * @return true if chessStack has at least 2 moves
	 *****************************************************************/
	public boolean canUndo2() {
		return (chessStack.size() > 1);
	}
}
