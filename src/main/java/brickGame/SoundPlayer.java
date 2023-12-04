package brickGame;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundPlayer {
    private final MediaPlayer mediaPlayer;

    public SoundPlayer(String soundFilePath) {
        Media sound = new Media(new File(soundFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
    }

    public void play() {
        // Reset the mediaPlayer to its initial state
        mediaPlayer.stop();
        mediaPlayer.seek(mediaPlayer.getStartTime());

        // Play the sound
        mediaPlayer.play();
    }

    // New method to reset the MediaPlayer
    public void reset() {
        mediaPlayer.stop();
        mediaPlayer.seek(mediaPlayer.getStartTime());
    }
}

