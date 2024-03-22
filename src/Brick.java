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
    private int initialnum;
    private int number2;
    private static final ArrayList<Brick> bricks = new ArrayList<>();
    private static final ArrayList<Brick> initialBricks = new ArrayList<>();

    Timer timer;

    public Brick(int x,int y, double width, double height, int number, int feature,int initialnum,int number2) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.number = number;
        this.feature = feature;
        this.initialnum  = initialnum;
        this.number2 = number2;

    }

//    public static void setInitialBricks(){
//        bricks.clear();
//
//        Brick brick1 = new Brick(0,-100,60,40,2,0,2);
//        Brick brick2 = new Brick(300,-100,60,40,2,3,2);
//
//        bricks.add(brick1);
//        bricks.add(brick2);
//        initialBricks.add(brick1);
//        initialBricks.add(brick2);
//
//    }

    public static ArrayList<Brick> getBricks(){
        return bricks;

    }
    public static ArrayList<Brick> getInitialBricks(){
        return initialBricks;

    }

    public void addBrick() {

        switch (GamePlayFrame.getChosenLevel()) {
            case "medium" -> {
                Random rand = new Random();
                int x = rand.nextInt(400) + 100;
                int num = rand.nextInt(Brick.getBricks().size() + 1);
                int num2 = rand.nextInt(Brick.getBricks().size() + 1);
                Brick.getBricks().add(new Brick(0, 0, 60, 40, num, rand.nextInt(8), num,num));
                Brick.getBricks().add(new Brick(x, 0, 60, 40, num2, rand.nextInt(8), num2,num2));


            }
            case "hard" -> {
                Random rand = new Random();
                int x = rand.nextInt(250) + 300;
                int x2 = rand.nextInt(100) + 70;
                int num = rand.nextInt(Brick.getBricks().size() + 2);
                int num2 = rand.nextInt(Brick.getBricks().size() + 3);
                int num3 = rand.nextInt(Brick.getBricks().size() + 2);
                Brick.getBricks().add(new Brick(0, 0, 60, 40, num, rand.nextInt(8), num,num));
                Brick.getBricks().add(new Brick(x, 0, 60, 40, num2, rand.nextInt(10), num2,num2));
                Brick.getBricks().add(new Brick(x2, 0, 60, 40, num3, rand.nextInt(9), num3,num3));

            }
            case "easy" -> {
                Random rand = new Random();
                int x = rand.nextInt(500);
                int num = rand.nextInt(Brick.getBricks().size() + 1);
                Brick.getBricks().add(new Brick(x, 0, 60, 40, num, rand.nextInt(7), num,num));
            }
        }
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

    public int getInitialnum() {
        return initialnum;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }
}
