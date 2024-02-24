package brickproject;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Random;
import javax.swing.ImageIcon;

public class Ball
{
    private int x,y,w,h,ballXdir=-1,ballYdir=-2;
    private boolean ballonPaddle=true;
    private String ballImagePath;

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

    public Ball()  // sets ball image, coordinates (default), gets width and height
    {
        this.x = 310;
        this.y = 495;

        ballImagePath = "src/Ball/Ball.png";
        ImageIcon imageIcon = new ImageIcon(ballImagePath);
        if (image != null) image = imageIcon.getImage();

        if (image != null) w = image.getWidth(null);
        if (image != null) h = image.getHeight(null);
    }
    public Ball(int x, int y) //if coordinates are passed
    {
        this.x = x;
        this.y = y;

        Random rand= new Random();
        ballXdir=(1+(rand.nextInt(3)))*-1;
        ballYdir=(1+(rand.nextInt(3)))*-1;

        ballImagePath = "src/Ball/Ball.png";
        ImageIcon imageIcon = new ImageIcon(ballImagePath);
        if (image != null) image = imageIcon.getImage();

        if (image != null) w = image.getWidth(null);
        if (image != null) h = image.getHeight(null);
    }

    //Moves the ball along with the paddle
    public void movewithpaddle(Player player)
    {
        this.x= player.getX();
        this.y=player.getY()-50;
    }

    //ball movement
    public void move()
    {
        if(!ballonPaddle)
        {
            this.x += ballXdir;
            this.y += ballYdir;
            // checks if ball goes out of dimensions
            if (x < w / 2)
            {
                reboundX();
            }

            if (x > 692 - w / 2)
            {
                reboundX();
            }
            if (y < getHeight() / 2)
            {
                reboundY();
            }
        }
    }

    //ball changes direction
    public void reboundX(){ ballXdir=-ballXdir;}
    public void reboundY(){ ballYdir=-ballYdir;}

    public int getX() { return x; }

    public int getY() { return y; }

    public int getWidth() { return w; }

    public int getHeight() { return h;}

    public Image getImage() { return image; }

    public void stopBall(){
       ballonPaddle=true;
    }

    public void resumeBall(){
        ballonPaddle=false;
    }

    public boolean isBallStuck(){
        return ballonPaddle;
    }

}


