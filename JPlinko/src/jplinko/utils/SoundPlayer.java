package jplinko.utils;

import java.io.BufferedInputStream;
import javax.sound.sampled.*;
import java.io.InputStream;

public class SoundPlayer {

    private Clip clip;

    public SoundPlayer(String fileName) {
        try {
            // Carica il file audio come risorsa dal classpath
            InputStream audioSrc = SoundPlayer.class.getResourceAsStream("/jplinko/utils/" + fileName);
            if (audioSrc == null) {
                System.out.println("File audio non trovato: " + fileName);
                return;
            }

            BufferedInputStream bufferedIn = new BufferedInputStream(audioSrc);

            // Ottieni lo stream audio
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            // Ottieni una Clip e aprila
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato audio non supportato: " + fileName);
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Risorsa audio non disponibile (Clip): " + fileName);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Errore nel caricamento audio: " + fileName);
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
