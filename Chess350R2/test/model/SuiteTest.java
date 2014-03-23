package model;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({BishopTest.class, ChessBoardTest.class, 
	ChessModelTest.class, GameTest.class, KingTest.class,
	KnightTest.class, PawnTest.class, QueenTest.class, RookTest.class,
	MoveTest.class })
public class SuiteTest {
  //nothing
}