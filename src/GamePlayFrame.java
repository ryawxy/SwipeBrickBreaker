import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GamePlayFrame extends JFrame {


    static final int GAME_WIDTH = 700;
    static final int GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    JButton startButton ;

    JPanel panel = new JPanel();

    JTextField enterName;

    JLabel name;
    JLabel color;

    JButton start;

    JLabel level;
    JButton back;
    private static String chosenLevel;
    private static  String chosenColor;
    JComboBox<String> levelSelection;
    JComboBox<String> colorSelection ;
    private static String playerName;
    public GamePlayFrame(){

        Brick.getBricks().clear();
        for (Brick brick1 : Brick.getInitialBricks()) {
            Brick.getBricks().add(brick1);
        }
        Item.getItems().clear();
        GamePanel.setGameOver(false);

        super.setTitle("Brick Breaker");
        this.setSize(750,650);

        panel = new JPanel();
        panel.setSize(800,700);
        panel.setBackground(Color.pink);

        name = new JLabel("Enter your name.");
        name.setBounds(100,150,200,100);
        panel.add(name);
        enterName = new JTextField();
        enterName.setBounds(100,220,80,30);
        panel.add(enterName);
        enterName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = enterName.getSelectedText();
            }
        });


        color = new JLabel("Choose a color.");
        color.setBounds(500,150,200,100);
        panel.add(color);
        colorSelection = new JComboBox<>();
        colorSelection.addItem("Red");
        colorSelection.addItem("Blue");
        colorSelection.addItem("Cyan");
        colorSelection.setBackground(Color.lightGray);
        colorSelection.setBounds(500,230,80,30);
        panel.add(colorSelection);
        colorSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenColor = (String) colorSelection.getSelectedItem();
            }
        });

        level = new JLabel("Choose a level");
        level.setBounds(300,240,200,100);
        panel.add(level);
        levelSelection = new JComboBox<>();
        levelSelection.addItem("easy");
        levelSelection.addItem("medium");
        levelSelection.addItem("hard");
        levelSelection.setBackground(Color.lightGray);
        levelSelection.setBounds(300,320,80,30);
        panel.add(levelSelection);
        levelSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenLevel = (String) levelSelection.getSelectedItem();
            }
        });





        start = new JButton("Start");
        start.setBounds(300,500,80,30);
        panel.add(start);
        System.out.println(chosenColor);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = enterName.getText();

                dispose();
                new GameFrame();
            }
        });

        back = new JButton("back");
        back.setBounds(10,10,80,40);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StarterFrame();
            }
        });

        this.setIconImage(new ImageIcon("logo.png").getImage());



        panel.setLayout(null);
        //  this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setContentPane(panel);
        this.setResizable(false);
        this.setVisible(true);

    }
    public static String getColor(){
        return chosenColor;
    }

    public static String getChosenLevel() {
        return chosenLevel;
    }

    public static String getPlayerName() {
        return playerName;
    }
}
