
package brickproject;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Brick4 extends Brick
{
    public Brick4(String path)//sets uncracked brick
    {
        super(path);
    }
    public void draw(Graphics2D g,int x,int y) //paints brick
    {
        g.drawImage(this.image, x, y, null);
        g.setColor(new Color(255,0,0));
    }

    public void setBrickImage()
    {
        this.imageIcon = new ImageIcon("src/Brick4/1.png");
        this.image = imageIcon.getImage();
    }
}
