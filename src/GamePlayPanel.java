import javax.swing.*;
import java.awt.*;

public class GamePlayPanel extends JPanel {

    static final int GAME_WIDTH = 700;
    static final int GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    GamePlayPanel(){

        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);

        this.setBackground(Color.pink);

    }
}
