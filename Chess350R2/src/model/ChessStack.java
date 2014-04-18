package model;

import java.util.Stack;

/*****************************************************************
 * The stack of the ChessBoard class.
 * 
 * @author Adam Rosenberg
 *****************************************************************/
public final class ChessStack {
	
	/** The chessboard stack. */
	private Stack<IChessBoard> myStack;
	
	/*****************************************************************
	 * Constructs a new stack of IChessBoard.
	 * 
	 *****************************************************************/
	public ChessStack() {
		myStack = new Stack<IChessBoard>();
	}
	
	/*****************************************************************
	 * Pushes a board onto the stack.
	 * 
	 * @param board the board to push
	 *****************************************************************/
	public void push(final IChessBoard board) {
		myStack.push(board);
	}
	
	/*****************************************************************
	 * Returns the size of the stack.
	 * 
	 * @return the size of the stack
	 *****************************************************************/
	public int size() {
		return myStack.size();
	}
	
	/*****************************************************************
	 * Determines if stack is emtpy.
	 * 
	 * @return true if stack is empty
	 *****************************************************************/
	public boolean empty() {
		return myStack.empty();
	}
	
	/*****************************************************************
	 * Checks the top of the stack.
	 * 
	 * @return the top board of the stack
	 *****************************************************************/
	public IChessBoard peek() {
		return myStack.peek();
	}
	
	/*****************************************************************
	 * Pops the top of the stack.
	 * 
	 * @return the top board of the stack
	 *****************************************************************/
	public IChessBoard pop() {
		return myStack.pop();
	}

}
