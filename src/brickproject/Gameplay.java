package brickproject;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Gameplay extends JPanel implements ActionListener
{
    Player player = Player.getInstance();
    private boolean play = true;
    int counter=0;
    int check=0;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    boolean isThere;
    private int delay = 10;
    protected ArrayList<Ball> balls= new ArrayList<Ball>();
    boolean PaddlePowerUp=false;
    boolean firePowerUp= true;
    boolean doubleBallPowerUp=false;
    private Map map;


    public Gameplay()
    {

        addKeyListener(new TAdapter());
        map = new Map(3, 7);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    //Paints the board(game)
    public void paint(Graphics g)
    {
        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        //map
        map.paint((Graphics2D) g);

        //borders
        g.setColor(Color.red);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        //score and lives
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);
        g.drawString("Lives Left: "+ player.getLives(),400,30);


        //paddle
        g.setColor(Color.magenta);
        g.drawImage(player.getImage(), player.getX() - player.getImage().getWidth(null) / 2, player.getY() - player.getImage().getHeight(null) / 2, null);
        g.drawRect(player.getX() - (player.getWidth() / 2), player.getY() - (player.getHeight() / 2), player.getWidth(), player.getHeight());

        //balls
        g.setColor(Color.magenta);
        if(balls.size()==0)
        {
            balls.add(new Ball());
        }
        for (int i = 0; i < balls.size(); i++)
        {
            Ball ball = balls.get(i);
            g.drawImage(ball.getImage(), ball.getX() - ball.getImage().getWidth(null) / 2, ball.getY() - ball.getImage().getHeight(null) / 2, null);
            g.drawRect(ball.getX() - ball.getWidth() / 2, ball.getY() - ball.getWidth() / 2, ball.getWidth(), ball.getHeight());
        }

        //Fire
        for (int i = 0; i < player.getBullets().size(); i++)
        {
            Fire fire = (Fire) player.getBullets().get(i);
            if (fire.isVisible())
            {
                g.drawImage(fire.getImage(), fire.getX() - fire.getImage().getWidth(null) / 2, fire.getY() - fire.getImage().getHeight(null) / 2, null);
            }
        }

        //check if bricks are all destroyed and prints game won
        if (totalBricks <= 0)
        {
            play = false;
            stopBalls();
            g.setColor(Color.cyan);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("YOU WON MABROOK!!!", 260, 300);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("PRESS ENTER TO GO AGAIN!!", 230, 350);
        }

        //checks for presence of balls on board
        if (!isBallPresent())
        {

            //checks if lives are over
            if(player.getLives()==0)
            {
                play = false;
                stopBalls();
                g.setColor(Color.cyan);
                g.setFont(new Font("serif", Font.BOLD, 25));
                g.drawString("Game Over BOSS!!,Scores:", 190, 300);
                g.setFont(new Font("serif", Font.BOLD, 25));
                g.drawString("PRESS ENTER TO GO AGAIN!!", 230, 350);

            }
            //if lives are left
            else
            {
                player.decreaseLives();
                balls.add(new Ball());
            }
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        repaint();
        counter++;

        for(int i=0;i<balls.size();i++)
        {
            Ball ball=balls.get(i);
            //checks for collision
            if(isBallPaddleCollision(ball))
            {
                if (isStickPowerUp())
                {
                    if (counter > 5)
                    {
                    ball.stopBall();
                    counter = 0;
                    }
                }
                ball.reboundY();
                ball.move();
            }
        }
        moveBullets(); //move bullets on board

        if(isDoubleBallPowerUp()) //if double ball powerup is gained, balls increase
        {
            splitBalls();
        }
        handleBrickCollisions(); //collisions handled

        if (play) //if game is being played
        {
            player.move();
            for(int m=0;m<balls.size();m++)
            {
                    Ball ball = balls.get(m);
                    if (ball.isBallStuck())
                    {
                        ball.movewithpaddle(player);
                    }
                    else
                    {
                        ball.move();
                    }
            }
        }

    }
    public boolean isStickPowerUp() { return PaddlePowerUp; }

    public boolean isFirePowerUp() { return firePowerUp; }

    public boolean isDoubleBallPowerUp() { return doubleBallPowerUp; }

    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent e)
        {
            int key = e.getKeyCode();
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) // if space pressed ball is released from paddle
            {
                boolean moved=false;
                for (int m = 0; m < balls.size(); m++)
                {
                    Ball ball = balls.get(m);
                    if(ball.isBallStuck())
                    {
                        ball.resumeBall();
                        ball.move();
                        moved=true;
                    }
                }
                if(isFirePowerUp()&&(!moved))
                {
                    player.FireBullets();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) //game begins once enter pressed
            {
                if (!play)
                {
                    player.get5Lives();
                    play = true;
                    balls=new ArrayList<Ball>();
                    Player playerX = Player.getInstance();
                    score = 0;
                    totalBricks = 21;
                    map = new Map(3, 7);
                    repaint();
                }
            }
            else
            {
                player.keyPressed(e);
            }
        }
    }
    public boolean isBallPresent()
    {
        for (int i = 0; i < balls.size(); i++)
        {
            Ball ball = balls.get(i);
            if (ball.getY() <= 570)
            {
                isThere = true;
                break;
            }
            else isThere=false;
        }
        return isThere;
    }
    public void stopBalls()
    {
        for (int i = 0; i < balls.size(); i++)
        {
            Ball ball = balls.get(i);
            ball.stopBall();
        }
    }
    public boolean isBallPaddleCollision(Ball ball)
    {
        return(new Rectangle(ball.getX(), ball.getY(), 20, 20).intersects(new Rectangle(player.getX() - (player.getWidth() / 2), player.getY() - (player.getHeight() / 2), player.getWidth(), player.getHeight())));
    }

    public void moveBullets()
    {
        for (int i = 0; i < player.getBullets().size(); i++)
        {
            ((Fire)player.getBullets().get(i)).move();
        }
    }

    public void splitBalls() //splitting balls if powerUp gained
    {
        int counter=balls.size();
        System.out.println(balls.size());
        check++;
        for(int i =0;i<counter;i++)
        {
            Ball ball= balls.get(i);
            balls.add(new Ball(ball.getX(),ball.getY()));
            balls.add(new Ball(ball.getX(),ball.getY()));
        }
        if(check>1)
        {
            doubleBallPowerUp=false;
        }
    }

    public void handleBrickCollisions()
    {
        A:
        for (int i = 0; i < map.map.length; i++)
        {
            for (int j = 0; j < map.map[0].length; j++)
            {
                if (map.map[i][j] > 0)
                {
                    int brickX = j * map.brickwidth + 80;
                    int brickY = i * map.brickheight + 50;
                    int brickwidth = map.brickwidth;
                    int brickheight = map.brickheight;

                    Rectangle rect = new Rectangle(brickX, brickY, brickwidth, brickheight);
                    Rectangle brickRect = rect;

                    for (int m = 0; m < balls.size(); m++)
                    {
                        Ball ball = balls.get(m);
                        Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), 20, 20);
                        //Checks if Ball touches a brick
                        if (ballRect.intersects(brickRect))
                        {
                            totalBricks=map.setBrickValue(i, j,totalBricks);
                            score += 5;
                            if (ball.getX() + 19 <= brickRect.x || ball.getX() + 1 >= brickRect.x + brickRect.width)
                            {
                                ball.reboundX();
                            }
                            else
                                ball.reboundY();
                            break A;
                        }
                    }

                    for (int k = 0; k < player.getBullets().size(); k++)
                    {
                        Fire bullet = (Fire) player.getBullets().get(k);
                        Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
                        if (bulletRect.intersects(brickRect))
                        {
                            totalBricks=map.setBrickValue(i, j,totalBricks);
                            
                            score += 5;
                            player.getBullets().remove(bullet);
                            break A;
                        }
                    }
                }
            }
        }
    }

}


