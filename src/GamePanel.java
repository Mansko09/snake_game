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
    JButton restart = new JButton("restart");

    GamePanel(){
        random=new Random();
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.pink);
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
        foodX=random.nextInt((int)(WIDTH/UNIT_SIZE))*UNIT_SIZE;
        foodY=random.nextInt((int)(HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        draw(graphics);

    }

    public void move(){
        for (int i=length; i>0; i--){
            //shift the snake one unit to the desired direction
            x[i]=x[i-1];
            y[i]=y[i-1];

        }
        if (direction=='L'){
            x[0]=x[0]-UNIT_SIZE;
        }else if (direction=='R'){
            x[0]=x[0]+UNIT_SIZE;
        }else if (direction=='U'){
            y[0]=y[0]-UNIT_SIZE;
        }else{
            y[0]=y[0]+UNIT_SIZE;
        }
    }
    public void checkFood(){
        if(x[0]==foodX && y[0]==foodY){
            length++;
            foodEaten++;
            addFood();
        }
    }
    public void draw(Graphics graphics){
        if (running){
            graphics.setColor(Color.yellow);
            graphics.fillOval(foodX,foodY,UNIT_SIZE,UNIT_SIZE);

            graphics.setColor(Color.white);
            graphics.fillRect(x[0],y[0],UNIT_SIZE,UNIT_SIZE);

            for (int i=1; i<length;i++){
                graphics.setColor(Color.CYAN);
                graphics.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
            }
            graphics.setColor(Color.white);
            graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE,25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: "+foodEaten,(WIDTH - metrics.stringWidth("Score: "+ foodEaten))/2, graphics.getFont().getSize());
        }
        else {
            gameOver(graphics);
        }
    }
    public void checkHit(){
        // Check if head runs into its body
        for (int i = length; i > 0; i--){
            if (x[0] == x[i] && y[0] == y[i]){
                running = false;
            }
        }

        // Check if head runs into walls
        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT){
            // If the head goes out of bounds, automatically change direction to keep it within bounds
            if (x[0] < 0) {
                x[0] = WIDTH - UNIT_SIZE; // Teleport to the opposite side of the board
            } else if (x[0] >= WIDTH) {
                x[0] = 0; // Teleport to the opposite side of the board
            } else if (y[0] < 0) {
                y[0] = HEIGHT - UNIT_SIZE; // Teleport to the opposite side of the board
            } else {
                y[0] = 0; // Teleport to the opposite side of the board
            }
        }

        if(!running){
            timer.stop();
        }
    }

    public void gameOver(Graphics graphics){
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE,50));
        FontMetrics metrics =getFontMetrics(graphics.getFont());
        graphics.drawString("GAME OVER",(WIDTH-metrics.stringWidth("GAME OVER"))/2,HEIGHT/2);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE,25));
        metrics =getFontMetrics(graphics.getFont());
        graphics.drawString("Score: "+foodEaten,(WIDTH-metrics.stringWidth("Score: "+foodEaten))/2,graphics.getFont().getSize());

        // Remove existing restart button
        //this.remove(restart);

        restart.setBounds(250,(HEIGHT/6),100,40);
        restart.addActionListener(this);
        this.add(restart);
        restart.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (running) {
            move();
            checkFood();
            checkHit();
        }
        repaint();
        if (arg0.getSource() == restart) {
            running = true;
            length = 5; // Reset snake length
            foodEaten = 0; // Reset food eaten count
            direction = 'D'; // Reset direction
            for (int i = 0; i < length; i++) {
                // Reset snake position to default
                x[i] = WIDTH / 2 - i * UNIT_SIZE;
                y[i] = HEIGHT / 2;
            }
            play();
            restart.setVisible(false); // Hide restart button after game restarts

        }
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction!='R'){
                        direction='L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction!='L'){
                        direction='R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction!='D'){
                        direction='U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction!='U'){
                        direction='D';
                    }
                    break;
            }
        }
    }
}
