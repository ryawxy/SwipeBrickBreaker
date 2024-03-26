import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SettingsFrame extends JFrame {

    JPanel panel;

    static final int GAME_WIDTH = 700;
    static final int GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    JCheckBox aim;
    private static boolean aiming = false;

    JButton okButton;

    JCheckBox themeSong;

    JCheckBox save;
    JButton back;

    private  static boolean saving = false;

    private static boolean sound = false;

    public SettingsFrame(){

        super.setTitle("Brick Breaker");
        this.setSize(750,650);

        panel = new JPanel();
        panel.setSize(800,700);
        panel.setBackground(Color.pink);



        aim = new JCheckBox("aiming");
        aim.setBounds(80,100,150,50);
        panel.add(aim);
//        aim.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                aiming = true;
//            }
//        });
//

        themeSong = new JCheckBox("theme song");
        themeSong.setBounds(80,200,150,50);
        panel.add(themeSong);
//        themeSong.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                sound = true;
//            }
//        });

        save = new JCheckBox("save");
        save.setBounds(80,300,150,50);
        panel.add(save);
//        save.json.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                saving = true;
//            }
//        });





        okButton = new JButton("ok");
        okButton.setBounds(300,500,120,30);
        panel.add(okButton);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound = themeSong.isSelected();
                if(save.isSelected()){
                    saving = true;
                }
                if(aim.isSelected()){
                    aiming = true;
                }
                dispose();
                try {
                    new StarterFrame();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        back = new JButton("back");
        back.setBounds(10,10,80,40);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new StarterFrame();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        panel.setLayout(null);
        //  this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setContentPane(panel);
        this.setResizable(false);
        this.setVisible(true);
        this.setIconImage(new ImageIcon("logo.png").getImage());

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

    public static void setSaving(boolean saving) {
        SettingsFrame.saving = saving;
    }

    public static void setSound(boolean sound) {
        SettingsFrame.sound = sound;
    }

    public static void setAiming(boolean aiming) {
        SettingsFrame.aiming = aiming;
    }
}
