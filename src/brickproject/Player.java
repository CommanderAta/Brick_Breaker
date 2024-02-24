package brickproject;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player
{
    private static Player player = new Player();
    private int x,y,width,height,dx=0,liveCount=5;
    private ArrayList<Fire> bullet= new ArrayList<Fire>();
    private String path;

    protected Image imgObj = new Image()
    { //get details of image
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
        public Object getProperty(String name, ImageObserver observer) { return null;}
    };

    private Player()
    {
        this.x = 300;
        this.y = 540;

        path = "src/Paddles/MediumPaddle.png";
        ImageIcon imageIcon = new ImageIcon(path);
        if (imgObj != null) imgObj = imageIcon.getImage();

        if (imgObj != null) width = imgObj.getWidth(null);
        if (imgObj != null) height = imgObj.getHeight(null);
    }

    //Moves paddle left and right
    public void move()
    {
        this.x += dx;
        if (x < getWidth()/2)
        {
            x = getWidth()/2;
        }
        if(x>692-getWidth()/2)
        {
            x=692-getWidth()/2;
        }
    }

    public void FireBullets()
    {
        bullet.add(new Fire(x,y - (height / 4)));
    }

    public static Player getInstance() { return player; }

    public void increasePaddleSize()
    {
            path = "src/Paddles/LongPaddle.png";
    }

    public void decreasePaddleSize()
    {
            path = "src/Paddles/ShortPaddle.png";
    }

    public void decreaseLives() {liveCount--; }

    public int getLives() { return liveCount; }

    public void get5Lives() { liveCount=5; }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getWidth() { return width; }

    public int getHeight() { return height;}

    public Image getImage() { return imgObj; }

    public ArrayList getBullets(){ return bullet; }

    public void moveRight() { dx=5; }

    public void moveLeft() { dx=-5;}

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            moveLeft();
        }
    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            dx = 0;
        }
    }
}
