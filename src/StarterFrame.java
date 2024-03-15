import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StarterFrame extends JFrame {

    StarterPanel panel;
    JButton startButton;

    JButton exit;

    JButton settings;
    public StarterFrame() {

        panel = new StarterPanel();
        this.add(panel);
        this.setTitle("BrickBreaker");
        this.setResizable(false);
        this.setBackground(Color.pink);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        startButton = new JButton("Start a New Game");
        startButton.setBounds(80, 100, 150, 50);
        panel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

                 new GamePlayFrame();
             //   new GameFrame();

            }
        });

        exit = new JButton("Exit");
        exit.setBounds(80,200,150,50);
        panel.add(exit);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        settings = new JButton("settings");
        settings.setBounds(80,300,150,50);
        panel.add(settings);

        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SettingsFrame();
            }
        });



    }
}
