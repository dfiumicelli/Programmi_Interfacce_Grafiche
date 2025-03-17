package jplinko.utils;

import javax.sound.sampled.*;
import java.io.InputStream;

public class SoundPlayer {

    private Clip clip;

    // Metodo per caricare il suono all'avvio
    public SoundPlayer(String fileName) {
        try {
            InputStream audioSrc = SoundPlayer.class.getResourceAsStream("../utils/" + fileName);
            if (audioSrc == null) {
                System.out.println("File audio non trovato: " + fileName);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo per riprodurre il suono
    public void playSound() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop(); // Ferma eventuale suono in corso
            }
            clip.setFramePosition(0); // Riparte dall'inizio
            clip.start();
        } else {
            System.out.println("Errore: Clip non inizializzato!");
        }
    }

}
