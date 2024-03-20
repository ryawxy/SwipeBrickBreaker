import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StarterFrame extends JFrame {

    StarterPanel panel;
    JButton startButton;
    JButton exit;
    JButton settings;
    JButton recent;
    JButton score;

    public StarterFrame() throws FileNotFoundException {

//        SettingsFrame.setSound(false);
//        SettingsFrame.setSaving(false);
//        SettingsFrame.setAiming(false);
        Brick.getBricks().clear();
        for (Brick brick1 : Brick.getInitialBricks()) {
            Brick.getBricks().add(brick1);
        }
        Item.getItems().clear();
        GamePanel.setGameOver(false);

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
                Brick.getBricks().clear();
                for (Brick brick1 : Brick.getInitialBricks()) {
                    Brick.getBricks().add(brick1);
                }
                Item.getItems().clear();
                GamePanel.setGameOver(false);
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

        recent = new JButton("recent games");
        recent.setBounds(80,400,150,50);
        panel.add(recent);
        recent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new RecentGameFrame();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        File file = new File("highestScore.txt");
        Scanner sc = new Scanner(file);


        if (file.length() > 0) {
            String s = sc.nextLine();
            String[] scores = s.split("/");


            if (scores.length > 0) {

                int max = Integer.parseInt(scores[0]);
                for (String string : scores) {
                    if (max < Integer.parseInt(string)) {
                        max = Integer.parseInt(string);
                    }
                }

                score = new JButton("highest score:" + max);
                score.setBounds(80, 500, 150, 50);
                panel.add(score);
            }
        }

        this.setIconImage(new ImageIcon("logo.png").getImage());


    }
}
