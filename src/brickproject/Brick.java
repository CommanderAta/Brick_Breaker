package brickproject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Brick
{
    protected Image image;
    protected ImageIcon imageIcon;
    public Brick(String path) //sets uncracked image
    {
        this.imageIcon = new ImageIcon(path);
        image = imageIcon.getImage();
    }

    
    public void draw(Graphics2D g,int x,int y) //paints the brick
    {
        g.drawImage(this.image, x, y, null);
        g.setColor(new Color(255,0,0));
    }

    public void setBrickImage() //sets cracked image
    {
        this.imageIcon = new ImageIcon("src/Brick1/2.png");
        this.image = imageIcon.getImage();
    }
}




