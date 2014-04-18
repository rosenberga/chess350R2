package model;

import java.util.Stack;

/*****************************************************************
 * The class that holds the stack of moves.
 *
 * @version 1.0
 * @author Adam Rosenberg
 *****************************************************************/
public final class MoveStack {
	
	/** stack of moves. */
	private Stack<Move> myStack;
	
	/*****************************************************************
	 * Constructor for the class.
	 * 
	 *****************************************************************/
	public MoveStack() {
		myStack = new Stack<Move>();
	}
	
	/*****************************************************************
	 * Pushes move onto the stack.
	 * 
	 * @param board the move to push
	 *****************************************************************/
	public void push(final Move board) {
		myStack.push(board);
	}
	
	/*****************************************************************
	 * Returns size of stack.
	 * 
	 * @return size of the stack
	 *****************************************************************/
	public int size() {
		return myStack.size();
	}
	
	/*****************************************************************
	 * Return true if stack is empty.
	 * 
	 * @return true if stack is empty
	 *****************************************************************/
	public boolean empty() {
		return myStack.empty();
	}
	
	/*****************************************************************
	 * Check the top of the stack.
	 * 
	 * @return the top move of the stack
	 *****************************************************************/
	public Move peek() {
		if (!myStack.empty()) {
			return myStack.peek();
		} else {
			return null;
		}
	}
	
	/*****************************************************************
	 * Pop the top of the stack.
	 * 
	 * @return move on top of the stack
	 *****************************************************************/
	public Move pop() {
		return myStack.pop();
	}

}
