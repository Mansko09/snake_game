import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class GamePanel extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;

    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    static final int UNIT_SIZE=20;
    static final int NUMBER_OF_UNITS = (WIDTH*HEIGHT)/(UNIT_SIZE*UNIT_SIZE);

    //hold x and y coordinates for body parts of the snake
    final int x[]=new int[NUMBER_OF_UNITS];
    final int y[]=new int[NUMBER_OF_UNITS];
    //initial length of the snake
    int length=5;
    int foodEaten;
    int foodX;
    int foodY;
    char direction = 'D';
    boolean running = false;
    Random random;
    Timer timer;

    GamePanel(){
        random=new Random();
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        play();
    }
    public void play(){
        addFood();
        running=true;

        timer = new Timer(80,this);
        timer.start();

    }

    private void addFood() {
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        draw(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
