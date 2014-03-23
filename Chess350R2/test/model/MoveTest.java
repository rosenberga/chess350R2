package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MoveTest {

	@Test
	public final void moveT() {
		int[] t = {0, 1, 2, 3 };
		Move m = new Move(t);
		assertEquals(m.getFromRow(), 0);
		assertEquals(m.getFromColumn(), 1);
		assertEquals(m.getToRow(), 2);
		assertEquals(m.getToColumn(), 3);
		
		int[] z = {1, 2 };
		m = new Move(z);
		assertEquals(m.getFromRow(), -1);
		assertEquals(m.getFromColumn(), -1);
		assertEquals(m.getToRow(), -1);
		assertEquals(m.getToColumn(), -1);
	}
}
