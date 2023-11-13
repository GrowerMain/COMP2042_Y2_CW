package brickGame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * The {@code Score} class provides methods to display score-related messages in the game.
 * It includes methods to show score updates, messages, game over, and victory messages.
 * <p>
 * <b>HTML Note:</b> The HTML tags can be used for formatting purposes in the generated Javadocs.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * Score score = new Score();
 * score.show(x, y, scoreValue, main);
 * score.showMessage("Game Over", main);
 * score.showGameOver(main);
 * score.showWin(main);
 * }
 * </pre>
 * </p>
 */
public class Score {
    /**
     * Displays the score update at the specified position.
     *
     * @param x     The x-coordinate for the score display.
     * @param y     The y-coordinate for the score display.
     * @param score The score value to be displayed.
     * @param main  The main game instance.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * score.show(x, y, scoreValue, main);
     * }
     * </pre>
     * </p>
     */
    public void show(final double x, final double y, int score, final Main main) {
        // Implementation...
    }

    /**
     * Displays a message at a predefined position.
     *
     * @param message The message to be displayed.
     * @param main    The main game instance.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * score.showMessage("Game Over", main);
     * }
     * </pre>
     * </p>
     */
    public void showMessage(String message, final Main main) {
        // Implementation...
    }

    /**
     * Displays the game over message and a restart button.
     *
     * @param main The main game instance.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * score.showGameOver(main);
     * }
     * </pre>
     * </p>
     */
    public void showGameOver(final Main main) {
        // Implementation...
    }

    /**
     * Displays the victory message.
     *
     * @param main The main game instance.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * score.showWin(main);
     * }
     * </pre>
     * </p>
     */
    public void showWin(final Main main) {
        // Implementation...
    }
}
