import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class  Main extends JFrame implements KeyListener
{
    //Define Name-constants
    private static final int CANVAS_WIDTH =1280;
    private static final int CANVAS_HEIGHT =720;
    private static final int UPDATE_PERIOD =10;
    static public int dinoX=150;
    static public int dinoY =360;
    int yourScore=0;
    int speed=5;
    Object obj= new Object();
    Rectangle dinoRect = new Rectangle(dinoX,dinoY,100,100);
    Rectangle imgRect = new Rectangle(obj.returnXCord(),obj.returnYCord(),obj.returnWidth(),obj.returnHeight());


    //width and height
//Constructor to set up the GUI components and event handlers
    public  Main() {
        // the drawing canvas
        DrawCanvas canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        this.setContentPane(canvas);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("");
        this.setVisible(true);           //makes game visible
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Define an ActionListener to perform update at regular interval
        ActionListener updateTask= _ -> {
            update(); //update the (x,y)position
            repaint();//refresh te JFrame, call back paintComponent
        };
        //Allocate Timer to run updateTask's actionPerformed() after every delay sec


        Timer time = new Timer(UPDATE_PERIOD,updateTask);
        //time.setDelay(1);
        time.start();

    }
    public void update ()
    {
        if(obj.returnXCord()<20)
        {
            obj.setObject();
        }
        yourScore+= speed;
        obj.updateSpeed(speed);
        if (obj.returnXCord()<0)
        {
            obj.resetXCord();
            if(yourScore>=yourScore+1400)
                speed+=3;
        }
        if(dinoRect.intersects(imgRect))
        {
            System.out.println("Final Score: "+yourScore);
            System.exit(0);
        }
    }


    private class DrawCanvas extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            dinoRect.setBounds(dinoX,dinoY,100,100);
            imgRect.setBounds(obj.returnXCord(),obj.returnYCord(),50,100);
            super.paintComponent(g); //paint parent's background
            Image img1 = Toolkit.getDefaultToolkit().getImage("background.png");
            g.drawImage(img1,-100,-250,this);
            Image img2 = Toolkit.getDefaultToolkit().getImage("dino.png");
            g.drawImage(img2,dinoX, dinoY,100,100,this);
            g.drawImage(obj.returnImage(), obj.returnXCord(), obj.returnYCord(), obj.returnWidth(), obj.returnHeight(), this);
            g.setColor(Color.black);
            Font f=new Font("Courier", Font.PLAIN,20);
            g.setFont(f);
            g.drawString(""+yourScore,1200,20);
        }
    }




    //The Entry main method
    public static void main(String[] args)
    {
        //Let the constructor do the job
        SwingUtilities.invokeLater(Main::new);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent evt)
    {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                dinoY =160;
                break;

            case KeyEvent.VK_DOWN:
                dinoY =380;
                break;
        }
    }
    public void keyReleased(KeyEvent evt)
    {
        switch (evt.getKeyCode()) {

            case KeyEvent.VK_SPACE:
                dinoY =360;
                break;

            case KeyEvent.VK_DOWN:
                dinoY =360;
                break;
        }
    }
}

