import java.awt.*;
import java.util.*;

public class Object
{
    Image img;
    int xCord=1280;
    int yCord;
   int width;
   int height;

   public Object()
   {
       setObject();
   }
    public void setObject()
    {
        Random gen  = new Random();
        int rand = gen.nextInt(2);
        if (rand==0)
        {
            img = Toolkit.getDefaultToolkit().getImage("bird.png");
            rand = gen.nextInt(2);
            if (rand==0)
                yCord=280;
            else
                yCord=360;
            width=115;
            height=80;
        }
        else
        {
            img = Toolkit.getDefaultToolkit().getImage("cacti.png");
            yCord=360;
            width=50;
            height=100;
        }
    }

    public void updateSpeed(int spd)
    {
        xCord-=spd;
    }
    public void resetXCord()
    {
        xCord=1280;
    }

    public Image returnImage()
    {
        return img;
    }

    public int returnXCord()
    {
        return xCord;
    }

    public int returnYCord()
    {
        return yCord;
    }

    public int returnWidth()
    {
        return width;
    }
    public int returnHeight()
    {
        return height;
    }
}
