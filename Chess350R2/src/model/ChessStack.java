package model;

import java.util.Stack;

public final class ChessStack {
	
	private Stack<IChessBoard> myStack;
	
	public ChessStack() {
		myStack = new Stack<IChessBoard>();
	}
	
	public void push(final IChessBoard board) {
		myStack.push(board);
	}
	
	public int size() {
		return myStack.size();
	}
	
	public boolean empty() {
		return myStack.empty();
	}
	
	public IChessBoard peek() {
		return myStack.peek();
	}
	
	public IChessBoard pop() {
		return myStack.pop();
	}

}
