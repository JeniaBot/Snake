package snake.object;

import snake.SnakeMain;

public class Apple {
    public int posX;
    public int posY;
    public Apple(int x, int y){
        posX=x;
        posY=y;
    }

    public void setRansomPosition(){
        posX=(int)(Math.random()* SnakeMain.WIDTH-1);
        posY=(int)(Math.random()* SnakeMain.HEIGHT-1);
    }
}
