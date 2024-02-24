package brickproject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Map
{
    public Brick[][] bricktype;
    public int[][] map;
    public int brickwidth,brickheight;
    public BrickFactory brickfactory = new BrickFactory();
    public Random rand=new Random();

    public Map(int row, int col)
    {
        map = new int[row][col];
        bricktype = new Brick[row][col];
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[0].length; j++)
            {
                map[i][j] = rand.nextInt(4)+1;   // random number generated to select type of brick
                bricktype[i][j]=brickfactory.getBrick(map[i][j], row, col); //brick type returned from brickfactory
            }
        }
        brickwidth = 540 / col;
        brickheight = 150 / row;
    }

    public void paint(Graphics2D g) //paints bricks from bricktype array
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[0].length; j++)
            {
                if (map[i][j] > 0)
                {
                    bricktype[i][j].draw(g, j * brickwidth+80, i * brickheight+50);
                }
            }
        } 
    }
    public int setBrickValue(int row, int col,int value) // handles number of hits and total bricks count
    {
        if (!(bricktype[row][col] instanceof Brick4))
        {
            map[row][col]--;
            bricktype[row][col].setBrickImage();
        }
        if(map[row][col]==0)
        {
            return (value-1);
        }
        return value;
    }
}
