import java.awt.*;
import java.util.ArrayList;

public class Ball extends Rectangle {

    private int width;
    private int height;

    private int xVelocity;
    private int yVelocity;

    private int x;
    private int y;
    private int number;
    private int xDir;
    private int yDir;

    private boolean collision = false;

    private static final ArrayList<Ball> balls = new ArrayList<>();

    public Ball(int x,int y, int width, int height,int number,int xDir,int yDir) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.number = number;
        this.xDir = xDir;
        this.yDir = yDir;


    }

    public static void setInitialBalls(){
        balls.add(new Ball(150,590,10,10,1,0,0));

    }


    public static ArrayList<Ball> getBalls(){
        return balls;
    }

    public int getBallXPos() {
        return x;
    }

    public void setBallXPos(int ballXPos) {
        this.x = ballXPos;
    }

    public int getBallYPose() {
        return y;
    }

    public void setBallYPose(int ballYPose) {
        this.y = ballYPose;
    }

    public void setXDirection(int xDirection){
        xVelocity = xDirection;
    }

    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }

    public void move(){
        x = x + xVelocity;
        y = y + yVelocity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getxDir() {
        return xDir;
    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
    }

    public int getyDir() {
        return yDir;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }
}
