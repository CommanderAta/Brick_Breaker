/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickproject;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/**
 *
 * @author huzai
 */
public class Brick2 extends Brick
{
    public Brick2(String path) //sets uncracked brick
    {
        super(path);
    }
    public void draw(Graphics2D g,int x,int y) //paints the brick
    {
        g.drawImage(this.image, x, y, null);
        g.setColor(new Color(255,0,0));
    }

    public void setBrickImage() //sets cracked image
    {
        this.imageIcon = new ImageIcon("src/Brick2/2.png");
        this.image = imageIcon.getImage();
    }
}
