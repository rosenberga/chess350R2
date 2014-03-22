package view;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CustomIcon extends ImageIcon{
    private Icon bottom;

    public CustomIcon(String bottom, ImageIcon icon) {
    	super(icon.getDescription(),icon.getDescription());
        this.bottom = new ImageIcon(bottom);
    }
    
/*    public CustomIcon(ImageIcon before, ImageIcon legal){
    	super(legal.getDescription(),legal.getDescription());
    	this.bottom = before;
    }*/

    public int getIconHeight() {
        return Math.max(super.getIconHeight(), bottom.getIconHeight());
    }

    public int getIconWidth() {
        return Math.max(super.getIconWidth(), bottom.getIconWidth());
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        bottom.paintIcon(c, g, x, y);
        super.paintIcon(c, g, x, y);
    }
    
    @Override
    public String getDescription(){
    	return super.getDescription();
    }
}
