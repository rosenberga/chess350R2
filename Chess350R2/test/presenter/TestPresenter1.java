package presenter;

import static org.junit.Assert.*;

import view.*;
import model.*;

import org.junit.Test;

public class TestPresenter1 {

	ChessPresenter p;
	ChessView v;
	ChessGame g;
	
	@Test
	public void test() {
		g = new ChessGame();
		v = new ChessView(g.getBoard().numColumns(),g.getBoard().numRows());
		p = new ChessPresenter(g,v);
		fail("Not yet implemented");
	}

}
