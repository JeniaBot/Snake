package snake;

import snake.object.Apple;
import snake.object.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeMain extends JPanel implements ActionListener {
    public static JFrame jFrame;
    public static final int SCALE=32; // razmer setki  v pixel
    public static final int WIDTH=20; // kletki v wirinu
    public static final int HEIGHT=20; // kletok v visotu

    public static int speed=10;

    Timer timer = new   Timer(1000/speed,this);
    Apple apple =new Apple((int)(Math.random()* SnakeMain.WIDTH-1),
            (int)(Math.random()* SnakeMain.HEIGHT-1));


    Snake snake=new Snake(5,6,5,5);

    public SnakeMain(){
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);

    }

    public void paint(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,WIDTH*SCALE,HEIGHT*SCALE);
        for (int x = 0; x < WIDTH*SCALE; x+=SCALE) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(x,0,x,HEIGHT*SCALE);
        }

        for (int y = 0; y < HEIGHT*SCALE; y+=SCALE) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(0,y,WIDTH*SCALE,y);
        }
        //otrisovka zmeii
        for (int i = 0; i < snake.length; i++) {
            graphics.setColor(Color.GREEN);
            graphics.fillRect(snake.snakeX[i]*SCALE+2,snake.snakeY[i]*SCALE+2,
                    SCALE-3,SCALE-3);
            graphics.setColor(Color.GRAY);
            graphics.fillRect(snake.snakeX[0]*SCALE+2,snake.snakeY[0]*SCALE+2,
                    SCALE-3,SCALE-3);

        }
        graphics.setColor(Color.RED);
        graphics.fillOval(apple.posX*SCALE+4,
                apple.posY *SCALE+4,
                SCALE-8,SCALE-8);
    }

    public static void main(String[] args) {
        jFrame=new JFrame("Snake");
        jFrame.setSize(WIDTH*SCALE+2,HEIGHT*SCALE+31);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(new SnakeMain());
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        repaint();

        if(snake.snakeX[0]==apple.posX&&snake.snakeY[0]==apple.posY){
            apple.setRansomPosition();
            snake.length++;
        }
        for (int i = 1; i < snake.length; i++) {
            if(snake.snakeX[i]==apple.posX&&snake.snakeY[i]==apple.posY){
                apple.setRansomPosition();
            }
            if(snake.snakeX[0]==snake.snakeX[i]&&snake.snakeY[0]==snake.snakeY[i]){
                timer.stop();
                JOptionPane.showMessageDialog(null,"Game Over. Start again?");
                jFrame.setVisible(false);
                snake.length=2;
                snake.direction=0;
                apple.setRansomPosition();
                jFrame.setVisible(true);
                timer.start();
            }
        }
    }
    public  class KeyBoard extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent event) {
            int key =event.getKeyCode();

            if(key ==KeyEvent.VK_UP&&snake.direction!=2){
                snake.direction=0;
            }
            if(key ==KeyEvent.VK_DOWN&&snake.direction!=0 ){
                snake.direction=2;
            }
            if(key ==KeyEvent.VK_LEFT&&snake.direction!=1){
                snake.direction=3;
            }
            if(key ==KeyEvent.VK_RIGHT&&snake.direction!=3){
                snake.direction=1;
            }
        }
    }

}
