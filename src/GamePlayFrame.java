import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePlayFrame extends JFrame {

    GameplayPanel panel2;

    static final int GAME_WIDTH = 700;
    static final int GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

   JButton startButton ;

   JPanel panel = new JPanel();

   JTextField enterName;

   JLabel name;
   JLabel color;

   JButton start;

  private static  String chosenColor;

   String enteredName;

   JComboBox<String> colorSelection ;
    public GamePlayFrame(){

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



        start = new JButton("Start");
        start.setBounds(300,300,80,30);
        panel.add(start);
        System.out.println(chosenColor);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GameFrame();
            }
        });





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
}
