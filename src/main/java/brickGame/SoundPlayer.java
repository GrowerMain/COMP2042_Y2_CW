package brickGame;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * The {@code SoundPlayer} class provides functionality to play sound files using JavaFX.
 * It utilizes the {@link javafx.scene.media.MediaPlayer} class to play sound from a specified file path.
 *
 * <p>
 *     Usage Example:
 * </p>
 * <pre>
 *     // Create a SoundPlayer instance with the path to a sound file
 *     SoundPlayer soundPlayer = new SoundPlayer("path/to/soundfile.mp3");
 *
 *     // Play the sound
 *     soundPlayer.play();
 *
 *     // Reset the sound player to its initial state
 *     soundPlayer.reset();
 * </pre>
 *
 * <p>Make sure to call {@link #play()} to play the sound and {@link #reset()} to reset the {@link MediaPlayer}
 * to its initial state before playing the sound again.</p>
 *
 * <p>Note: This class assumes that JavaFX is properly set up in your project and the required modules are available.</p>
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-12-05
 */
public class SoundPlayer {
    private final MediaPlayer mediaPlayer;

    /**
     * Constructs a {@code SoundPlayer} instance with the provided sound file path.
     *
     * @param soundFilePath The path to the sound file.
     */
    public SoundPlayer(String soundFilePath) {
        Media sound = new Media(new File(soundFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
    }

    /**
     * Plays the sound. If the sound is already playing, it stops and restarts from the beginning.
     */
    public void play() {
        // Reset the mediaPlayer to its initial state
        mediaPlayer.stop();
        mediaPlayer.seek(mediaPlayer.getStartTime());

        // Play the sound
        mediaPlayer.play();
    }

    /**
     * Resets the {@link MediaPlayer} to its initial state, stopping the sound if it's currently playing.
     */
    public void reset() {
        mediaPlayer.stop();
        mediaPlayer.seek(mediaPlayer.getStartTime());
    }
}


