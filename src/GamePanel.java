import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, MouseListener, Runnable, MouseMotionListener {

    static final int GAME_WIDTH = 700;
    static final int GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    private int brickXPos;
    private int brickYPos;

    private int mouseXPos;
    private int mouseYPose;

    private int mouseXPos2;
    private int mouseYPose2;

    private int ballXDir = 5;
    private int ballYDir = 5;

    Image image;

    Graphics graphics;

    private final int ballSize = 10;

    Brick brick = new Brick(0,0,0,0,0);

    Timer timer;


    Thread thread;


    public GamePanel() {

        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);

        this.setBackground(Color.black);
        addMouseListener(this);
        addMouseMotionListener(this);

        thread = new Thread(this);
        thread.start();

        timer= new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brick.addBrick();
                repaint();
            }
        });
        timer.start();

    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        //   draw(g);


        int z = 20;
        //bricks
        for (Brick brick : Brick.getBricks()) {
if(brick.getNumber()>0) {
    g.setColor(Color.RED);
    g.fillRect(brick.getx(), brick.gety(), brick.getW(), brick.getH());


    g.setColor(Color.BLACK);
    Font font = new Font("Arial", Font.BOLD, 20);
    g.setFont(font);
    g.drawString(Integer.toString(brick.getNumber()), brick.getx() + 20, brick.gety() + 25);
}


        }
        //balls

        for (Ball ball : Ball.getBalls()) {
            g.setColor(Color.CYAN);
            g.fillOval(ball.getBallXPos(), ball.getBallYPose(), ballSize, ballSize);

        }

        //draw line

        g.setColor(Color.GREEN);
        Ball ball = Ball.getBalls().getFirst();
        if(ball.getBallYPose() >= 590) {
            g.drawLine(ball.getBallXPos(), ball.getBallYPose(), mouseXPos2, mouseYPose2);
        }
//        if(gameOver()){
//            g.setColor(Color.red);
//            JButton button = new JButton("Game over");
//            button.setBounds(150,150,100,50);
//            this.add(button);
//            button.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    new StarterFrame();
//                }
//            });
//        }


    }





    public void moveBricks() {

        for(Brick brick : Brick.getBricks()){
            brick.setY(brick.gety()+1);
        }

    }

    public void moveBalls() {

        Ball ball2 = Ball.getBalls().getFirst();

        if(mouseYPose-mouseYPose2!=0 || mouseXPos-mouseXPos2 != 0){
            ballXDir =  (mouseXPos2 -ball2.getBallXPos() )/8;
            ballYDir =  (mouseYPose - ball2.getBallYPose())/8;

        }
        if(!stop()) {
            for (Ball ball : Ball.getBalls()) {
                ball.setBallXPos(ball.getBallXPos() + ballXDir);
                ball.setBallYPose(ball.getBallYPose() + ballYDir);

            }
        }
        for (Ball ball : Ball.getBalls()) {
            if (ball.getBallXPos() <= 0 || ball.getBallXPos() >= GAME_WIDTH - ballSize) {
                ballXDir = -1 * ballXDir;
            }
            if (ball.getBallYPose() <= 0) {
                ballYDir = -1 * ballYDir;
            }
            if (ball.getBallYPose() >= GAME_HEIGHT - ballSize) {

            }

        }

    }

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        moveBricks();
        moveBalls();
        checkCollision();
        repaint();


    }

    @Override
    public void mouseClicked(MouseEvent e) {



    }

    @Override
    public void mousePressed(MouseEvent e) {
//        mouseXPos2 = e.getX();
//        mouseYPose2 = e.getY();
//        System.out.println(e.getX()+"/"+e.getY());
//

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        mouseXPos = e.getX();
        mouseYPose = e.getY();

        for (Ball ball : Ball.getBalls()) {
            ball.setBallXPos(mouseXPos);
            ball.setBallYPose(mouseYPose);
        }
        repaint();


    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void run() {

        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 2000000000 / amountOfTicks;
        double delta = 0;



        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                moveBalls();
                moveBricks();
                checkCollision();

                repaint();
                delta--;
            }
        }
    }

    public boolean stop() {
        for (Ball ball : Ball.getBalls()) {
        return ball.getBallYPose()  >= GAME_HEIGHT - ballSize;
        }
        return false;
    }
    public void checkCollision(){

        for(Ball ball : Ball.getBalls()){
            for(Brick brick : Brick.getBricks()){

//                System.out.println("ball: "+ball.getBallYPose()+"/"+ball.getBallYPose());
//
//                System.out.println("brick: "+brick.gety()+"/"+brick.getx());
                Rectangle rect = new Rectangle(brick.getx(),brick.gety(),60,40);
                if(ball.intersects(rect)){


                    System.out.println(1111);
                    ballXDir = -1*ballXDir;
                    ballYDir = -1*ballYDir;
//                    if(brick.getNumber() == 1){
//                        Brick.getBricks().remove(brick);
//                        repaint();
//                        System.out.println(Brick.getBricks().size());
//
//                    }else {
                        brick.setNumber(brick.getNumber() - 1);
                        repaint();
 //                   }

                }
            }
        }
    }
    public  boolean gameOver(){
        for(Brick brick : Brick.getBricks()){
            if(brick.gety() >= 590){
                return true;
            }
        }
        return false;
    }



    @Override
    public void mouseDragged(MouseEvent e) {


        mouseXPos2 = e.getX();
        mouseYPose2 = e.getY();






    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}

