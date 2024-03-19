import javax.swing.*;
import java.awt.*;

public class GameFrame  extends JFrame {

    GamePanel panel;
    public GameFrame()  {
        GamePanel.setGameOver(false);
        Brick.getBricks().clear();
        for (Brick brick1 : Brick.getInitialBricks()) {
            Brick.getBricks().add(brick1);
        }
        Item.getItems().clear();
        panel = new GamePanel();


        this.add(panel);
        this.setTitle("BrickBreaker");
        this.setResizable(false);
       // this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        GamePanel.setGameOver(false);






    }
}
