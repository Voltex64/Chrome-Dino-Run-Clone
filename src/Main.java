import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class  Main extends JFrame implements KeyListener
{
    private static final int CANVAS_WIDTH =1280;
    private static final int CANVAS_HEIGHT =720;
    private static final int UPDATE_PERIOD =10;
    static public int dinoX=150;
    static public int dinoY =360;
    static public String dinoAnim= "img/dino/dinoLeft.png";
    static public int dinoWidth= 100;
    static public int dinoHeight= 100;
    public boolean isDucking= false;
    int yourScore=0;
    int constant=1;
    int speed=5;
    static Object obj= new Object();
    static String file= obj.returnFileName();
    Rectangle dinoRect = new Rectangle(dinoX,dinoY,dinoWidth,dinoHeight);
    Rectangle imgRect = new Rectangle(obj.returnXCord(),obj.returnYCord(),obj.returnWidth(),obj.returnHeight());


    //width and height
//Constructor to set up the GUI components and event handlers
    public  Main() {
        DrawCanvas canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        this.setContentPane(canvas);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("");
        this.setVisible(true);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Define an ActionListener to perform update at regular interval
        Timer dinoTime = getTimer();
        dinoTime.setDelay(500);
        dinoTime.start();
    }

    public Timer getTimer() {
        ActionListener updateTask= _ ->
        {
            update(); //update the (x,y)position
            repaint();//refresh te JFrame, call back paintComponent
        };
        //Allocate Timer to run updateTask's actionPerformed() after every delay sec
        ActionListener updateDinoTask= _ ->
        {
            updateAnim();
            repaint();//refresh te JFrame, call back paintComponent
        };

        Timer time = new Timer(UPDATE_PERIOD,updateTask);
        time.start();

        return new Timer(UPDATE_PERIOD,updateDinoTask);
    }

    public void update ()
    {
        if(obj.returnXCord()<5)
        {
            obj.setObject();
            yourScore++;
        }
        if(yourScore==5*constant)
            constant++;
        if (obj.returnXCord()<0)
        {
            obj.resetXCord();
            if(yourScore>=5*constant)
                speed+=3;
        }
        obj.updateSpeed(speed);
        if(dinoRect.intersects(imgRect))
        {
            System.out.println("Final Score: "+yourScore);
            System.exit(0);
        }
    }

    public void updateAnim()
    {

        switch (dinoAnim)
        {
            case "img/dino/dinoLeft.png" -> dinoAnim = "img/dino/dinoRight.png";
            case "img/dino/dinoRight.png" -> dinoAnim = "img/dino/dinoLeft.png";
            case "img/dino/dinoDuckLeft.png" -> dinoAnim = "img/dino/dinoDuckRight.png";
            case "img/dino/dinoDuckRight.png" -> dinoAnim = "img/dino/dinoDuckLeft.png";
        }

        file= obj.returnFileName();
        if (file.equals("img/bird/birdDown.png") || file.equals("img/bird/birdUp.png"))
        {
            if(file.equals("img/bird/birdDown.png"))
            {
                obj.setAnim("img/bird/birdUp.png");
                obj.updateAnim();
            }
            else
            {
                obj.setAnim("img/bird/birdDown.png");
                obj.updateAnim();
            }
        }


    }

    private class DrawCanvas extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            dinoRect.setBounds(dinoX,dinoY,dinoWidth,dinoHeight);
            imgRect.setBounds(obj.returnXCord(),obj.returnYCord(),obj.returnWidth(),obj.returnHeight());
            super.paintComponent(g); //paint parent's background
            Image img1 = Toolkit.getDefaultToolkit().getImage("img/background.png");
            g.drawImage(img1,-100,-250,this);
            Image img2 = Toolkit.getDefaultToolkit().getImage(dinoAnim);
            g.drawImage(img2,dinoX, dinoY,dinoWidth,dinoHeight,this);
            g.drawImage(obj.returnImage(), obj.returnXCord(), obj.returnYCord(), obj.returnWidth(), obj.returnHeight(), this);
            g.setColor(Color.black);
            Font f=new Font("Courier", Font.PLAIN,20);
            g.setFont(f);
            g.drawString(""+yourScore,1200,20);
        }
    }


    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Main::new);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent evt)
    {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                dinoAnim="img/dino/dino.png";
                dinoY =160;
                break;

            case KeyEvent.VK_DOWN:
                dinoAnim="img/dino/dinoDuckLeft.png";
                isDucking= true;
                dinoWidth=118;
                dinoHeight=85;
                dinoY =380;
                break;
        }
    }
    public void keyReleased(KeyEvent evt)
    {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_SPACE, KeyEvent.VK_DOWN:
                dinoY =360;
                dinoWidth=100;
                dinoHeight=100;
                dinoAnim="img/dino/dinoLeft.png";
                isDucking= false;
                break;
        }
    }
}

