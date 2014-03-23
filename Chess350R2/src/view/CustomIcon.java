package view;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/*****************************************************************
 * Creates a custom icon that is one icon superimposed over
 * another.
 * 
 * @author Adam Rosenberg
 * @version 1.0
 *****************************************************************/
public final class CustomIcon extends ImageIcon {
	
	/** CustomIcons serial id. */
	private static final long serialVersionUID = -6348943189499307508L;
	
	/** The icon to be on bottom. */
    private Icon bottom;

    /*****************************************************************
     * Constructs a new Customer Icon.
     * 
     * @param btm
     * 			the String location of the icon to be on bottom
     * @param icon
     * 			the icon to go on top
     *****************************************************************/
    public CustomIcon(final String btm, final ImageIcon icon) {
    	super(icon.getDescription(), icon.getDescription());
        this.bottom = new ImageIcon(btm);
    }
    
    /*****************************************************************
     * Returns the icon's height.
     * 
     * @return the icon's height
     *****************************************************************/
    public int getIconHeight() {
        return Math.max(super.getIconHeight(), bottom.getIconHeight());
    }


    /*****************************************************************
     * Returns the icon's width.
     * 
     * @return the icon's width
     *****************************************************************/
    public int getIconWidth() {
        return Math.max(super.getIconWidth(), bottom.getIconWidth());
    }

    /*****************************************************************
     * Paints the icon.
     * 
     * @param c
     * 			the component to be used as the observer if
     * 		    this icon has no image observer
     * @param g
     * 			the graphics context
     * @param x
     * 			the x-axis location to paint
     * @param y
     * 			the y-axis location to paint
     *****************************************************************/
    public void paintIcon(final Component c, final Graphics g, 
    		final int x, final int y) {
        bottom.paintIcon(c, g, x, y);
        super.paintIcon(c, g, x, y);
    }
}
