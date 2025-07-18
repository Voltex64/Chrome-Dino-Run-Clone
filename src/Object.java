import java.awt.*;
import java.util.*;

public class Object
{
    Image img;
    int xCord=1280;
    int yCord;
    int width;
    int height;
    String fileName;
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
            fileName="img/bird/birdDown.png";
            img = Toolkit.getDefaultToolkit().getImage(fileName);
            width=115;
            height=80;
            rand = gen.nextInt(2);

            if (rand==0)
                yCord=300;
            else
                yCord=360;
        }
        else
        {
            fileName="img/cacti/cacti.png";
            img = Toolkit.getDefaultToolkit().getImage("img/cacti/cacti.png");
            width=50;
            height=100;
            yCord=360;
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
    public void setAnim (String anim)
    {
        fileName=anim;
    }
    public void updateAnim()
    {
        img = Toolkit.getDefaultToolkit().getImage(fileName);
        width=115;
        height=80;
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
    public String returnFileName()
    {
        return fileName;
    }

}
