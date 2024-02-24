package brickproject;

public class BrickFactory
{
    Brick brick;
    public Brick getBrick(int choice,int row, int col) //returns type of brick according to choice passed
    {
        if(choice==1)
        {
            brick= new Brick("src/Brick1/1.png");
        }
        else if(choice==2)
        {
            brick= new Brick2("src/Brick2/1.png");
        }
        else if(choice==3)
        {
            brick= new Brick3("src/Brick3/1.png");
        }
        else if(choice==4)
        {
            brick= new Brick4("src/Brick4/1.png");
        }
        return brick;
    }
}
