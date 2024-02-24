package brickproject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Fire
{

    private final int BOARD_HEIGHT = 592;
    private final int Fire_SPEED = -6;
    private int x;
    private int y;
    private int w;
    private int h;
    private boolean visible= true;
    private String FireImagePath;

    protected Image image = new Image()
    {
        @Override
        public int getWidth(ImageObserver observer) {
            return 0;
        }

        @Override
        public int getHeight(ImageObserver observer) {
            return 0;
        }

        @Override
        public ImageProducer getSource() {
            return null;
        }

        @Override
        public Graphics getGraphics() {
            return null;
        }

        @Override
        public Object getProperty(String name, ImageObserver observer) {
            return null;
        }

    };

    public Fire( int x,int y)
    {
        this.x = x;
        this.y = y;

        FireImagePath = "src/Fire/Fire.png";
        ImageIcon imageIcon = new ImageIcon(FireImagePath);
        if (image != null) image = imageIcon.getImage();
        if (image != null) w = image.getWidth(null);
        if (image != null) h = image.getHeight(null);
    }


    public int getX() { return x; }

    public int getY() { return y; }

    public int getWidth() { return w; }

    public int getHeight() { return h;}

    public Image getImage() { return image; }

    public boolean isVisible(){ return visible; }

    public void move()
    {
        y += Fire_SPEED;
        if (y<0)
        {
            visible = false;
        }
    }

}

