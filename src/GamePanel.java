import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Handler;



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

    private final int ballSize = 12;

    Brick brick = new Brick(0, 0, 0, 0, 0, 0,0,0);

    Timer timer;

    Timer timer2;

    int timeLeft = 0;

    Thread thread;

    private int score;

    boolean showDialog = true;
    private static boolean gameOver = false;

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
    Item item = new Item(0, 0, 0, 0, 0,0);
    int itemSize = 30;
    boolean speed = false;
    int timeLeft4 = 0;
    boolean strength = false;

    int timeLeft5 = 0;
    boolean dizzy = false;
    boolean hasReleased = false;

    boolean goUP = false;
    int numberOfBalls;
    private int gameTime;
    String [] choice = {"play again","new game","first page"};

    SoundTrack soundTrack = new SoundTrack();
    private static final long FRAME_TIME = 1000/60;
    private static long timerr = System.currentTimeMillis();
    private static int seconds = 0;
    private static boolean idk = false;
    boolean pause = false;
    JButton pauseGame;
    boolean gameStarted = false;
    boolean bomb = false;
    SoundTrack explosion;
    boolean explode = false;
    int x;
    int y;
    ArrayList<Brick> explodedBricks = new ArrayList<>();
    boolean extraLive = false;
    boolean used = false;
    boolean hasDragged = false;
    Timer timer10;
    boolean addBall = false;
    int x2;
    int y2;
    ArrayList<String> plyerName = new ArrayList<>();
    int x3;
    int y3;
    boolean explosion2 = false;
    int time7;
    int feature;




    public GamePanel() {


        gameStarted = false;
        gameOver = false;
        //   setNewGame();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);

        this.setBackground(new Color(255, 183, 183));
        Brick brick1 = new Brick(0,-100,60,40,10,0,10,10);
        Brick brick2 = new Brick(300,-100,60,40,2,3,2,2);
        Brick.getBricks().add(brick1);
        Brick.getBricks().add(brick2);

        //    Ball.getBalls().add(new Ball(150,590,10,10));

        addMouseListener(this);
        addMouseMotionListener(this);

        thread = new Thread(this);
        thread.start();

        timer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pause && !gameOver) {
                    time++;
                    brick.addBrick();
                    repaint();
                }
            }
        });
        timer.start();


        timer3 = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!turn && !pause && !gameOver) {
                    if(GamePlayFrame.getChosenLevel().equals("easy")) {
                         feature = rand.nextInt(10);
                    }if(GamePlayFrame.getChosenLevel().equals("medium")) {
                        feature = rand.nextInt(15);
                    }if(GamePlayFrame.getChosenLevel().equals("hard")) {
                        feature = rand.nextInt(20);
                    }

                    int x = rand.nextInt(300);
                    int y = rand.nextInt(300);

                    if(feature == 6) {
                        if (!used) {
                            Item.getItems().add(new Item(x, y, 10, 10, feature,feature));
                            used = true;
                        }
                    }else{
                        Item.getItems().add(new Item(x, y, 10, 10, feature,feature));
                    }
                    repaint();
                }
            }
        });
        timer3.start();

//        if(SettingsFrame.isSound()){
//            String path = "Hedwig_s-Theme.wav";
//            File file = new File(path);
//            File file2 = file.getAbsoluteFile();
//            if(!gameOver) {
//                soundTrack.play(String.valueOf(file2));
//            }
//        }
        pauseGame = new JButton("Pause");
        pauseGame.setBounds(560,500,50,50);
        this.add(pauseGame);
        pauseGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pauseGame.getText().equals("Pause")){
                    pause = true;
                    pauseGame.setText("Resume");
                }else if(pauseGame.getText().equals("Resume")){
                    pause = false;
                    pauseGame.setText("Pause");
                }

            }
        });

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
                if (brick.getFeature() == 3) {
                    g.setColor(Color.WHITE);
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
        if(explosion2){

            image = new ImageIcon("untitled.png").getImage();

            g.drawImage(image,x3-70,y3-60,GamePanel.this);
        //    explosion2 = false;
        }
        //balls
        String color = GamePlayFrame.getColor();
        Color color1 = switch (color) {
            case "Red" -> Color.RED;
            case "Blue" -> Color.BLUE;
            case "Cyan" -> Color.CYAN;
            default -> Color.RED;
        };

        if (disco) {
            color1 = new Color(rand.nextInt(150), rand.nextInt(150), rand.nextInt(150));
        }

        for (int i = 0; i < Ball.getBalls().size(); i++) {
            Ball ball = Ball.getBalls().get(i);
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
            if (ball.getBallYPose() >= 580) {
                g.drawLine(ball.getBallXPos(), ball.getBallYPose(), mouseXPos2, mouseYPose2);
            }

        }
        if (disco) {
            GamePanel.this.setBackground(new Color(rand.nextInt(150), rand.nextInt(150), rand.nextInt(150)));

        } else {
            GamePanel.this.setBackground(new Color(255, 183, 183));
        }

        //draw items

        for (Item item1 : Item.getItems()) {
            if (item1.getFeature() <= 7 && item1.getFeature() > 0) {
                if (disco) {
                    color1 = new Color(rand.nextInt(150), rand.nextInt(150), rand.nextInt(150));
                    g.setColor(color1);
                }else {
                    if (item1.getFeature() == 1 || item1.getFeature()== 7) {
                        g.setColor(new Color(160, 32, 200));
                    } else if (item1.getFeature() == 2) {
                        g.setColor(new Color(255, 151, 215));
                    } else if (item1.getFeature() == 3) {
                        g.setColor(new Color(175, 193, 255));

                    } else if (item1.getFeature() == 4) {
                        g.setColor(new Color(255, 165, 51));
                    } else if (item1.getFeature() == 5) {
                        g.setColor(Color.BLACK);
                    } else if (item1.getFeature() == 6 && !used) {
                        g.setColor(new Color(100, 50, 50));

                    }
                }
                g.fillOval(item1.getx(), item1.gety(), itemSize, itemSize);
            }
        }

        //draw time

        g.setColor(Color.BLACK);
        Font font = new Font("Algerian",Font.BOLD,30);
        g.setFont(font);
        g.drawString("Time: "+String.valueOf(seconds),550,580);


        //draw score
        g.setColor(Color.BLACK);
        Font font1 = new Font("HelveticalNeue",Font.ITALIC,30);
        g.drawString("Score: "+score,550,550);

        Toolkit.getDefaultToolkit().sync();


    }

    public void moveItems() {
        if (!turn) {
            for (Item item1 : Item.getItems()) {

                item1.setY(item1.gety() + 1);
            }
        }
    }

    public void moveBricks() {
        if (!pause) {
            if (hasPlayed() && !goUP) {

                for (Brick brick1 : Brick.getBricks()) {
                    brick1.setY(brick1.gety() + 60);
                    hasReleased = false;
                    hasDragged = false;
                }
            }
            if (hasPlayed() && goUP) {

                for (Brick brick1 : Brick.getBricks()) {
                    brick1.setY(brick1.gety() - 300);
                }
                goUP = false;
                hasReleased = false;
                hasDragged = false;
            }
            if (!turn && !hasPlayed()) {
                if (GamePlayFrame.getChosenLevel().equals("easy")) {
                    for (Brick brick : Brick.getBricks()) {
                        brick.setY(brick.gety() + 1);
                    }
                } else if (GamePlayFrame.getChosenLevel().equals("medium")) {
                    for (Brick brick : Brick.getBricks()) {
                        brick.setY(brick.gety() + 2);
                    }
                } else if (GamePlayFrame.getChosenLevel().equals("hard")) {
                    for (Brick brick : Brick.getBricks()) {
                        brick.setY(brick.gety() + 3);
                    }
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

    public void moveBalls() throws InterruptedException {

//        if (Ball.getBalls().size() > 1) {
//            for (Ball ball : Ball.getBalls()) {
//
//                Ball ball2 = Ball.getBalls().getFirst();
//                if (!pause) {
//                    if (mouseYPose - mouseYPose2 != 0 || mouseXPos - mouseXPos2 != 0) {
//                        if (!speed && !dizzy) {
//                            ballXDir = (mouseXPos2 - ball.getBallXPos()) / 14;
//                            ballYDir = (mouseYPose2 - ball.getBallYPose()) / 14;
//
//                        }
//                        if (speed && !dizzy) {
//                            ballXDir = (mouseXPos2 - ball.getBallXPos()) / 5;
//                            ballYDir = (mouseYPose2 - ball.getBallYPose()) / 5;
//                        }
//                        if (dizzy) {
//
//                            ballXDir = -1 * (mouseXPos2 - ball.getBallXPos()) / 8;
//                            ballYDir = (mouseYPose2 - ball.getBallYPose()) / 8;
//
//
//                        }
//
//
//                    }
//
//                    checkCollision();
//                //    Thread.sleep(1);
//
//                    if (!stop()) {
//                        checkCollision();
//                        ball.setBallXPos(ball.getBallXPos() + ballXDir);
//                        ball.setBallYPose(ball.getBallYPose() + ballYDir);
//                        //   checkCollision();
//
//                    }
//
//                }
//
//                Thread.sleep(1);
//
//            }
//        }else{
            for (Ball ball : Ball.getBalls()) {

                Ball ball2 = Ball.getBalls().getFirst();
                if (!pause) {
                    if (mouseYPose - mouseYPose2 != 0 || mouseXPos - mouseXPos2 != 0) {
                        if (!speed && !dizzy) {
                            ballXDir = (mouseXPos2 - ball.getBallXPos()) / 12;
                            ballYDir = (mouseYPose2 - ball.getBallYPose()) / 12;

                        }
                        if (speed && !dizzy) {
                            ballXDir = (mouseXPos2 - ball.getBallXPos()) / 5;
                            ballYDir = (mouseYPose2 - ball.getBallYPose()) / 5;
                        }
                        if (dizzy) {
                            if(rand.nextInt(2)==0) {
                                ballXDir = -1 * (rand.nextInt(7));
                            }else{
                                ballXDir =  (rand.nextInt(7));
                            }
                            ballYDir = (rand.nextInt(5))+3;


                        }


                    }

          //          checkCollision();
//                    Thread.sleep(1);

                    if (!stop()) {
                   //     checkCollision();
                        ball.setBallXPos(ball.getBallXPos() + ballXDir);
                        ball.setBallYPose(ball.getBallYPose() + ballYDir);
                       //    checkCollision();

                    }

                }

         //       Thread.sleep(1);

            }
        }
  //  }


    @Override
    public void actionPerformed(ActionEvent e) {

        moveBricks();
        //  moveBalls();
        // checkCollision();
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

        if (!turn && !pause) {
            mouseXPos = e.getX();
            mouseYPose = e.getY();

            hasReleased = true;

                for (int i = 0; i < Ball.getBalls().size(); i++){
                    Ball ball = Ball.getBalls().get(i);
                    ball.setBallXPos(mouseXPos);
                ball.setBallYPose(mouseYPose);
            }

            if(dizzy){
                dizzy = false;
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
//            for(Item item1 : Item.getItems()){
//                int x = rand.nextInt(10);
//                if(x == 1){
//                    item1.setFeature(10);
//                }
//            }
        }
        else{
            for(Brick brick1 : Brick.getBricks()){
                brick1.setNumber(brick1.getNumber2());
            }
//            for(Item item1 : Item.getItems()){
//                item1.setFeature(item1.getFeature2());
//            }

        }
    }

    @Override
    public void run() {


        //     setNewGame();
        if (!gameOver && !pause) {
            //game loop
            long lastTime = System.nanoTime();
            double amountOfTicks = 60.0;
            double ns = 1000000000 / amountOfTicks;
            double delta = 0;


            long lastFrameTime = System.currentTimeMillis();

            if(SettingsFrame.isSound()){
                String path = "Hedwig_s-Theme.wav";
                File file = new File(path);
                File file2 = file.getAbsoluteFile();
                if(!gameOver) {
                    soundTrack.play(String.valueOf(file2));
                }
            }



            while (true) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;

                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - lastFrameTime;
                if (delta >= 1) {
                    if (!pause && !gameOver ) {

                        checkTurn();
                        moveBricks();
                        moveItems();
                        try {
                            checkCollision();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        try {

                            moveBalls();

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        countScore();
                        hasPlayed();
                        addBalls();
//                        firstOnGround();
//                        getTogether();

                        gameOver();

if(explosion2) {
    System.out.println("trueeeeeeee");
}



                        if (elapsedTime >= FRAME_TIME) {
                            if (currentTime - timerr >= 1000) {
                                seconds++;
                                timerr = currentTime;
                            }
                            lastFrameTime = currentTime;
                        }


                        if (earthquake) {
                            changeSize();
                            timeLeft++;
                        }
                        if (timeLeft >= 400) {
                            earthquake = false;

                            timeLeft = 0;
                        }
                        invisible();
                        if (disco) {

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
                        if (explosion2){
                            time7++;
                        }
                        if(time7>40){
                            explosion2 = false;
                            time7 = 0;
                        }



                        if (gameOver) {


                            FileWriter fw = null;
                            try {
                                fw = new FileWriter("highestScore.txt", true);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            BufferedWriter bw = new BufferedWriter(fw);
                            try {
                                bw.write(score+"/");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
//
                            try {
                                bw.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                            if(SettingsFrame.isSaving()) {
                                FileWriter fw1 = null;
                                try {
                                    fw1 = new FileWriter("data.txt", true);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                BufferedWriter bw1 = new BufferedWriter(fw1);
                                try {
                                    bw1.write(GamePlayFrame.getPlayerName() + "/" + score + "/" + LocalDate.now() + "/" + LocalTime.now());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    bw1.newLine();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                try {
                                    bw1.close();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }



                            JOptionPane.showMessageDialog(GamePanel.this, "Game over :(\n score: " + score);
                            //    gameOver = false;
                            int choice2 = JOptionPane.showOptionDialog(GamePanel.this,
                                    "choose an option", null, JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.PLAIN_MESSAGE, null, choice, choice[0]);
                            setNewGame();
                            repaint();
                            gameOver = false;


                            if (choice2 == 2) {
                                SettingsFrame.setAiming(false);
                                SettingsFrame.setSaving(false);
                                SettingsFrame.setSound(false);

                                // repaint();
                                GameFrame parent = (GameFrame) SwingUtilities.getWindowAncestor(GamePanel.this);
                                if (parent != null) {
                                       parent.remove(GamePanel.this);
                                    parent.dispose();
                                    try {
                                        new StarterFrame();
                                    } catch (FileNotFoundException e) {
                                        throw new RuntimeException(e);
                                    }
                                    break;
                                }
//
//                                seconds = 0;
//                                score = 0;

                                //               new StarterFrame();
                                //    break;


                            }
                            if(choice2 == 1) {
                                GameFrame parent = (GameFrame) SwingUtilities.getWindowAncestor(GamePanel.this);
                                if (parent != null) {
                                    parent.remove(GamePanel.this);
                                    parent.dispose();
                                    new GamePlayFrame();
                                    break;
                                }
                            }
                            if(choice2 == 0){
                                GameFrame parent = (GameFrame) SwingUtilities.getWindowAncestor(GamePanel.this);
                                if (parent != null) {
                                    parent.remove(GamePanel.this);
                                    parent.dispose();
                                    new GameFrame();
                                    break;
                                }

                            }


                            break;

                        }

                        repaint();
                        delta--;
                    }

                }
            }
        }
    }


    public boolean stop() {
        boolean allOnGround = true;
        for (Ball ball : Ball.getBalls()) {
            if (ball.getBallYPose() < 580) {
                allOnGround = false;
                break;
            }
        }
        return allOnGround;
    }

    public void checkCollision() throws InterruptedException {


        for(int i=0;i<Brick.getBricks().size();i++) {
            Brick brick = Brick.getBricks().get(i);
            Ball ball = Ball.getBalls().getFirst();
            if (brick.getNumber() > 0) {
                Rectangle rect = new Rectangle(brick.getx(), brick.gety(), 60, 40);
                Rectangle rect2 = new Rectangle(ball.getBallXPos(), ball.getBallYPose(), ballSize, ballSize);

                if (rect.intersects(rect2)) {

                    ballXDir = -1 * ballXDir;
                    ballYDir = -1 * ballYDir;

                                            if (brick.getNumber() <= ball.getNumber()) {
                                                numberofBricks += brick.getInitialnum();
                                            }
                            if (brick.getFeature() == 1 && brick.getNumber() <= ball.getNumber()) {
                                earthquake = true;


                            }
                            if (brick.getFeature() == 2 && brick.getNumber() <= ball.getNumber()) {
                                disco = true;
                            }
                            if (brick.getFeature() == 3 && brick.getNumber() <= ball.getNumber()) {
                                bomb = true;
                                x3 = brick.getx();
                                y3 = brick.gety();
                                explode = true;
                                explosion2 = true;
                            }
                            if(!strength && !bomb){
                                brick.setNumber(brick.getNumber()-ball.getNumber());
                                brick.setNumber2(brick.getNumber2()-ball.getNumber());
                            }
                            if(strength && !bomb){
                                brick.setNumber(brick.getNumber()-2*ball.getNumber());
                                brick.setNumber2(brick.getNumber2()-2*ball.getNumber());
                            }
                            if(!strength && bomb){
                                x = brick.getx();
                            y = brick.gety();
                      //      if (brick.getNumber() == 1) {
                                explodedBricks.add(brick);
                                brick.setNumber(brick.getNumber() - 50*Ball.getBalls().getFirst().getNumber());
                                brick.setNumber2(brick.getNumber2() - 50*Ball.getBalls().getFirst().getNumber());
                                for (int j = 0; j < Brick.getBricks().size(); j++) {
                                    Brick brick1 = Brick.getBricks().get(j);

                                    if ((brick1.getx() >= x - 50 || brick1.getx() <= x + 50) &&
                                            brick1.gety() >= y - 50 || brick1.gety() <= y + 50) {
                                        explodedBricks.add(brick1);
                                        numberofBricks += brick1.getInitialnum();
                                        brick1.setNumber(brick1.getNumber() - 50 * Ball.getBalls().getFirst().getNumber());
                                        brick1.setNumber2(brick1.getNumber2() - 50 * Ball.getBalls().getFirst().getNumber());
                                        SoundTrack player = new SoundTrack();
                                        String path = "explosion.wav";
                                        File file = new File(path);
                                        File file2 = file.getAbsoluteFile();
                                        soundTrack.play(String.valueOf(file2));
                                        bomb = false;
                                        soundTrack.pause();
                                    }
                                }
                                }
                            }

     //                   }


                }
            }


//        for (Ball ball : Ball.getBalls()) {
//            for (int i = 0; i < Brick.getBricks().size(); i++){
//                Brick brick = Brick.getBricks().get(i);
//
//                if (brick.getNumber() > 0) {
//                    Rectangle rect = new Rectangle(brick.getx(), brick.gety(), 60, 40);
//                    Rectangle rect2 = new Rectangle(ball.getBallXPos(), ball.getBallYPose(), ballSize, ballSize);
//
//                    if (rect.intersects(rect2)) {
//
//                        ballXDir = -1 * ballXDir;
//                        ballYDir = -1 * ballYDir;
//
//                        if (brick.getNumber() <= Ball.getBalls().getFirst().getNumber()) {
//                            numberofBricks += brick.getInitialnum();
//                            if (brick.getFeature() == 1) {
//                                earthquake = true;
//
//
//                            }
//                            if (brick.getFeature() == 2) {
//                                disco = true;
//                            }
//                            if (brick.getFeature() == 3) {
//                                bomb = true;
//                                x3 = brick.getx();
//                                y3 = brick.gety();
//                                explode = true;
//                                explosion2 = true;
//                            }
//                        }
//                        if (brick.getNumber() <= 0) {
//                            if (brick.getFeature() == 1) {
//                                earthquake = true;
//
//
//                            }
//                            if (brick.getFeature() == 2) {
//                                disco = true;
//                            }
//                            if (brick.getFeature() == 3) {
//                                bomb = true;
//                                explode = true;
//                            }
//                        }
//                        if (!strength && !bomb) {
//                            brick.setNumber(brick.getNumber() - Ball.getBalls().getFirst().getNumber());
//                            brick.setNumber2(brick.getNumber2()-Ball.getBalls().getFirst().getNumber());
//                        } else if (strength && !bomb) {
//                            brick.setNumber(brick.getNumber() - 2*Ball.getBalls().getFirst().getNumber());
//                            brick.setNumber2(brick.getNumber2()-2*Ball.getBalls().getFirst().getNumber());
//
//                        } else if (!strength && bomb) {
//                            x = brick.getx();
//                            y = brick.gety();
//                            if (brick.getNumber() == 1) {
//                                explodedBricks.add(brick);
//                                brick.setNumber(brick.getNumber() - 50*Ball.getBalls().getFirst().getNumber());
//                                brick.setNumber2(brick.getNumber2() - 50*Ball.getBalls().getFirst().getNumber());
//                                for (int j = 0; j < Brick.getBricks().size(); j++) {
//                                    Brick brick1 = Brick.getBricks().get(j);
//
//                                    if ((brick1.getx() >= x - 50 || brick1.getx() <= x + 50) &&
//                                            brick1.gety() >= y - 50 || brick1.gety() <= y + 50) {
//                                        explodedBricks.add(brick1);
//                                        numberofBricks += brick1.getInitialnum();
//                                        brick1.setNumber(brick1.getNumber() - 50*Ball.getBalls().getFirst().getNumber());
//                                        brick1.setNumber2(brick1.getNumber2() - 50*Ball.getBalls().getFirst().getNumber());
//                                        SoundTrack player = new SoundTrack();
//                                        String path = "explosion.wav";
//                                        File file = new File(path);
//                                        File file2 = file.getAbsoluteFile();
//                                        soundTrack.play(String.valueOf(file2));
//                                        bomb = false;
//                                        soundTrack.pause();
//                                    }
//                                }
//
//                                numberofBricks = numberofBricks - 1;
//                            }
//                        }
//
                        repaint();
//
//                    }
//                }
//            }
//        }


        for (Ball ball : Ball.getBalls()) {
            if (ball.getBallXPos() <= 2 || ball.getBallXPos() >= GAME_WIDTH - ballSize) {
                ballXDir = -1 * ballXDir;
            }
            if (ball.getBallYPose() <= 3) {
                ballYDir = -1 * ballYDir;
            }

        }
        for (Ball ball : Ball.getBalls()) {
            for (Item item1 : Item.getItems()) {
                if (item1.getFeature() <= 7 && item1.getFeature() > 0) {
                    Rectangle rect = new Rectangle(ball.getBallXPos(), ball.getBallYPose(), ballSize, ballSize);
                    Rectangle rect2 = new Rectangle(item1.getx(), item1.gety(), itemSize, itemSize);

                    if (rect.intersects(rect2)) {
                        ballXDir = -1 * ballXDir;
                        ballYDir = -1 * ballYDir;

                        if(item1.getFeature() == 1 || item1.getFeature() == 7){
                            addBall = true;
                            item1.setFeature(10);
                        }

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
                        }  if (item1.getFeature() == 6) {
                            extraLive = true;
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
        if (!turn && !pause) {

            mouseXPos2 = e.getX();
            mouseYPose2 = e.getY();

            hasDragged = true;

        }


    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }



    public void checkTurn() {
        for (Ball ball : Ball.getBalls()) {
            if (ball.getBallYPose() < 580) {
                turn = true;
                endTurn = 0;
            } else {
                turn = false;

            }
            if (ball.getBallYPose() >= 580) {
                endTurn = 1;
            }
        }
    }

    public boolean hasPlayed() {
        boolean b = true;
        for (int i = 0; i < Ball.getBalls().size(); i++){
            Ball ball = Ball.getBalls().get(i);
            if (ball.getBallYPose() >= 580 && hasReleased && hasDragged && b) {

                x = ball.getBallXPos();
                y = ball.getBallYPose();
//                if (!addBall) {
//                //    Ball.getBalls().add(new Ball(x, y, 10, 10));
//                    Ball.getBalls().getFirst().setNumber(Ball.getBalls().getFirst().getNumber()+1);
//
//
//
//
//                } else {
////                    Ball.getBalls().add(new Ball(x, y, 10, 10));
////                    Ball.getBalls().add(new Ball(x, y, 10, 10));
//                    Ball.getBalls().getFirst().setNumber(Ball.getBalls().getFirst().getNumber()+2);
//                    addBall = false;
//
//
//                }

                //   numberOfBalls++;
//                Ball.getBalls().removeLast();
//                Ball.getBalls().add(new Ball(x, y, 10, 10));
                return true;


            }
        }

        return false;
    }



    public void countScore(){
        score = numberofBricks-seconds/4;
        if(score<0){
            score = 0;
        }
    }
    public void gameOver(){
        for(Brick brick1 : Brick.getBricks()) {
            if (brick1.getNumber() > 0) {
                if (brick1.gety() >= 570) {
                    if (!extraLive) {
                        gameOver = true;
                    }
                }
            }

        }if(extraLive ){
            ArrayList<Brick> bricks = new ArrayList<>();
            for(Brick brick11 : Brick.getBricks()) {
                if (brick11.getNumber() > 0) {
                    if (brick11.gety() >= 570) {
                        bricks.add(brick11);
                    }
                }
            }
            if(bricks.size() >= 2){
                gameOver = true;
                extraLive = false;
            }
        }


    }


    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        GamePanel.gameOver = gameOver;
    }
    public void setNewGame() {
        if (gameOver) {

            Brick.getBricks().clear();
//            for (Brick brick1 : Brick.getInitialBricks()) {
//                Brick.getBricks().add(brick1);
//            }
            Item.getItems().clear();
            Ball.getBalls().clear();
            Ball.getBalls().add(new Ball(150,590,10,10,1));
            gameOver = false;
            seconds = 0;
            score = 0;
        }
    }
//    public void getTogether() {
//        boolean allOnGround = true;
//        for (Ball ball : Ball.getBalls()) {
//            if (ball.getBallYPose() < 580) {
//                allOnGround = false;
//                break;
//            }
//        }
//        if(allOnGround){
//            for(Ball ball1: Ball.getBalls()){
//                ball1.setBallYPose(y2);
//                ball1.setBallXPos(x2);
//            }
//        }
//    }
//
//    public void firstOnGround(){
//        for(Ball ball : Ball.getBalls()){
//            if(ball.getBallYPose()>=580){
//                x2 = ball.getBallXPos();
//                y2 = ball.getBallYPose();
//            }
//        }
//    }
    public void addBalls(){
        if(hasPlayed()){
            if (!addBall) {
                //    Ball.getBalls().add(new Ball(x, y, 10, 10));
                Ball.getBalls().getFirst().setNumber(Ball.getBalls().getFirst().getNumber()+1);




            } else {
//                    Ball.getBalls().add(new Ball(x, y, 10, 10));
//                    Ball.getBalls().add(new Ball(x, y, 10, 10));
                Ball.getBalls().getFirst().setNumber(Ball.getBalls().getFirst().getNumber()+2);
                addBall = false;


            }
        }
    }


}



