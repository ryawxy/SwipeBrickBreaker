import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {

    JPanel panel = new JPanel();

    static final int GAME_WIDTH = 700;
    static final int GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    JRadioButton aim;
    private static boolean aiming = false;

    JButton okButton;

    JRadioButton themeSong;

    JRadioButton save;

    private  static boolean saving = false;

    private static boolean sound = false;

    public SettingsFrame(){

        super.setTitle("Brick Breaker");
        this.setSize(750,650);

        panel = new JPanel();
        panel.setSize(800,700);
        panel.setBackground(Color.pink);



        aim = new JRadioButton("aiming");
        aim.setBounds(80,100,150,50);
        panel.add(aim);
        aim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aiming = true;
            }
        });


        themeSong = new JRadioButton("theme song");
        themeSong.setBounds(80,200,150,50);
        panel.add(themeSong);
        themeSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sound = true;
            }
        });

        save = new JRadioButton("save");
        save.setBounds(80,300,150,50);
        panel.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saving = true;
            }
        });





        okButton = new JButton("ok");
        okButton.setBounds(300,500,80,30);
        panel.add(okButton);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
             new StarterFrame();
            }
        });


        panel.setLayout(null);
        //  this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setContentPane(panel);
        this.setResizable(false);
        this.setVisible(true);


    }

    public static boolean isAiming() {
        return aiming;
    }

    public static boolean isSound() {
        return sound;
    }

    public static boolean isSaving() {
        return saving;
    }
}
