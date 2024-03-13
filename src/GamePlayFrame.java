import javax.swing.*;
import java.awt.*;

public class GamePlayFrame extends JFrame {
    GamePlayPanel panel;

    JTextField nameArea;
    JLabel enterName;

    JButton button = new JButton();
    GamePlayFrame(){


        panel = new GamePlayPanel();
        this.add(panel);
        this.setTitle("BrickBreaker");
        this.setResizable(false);
        this.setBackground(Color.pink);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        button.setBounds(100,150,150,100);
panel.add(button);
        enterName = new JLabel("Enter your name");
        enterName.setBounds(300,400,80,50);
        panel.add(enterName);

//        nameArea = new JTextField();
//        nameArea.setBounds(100,200,500,200);
//        panel.add(nameArea);

    }
}
