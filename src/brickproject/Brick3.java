package brickproject;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Brick3 extends Brick
{
    public Brick3(String path) //sets uncracked brick
    {
        super(path);
    }
    public void draw(Graphics2D g,int x,int y) //paints brick
    {
        g.drawImage(this.image, x, y, null);
        g.setColor(new Color(255,0,0));
    }

    public void setBrickImage() //sets uncracked brick
     {
        this.imageIcon = new ImageIcon("src/Brick3/2.png");
        this.image = imageIcon.getImage();
    }
}
