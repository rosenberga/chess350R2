package model;

/*****************************************************************
 * A player of a chess game.
 *
 * @author Zachary Kurmas
 * @version 1.0
 *****************************************************************/
 
/*****************************************************************
 * Player has two values: BLACK and WHITE.
 *****************************************************************/
public enum Player {
	
	/** The Player is color Black. */
   BLACK,
   
   /** The Player is color White. */
   WHITE;

   
   /*****************************************************************
    * Return the Player whose turn is next.
    *
    * @return the Player whose turn is next
    *****************************************************************/
   public Player next() {
      if (this == BLACK) {
    	  return WHITE;
      } else {
    	  return BLACK;
      }
   }
}