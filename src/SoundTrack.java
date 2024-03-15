import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundTrack {
        private Clip clip;
        public void play(String filePath) {
            try {
                File audioFile = new File(filePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        public void pause() {

            if (clip != null && clip.isRunning()) {

                clip.stop();

            }
        }

    }
