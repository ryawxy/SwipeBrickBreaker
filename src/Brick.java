import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Brick extends Rectangle {

    private int x;

    private int y;
    private double width;
    private double height;
    private int number;
    private int feature;
    private static final ArrayList<Brick> bricks = new ArrayList<>();

    Timer timer;

    public Brick(int x,int y, double width, double height, int number, int feature) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.number = number;
        this.feature = feature;

    }

    public static void setInitialBricks(){

        Brick brick1 = new Brick(0,0,60,40,2,0);
        Brick brick2 = new Brick(300,0,60,40,1,0);

        bricks.add(brick1);
        bricks.add(brick2);

    }

    public static ArrayList<Brick> getBricks(){
        return bricks;

    }

    public void addBrick(){

            Random rand = new Random();
            Brick.getBricks().add(new Brick(0,0,60,40,rand.nextInt(Brick.getBricks().size()+1),rand.nextInt(3)));
            Brick.getBricks().add(new Brick(300,0,60,40,rand.nextInt(Brick.getBricks().size()+1),rand.nextInt(3)));
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

    public double getW() {
        return width;
    }

    public double getH() {
        return height;
    }

    public int getNumber() {
        return number;
    }

    public void setW(double width) {
        this.width = width;
    }

    public void setH(double height) {
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

    public int getFeature() {
        return feature;
    }

}
