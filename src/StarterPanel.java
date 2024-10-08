import javax.swing.*;
import java.awt.*;

public class StarterPanel extends JPanel {

    static final int GAME_WIDTH = 700;
    static final int GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    public StarterPanel(){

        Brick.getBricks().clear();
        for (Brick brick1 : Brick.getInitialBricks()) {
            Brick.getBricks().add(brick1);
        }
        GamePanel.setGameOver(false);
        Item.getItems().clear();

        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.pink);

    }
}
