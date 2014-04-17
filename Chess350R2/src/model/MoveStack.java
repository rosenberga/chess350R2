package model;

import java.io.Serializable;
import java.util.Stack;

public final class MoveStack implements Serializable {
	
	private Stack<Move> myStack;
	
	public MoveStack() {
		myStack = new Stack<Move>();
	}
	
	public void push(final Move board) {
		myStack.push(board);
	}
	
	public int size() {
		return myStack.size();
	}
	
	public boolean empty() {
		return myStack.empty();
	}
	
	public Move peek() {
		if(!myStack.empty())
			return myStack.peek();
		else
			return null;
	}
	
	public Move pop() {
		return myStack.pop();
	}

}
