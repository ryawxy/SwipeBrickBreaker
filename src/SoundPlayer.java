import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer implements LineListener {

    private static Clip clip;

    public static void playSound(String filePath) {

        try {


                File soundFile = new File(filePath);
                clip = AudioSystem.getClip();
            if(!clip.isRunning()) {
                clip.open(AudioSystem.getAudioInputStream(soundFile));
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
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

    @Override
    public void update(LineEvent event) {
        if(event.getType()==LineEvent.Type.STOP){
            if(SettingsFrame.isSound()) {
                clip.setMicrosecondPosition(0);
                playSound("Hedwig_s-Theme.wav");
            }
        }
    }
}