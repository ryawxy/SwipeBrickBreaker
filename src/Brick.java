import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Brick extends Rectangle {

    private int x;

    private int y;
    private int width;
    private int height;
    private int number;
    private static final ArrayList<Brick> bricks = new ArrayList<>();

    Timer timer;

    public Brick(int x,int y, int width, int height, int number) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.number = number;

    }

    public static void setInitialBricks(){

        Brick brick1 = new Brick(0,0,60,40,2);
        Brick brick2 = new Brick(300,0,60,40,1);

        bricks.add(brick1);
        bricks.add(brick2);

    }

    public static ArrayList<Brick> getBricks(){
        return bricks;

    }

    public void addBrick(){

            Random rand = new Random();
            Brick.getBricks().add(new Brick(0,0,60,40,rand.nextInt(Brick.getBricks().size()+1)));
            Brick.getBricks().add(new Brick(300,0,60,40,rand.nextInt(Brick.getBricks().size()+1)));
        }


    public void loop(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Brick.this.addBrick();
            }
        }, 10000);
    }

    public int getW() {
        return width;
    }

    public int getH() {
        return height;
    }

    public int getNumber() {
        return number;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
