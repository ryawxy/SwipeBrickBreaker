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

    private int time = 0;

    private int numberofBricks;

    private boolean hasMoved = false;

    private boolean turn = false;

    Image image;

    Graphics graphics;

    private final int ballSize = 10;

    Brick brick = new Brick(0, 0, 0, 0, 0, 0);

    Timer timer;

    Timer timer2;

    int timeLeft = 0;

    Thread thread;

    private int score = numberofBricks - time / 5;

    boolean showDialog = true;
    boolean gameOver = false;

    int INITIAL_XPOSITION = 150;
    int INITIAL_YPOSITION = 590;

    double DELTA_X = 0;
    double DELTA_Y = 0;

    boolean earthquake = false;

    boolean disco = false;

    Timer timer3;
    Random rand = new Random();
    int timeLeft2;
    int endTurn = 0;
    Item item = new Item(0, 0, 0, 0, 0);
    int itemSize = 30;
    boolean speed = false;
    int timeLeft4 = 0;
    boolean strength = false;

    int timeLeft5 = 0;
    boolean dizzy = false;
    boolean hasReleased = false;

    boolean goUP = false;
    int numberOfBalls;

    SoundTrack soundTrack = new SoundTrack();

    public GamePanel() {

        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);

        this.setBackground(new Color(255, 183, 183));


        addMouseListener(this);
        addMouseMotionListener(this);

        thread = new Thread(this);
        thread.start();

        timer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                brick.addBrick();
                repaint();
            }
        });
        timer.start();


        timer3 = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!turn) {
                    item.addItem();
                    repaint();
                }
            }
        });
        timer3.start();

        if(SettingsFrame.isSound()){
            String path = "Hedwig_s-Theme.wav";
            File file = new File(path);
            File file2 = file.getAbsoluteFile();
            soundTrack.play(String.valueOf(file2));
        }







//        if(showDialog) {
//            timer2 = new Timer(100, new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if (gameOver) {
//                        if(showDialog) {
//                            JOptionPane.showMessageDialog(GamePanel.this, "Game over :(\n score: " + score);
//
//                            GameFrame parent = (GameFrame) SwingUtilities.getWindowAncestor(GamePanel.this);
//                            if (parent != null) {
//                                parent.dispose();
//                                timer2.setRepeats(false);
//                                showDialog = false;
//                                gameOver = false;
//                                new StarterFrame();
//
//
//                            }
//                        }
//                    }
//                }
//            });
//            timer2.start();
//        }


    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        //   draw(g);


        if (earthquake) {
            DELTA_Y = 1;
            DELTA_X = 1;
        }
        //bricks
        for (Brick brick : Brick.getBricks()) {

            if (brick.getNumber() > 0) {

                g.setColor(Color.RED);
                if (brick.getFeature() == 1) {
                    g.setColor(Color.GREEN);
                }
                if (brick.getFeature() == 2) {
                    g.setColor(Color.yellow);
                }
                if (disco) {
                    g.setColor(new Color(rand.nextInt(100), rand.nextInt(100), rand.nextInt(100)));
                }

                g.fillRect(brick.getx(), brick.gety(), (int) brick.getW(), (int) brick.getH());

                g.setColor(Color.BLACK);
                Font font = new Font("Arial", Font.BOLD, 20);
                g.setFont(font);
                g.drawString(Integer.toString(brick.getNumber()), brick.getx() + 20, brick.gety() + 25);

            }

        }
        //balls
        String color = GamePlayFrame.getColor();
        Color color1 = switch (color) {
            case "Red" -> Color.RED;
            case "Blue" -> Color.BLUE;
            case "Cyan" -> Color.CYAN;
            default -> null;
        };

        if (disco) {
            color1 = new Color(rand.nextInt(150), rand.nextInt(150), rand.nextInt(150));
        }
        for (Ball ball : Ball.getBalls()) {
            g.setColor(color1);
            g.fillOval(ball.getBallXPos(), ball.getBallYPose(), ballSize, ballSize);
            hasMoved = true;
        }

        //draw line
if(SettingsFrame.isAiming()) {
    g.setColor(Color.GREEN);
    if (disco) {
        g.setColor(new Color(rand.nextInt(150), rand.nextInt(150), rand.nextInt(150)));
    }
    Ball ball = Ball.getBalls().getFirst();
    if (ball.getBallYPose() >= 590) {
        g.drawLine(ball.getBallXPos(), ball.getBallYPose(), mouseXPos2, mouseYPose2);
    }
    if (disco) {
        GamePanel.this.setBackground(new Color(rand.nextInt(150), rand.nextInt(150), rand.nextInt(150)));
    } else {
        GamePanel.this.setBackground(new Color(255, 183, 183));
    }
}

        //draw items

        for (Item item1 : Item.getItems()) {
            if (item1.getFeature() <= 5 && item1.getFeature() > 0) {
                if (item1.getFeature() == 1) {
                    g.setColor(new Color(160, 32, 200));
                } else if (item1.getFeature() == 2) {
                    g.setColor(new Color(255, 151, 215));
                } else if (item1.getFeature() == 3) {
                    g.setColor(new Color(175, 193, 255));

                } else if (item1.getFeature() == 4) {
                    g.setColor(new Color(255, 165, 51));
                } else if (item1.getFeature() == 5) {
                    g.setColor(Color.BLACK);
                }
                g.fillOval(item1.getx(), item1.gety(), itemSize, itemSize);
            }
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

    public void moveItems() {
        if (!turn) {
            for (Item item1 : Item.getItems()) {

                item1.setY(item1.gety() + 1);
            }
        }
    }

    public void moveBricks() {

        if (hasPlayed() && !goUP) {

            for (Brick brick1 : Brick.getBricks()) {
                brick1.setY(brick1.gety() + 60);
                hasReleased = false;
            }
        }
        if (hasPlayed() && goUP) {

            for (Brick brick1 : Brick.getBricks()) {
                brick1.setY(brick1.gety() - 300);
            }
            goUP = false;
            hasReleased = false;
        }
        if (!turn && !hasPlayed()) {
            if(GamePlayFrame.getChosenLevel().equals("easy")){
            for (Brick brick : Brick.getBricks()) {
                brick.setY(brick.gety() + 1);
            }
            }else if(GamePlayFrame.getChosenLevel().equals("medium")){
                for (Brick brick : Brick.getBricks()) {
                    brick.setY(brick.gety() + 2);
                }
                }
            else if(GamePlayFrame.getChosenLevel().equals("hard")){
                for (Brick brick : Brick.getBricks()) {
                    brick.setY(brick.gety() + 3);
                }
            }
            }


        }


    public void changeSize() {

        int y = 0;
        if (earthquake) {
            DELTA_X = 0.6;
            DELTA_Y = 0.6;
        }
        Random rand = new Random();
        for (Brick brick : Brick.getBricks()) {
            int x = rand.nextInt(2);
            if (x == 0) {
                y = 1;
            } else {
                y = -1;
            }
            brick.setW((brick.getW() + DELTA_X * y));
            brick.setH((brick.getH() + DELTA_Y * y));
        }
    }

    public void moveBalls() {

//        int x = 0;
//        int y = 0;
//        if(numberOfBalls>1 && hasPlayed()){
//            for(Ball ball : Ball.getBalls()){
//                if(ball.getBallYPose()>= 590){
//                    x = ball.getBallXPos();
//                    y = ball.getBallYPose();
//                    break;
//                }
//                break;
//            }
//            for(Ball ball : Ball.getBalls()){
//                ball.setBallXPos(x);
//                ball.setBallYPose(y);
//            }
//        }

        Ball ball2 = Ball.getBalls().getFirst();

        if (mouseYPose - mouseYPose2 != 0 || mouseXPos - mouseXPos2 != 0) {
            if (!speed && !dizzy) {
                ballXDir = (mouseXPos2 - ball2.getBallXPos()) / 8;
                ballYDir = (mouseYPose2 - ball2.getBallYPose()) / 8;
            }
            if (speed && !dizzy) {
                ballXDir = (mouseXPos2 - ball2.getBallXPos()) / 5;
                ballYDir = (mouseYPose2 - ball2.getBallYPose()) / 5;
            }
            if (dizzy) {

//                int x = rand.nextInt(50);
//                int y = rand.nextInt(50);
//                ballXDir =  (x -ball2.getBallXPos() )/8;
//                ballYDir =  (y - ball2.getBallYPose())/8;
                ballXDir = -1 * (mouseXPos2 - ball2.getBallXPos()) / 8;
                ballYDir = (mouseYPose2 - ball2.getBallYPose()) / 8;

                for (Ball ball : Ball.getBalls()) {
                    ball.setBallXPos(ball.getBallXPos() + ballXDir);
                    ball.setBallYPose(ball.getBallYPose() + ballYDir);
                }
            }

        }
//        if (numberOfBalls > 1) {
//            Ball.getBalls().add(new Ball(Ball.getBalls().getFirst().getBallXPos() + ballXDir, Ball.getBalls().getFirst().getBallYPose() + ballYDir, ballSize, ballSize));
//        }
        if (!stop() && !dizzy) {
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
            dizzy = false;

        }

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
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (!turn) {
            mouseXPos = e.getX();
            mouseYPose = e.getY();

            hasReleased = true;
            for (Ball ball : Ball.getBalls()) {
                ball.setBallXPos(mouseXPos);
                ball.setBallYPose(mouseYPose);
            }

            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void invisible() {

        if (disco) {
            for (Brick brick1 : Brick.getBricks()) {
                int x = rand.nextInt(70);
                if (x == 1) {
                    brick1.setNumber(0);
                }
            }
        }
    }

    @Override
    public void run() {
        if (!gameOver) {
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
                    checkTurn();
                    moveBricks();
                    moveItems();
                    checkCollision();
                    hasPlayed();



                    if (earthquake) {
                        changeSize();
                        timeLeft++;
                    }
                    if (timeLeft >= 400) {
                        earthquake = false;

                        timeLeft = 0;
                    }

                    if (disco) {
                        invisible();
                        timeLeft2++;
                    }
                    if (timeLeft2 >= 400) {
                        disco = false;
                        timeLeft2 = 0;
                    }

                    if (speed) {
                        timeLeft4++;
                    }
                    if (timeLeft4 >= 500) {
                        speed = false;
                        timeLeft4 = 0;
                    }
                    if (strength) {
                        timeLeft5++;
                    }
                    if (timeLeft5 >= 500) {
                        timeLeft5 = 0;
                        strength = false;
                    }

                    System.out.println("first: " + Ball.getBalls().getFirst().getBallXPos());
                    System.out.println("second: " + Ball.getBalls().getLast().getBallXPos());


//                    if(endTurn == 1){
//                        for(Brick brick1 : Brick.getBricks()) {
//                            brick1.setY(brick1.gety()+10);
//                        }
//                        endTurn = 0;
//                    }
                    repaint();
                    delta--;

                }
            }
        }
    }

    public boolean stop() {
        for (Ball ball : Ball.getBalls()) {
            return ball.getBallYPose() >= GAME_HEIGHT - ballSize;
        }
        return false;
    }

    public void checkCollision() {

        for (Ball ball : Ball.getBalls()) {
            for (Brick brick : Brick.getBricks()) {

                if (brick.getNumber() > 0) {
                    Rectangle rect = new Rectangle(brick.getx(), brick.gety(), 60, 40);
                    Rectangle rect2 = new Rectangle(ball.getBallXPos(), ball.getBallYPose(), ballSize, ballSize);

                    if (rect.intersects(rect2)) {

                        ballXDir = -1 * ballXDir;
                        ballYDir = -1 * ballYDir;

                        if (brick.getNumber() == 1) {
                            numberofBricks++;
                            if (brick.getFeature() == 1) {
                                earthquake = true;


                            }
                            if (brick.getFeature() == 2) {
                                disco = true;
                            }
                        }
                        if (!strength) {
                            brick.setNumber(brick.getNumber() - 1);
                        } else {
                            brick.setNumber(brick.getNumber() - 2);
                        }

                        repaint();

                    }
                }
            }
        }
        for (Ball ball : Ball.getBalls()) {
            for (Item item1 : Item.getItems()) {
                if (item1.getFeature() <= 5 && item1.getFeature() > 0) {
                    Rectangle rect = new Rectangle(ball.getBallXPos(), ball.getBallYPose(), ballSize, ballSize);
                    Rectangle rect2 = new Rectangle(item1.getx(), item1.gety(), itemSize, itemSize);

                    if (rect.intersects(rect2)) {
                        ballXDir = -1 * ballXDir;
                        ballYDir = -1 * ballYDir;
                        if (item1.getFeature() == 2) {
                            speed = true;
                            item1.setFeature(10);
                        }
                        if (item1.getFeature() == 3) {
                            strength = true;
                            item1.setFeature(10);

                        }
                        if (item1.getFeature() == 4) {
                            dizzy = true;
                            item1.setFeature(10);
                        }
                        if (item1.getFeature() == 5) {
                            goUP = true;
                            item1.setFeature(10);
                        }
                    }
                    repaint();
                }

            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!turn) {

            mouseXPos2 = e.getX();
            mouseYPose2 = e.getY();


        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void changePosition() {
        for (Ball ball : Ball.getBalls()) {
            //        if(hasMoved()){
            if (ball.getBallYPose() >= 590 && ball.getBallXPos() != 150) {
                INITIAL_XPOSITION = ball.getBallXPos();
                INITIAL_YPOSITION = ball.getBallYPose();
                break;
            }
        }
        //   }
        for (Ball ball : Ball.getBalls()) {
            ball.setBallXPos(INITIAL_XPOSITION);
            ball.setBallYPose(INITIAL_YPOSITION);
        }
        Ball.getBalls().add(new Ball(INITIAL_XPOSITION, INITIAL_YPOSITION, ballSize, ballSize));
    }

    //   public boolean hasMoved(){
//        for(Ball ball : Ball.getBalls()){
//            if(ball.getBallXPos() != INITIAL_XPOSITION || ball.getBallYPose() != INITIAL_YPOSITION){
//                return true;
//
//            }
//        }
//        return false;
    //   }

    public void checkTurn() {
        for (Ball ball : Ball.getBalls()) {
            if (ball.getBallYPose() < 590) {
                turn = true;
                endTurn = 0;
            } else {
                turn = false;

            }
            if (ball.getBallYPose() >= 570) {
                endTurn = 1;
            }
        }
    }

    public boolean hasPlayed() {

        for (Ball ball : Ball.getBalls()) {
            if (ball.getBallYPose() >= 585 && hasReleased) {
                numberOfBalls++;

                return true;
            }
        }
        return false;
    }
}



