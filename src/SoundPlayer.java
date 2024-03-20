import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;

import javax.sound.sampled.LineUnavailableException;

import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;

import java.io.IOException;



public class SoundPlayer {

    private static Clip clip;

//    public static void main(String[] args) {
//
//        playSound("path/to/sound.wav");
//
//
//
//        // Simulate restarting the sound after 3 seconds
//
//        try {
//
//            Thread.sleep(3000);
//
//            restartSound();
//
//        } catch (InterruptedException e) {
//
//            e.printStackTrace();
//
//        }
//
//
//
//        // Simulate stopping the sound after 6 seconds
//
//        try {
//
//            Thread.sleep(3000);
//
//            stopSound();
//
//        } catch (InterruptedException e) {
//
//            e.printStackTrace();
//
//        }
//
//    }

    public static void playSound(String filePath) {

        try {

            File soundFile = new File(filePath);

            clip = AudioSystem.getClip();

            clip.open(AudioSystem.getAudioInputStream(soundFile));

            clip.start();

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {

            e.printStackTrace();

        }

    }



    public static void stopSound() {

        if (clip != null && clip.isRunning()) {

            clip.stop();

        }

    }



    public static void restartSound() {

        if (clip != null) {

            clip.setMicrosecondPosition(0);

            clip.start();

        }

    }



    public static boolean isSoundPlaying() {

        return clip != null && clip.isRunning();

    }

}