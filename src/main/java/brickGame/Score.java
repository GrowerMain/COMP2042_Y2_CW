package brickGame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The {@code Score} class provides methods to display score-related messages in the game.
 * It includes methods to show score updates, messages, game over, and victory messages.
 * <p>
 * <b>HTML Note:</b> The HTML tags can be used for formatting purposes in the generated Javadocs.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>
 * {@code
 * Score score = new Score();
 * score.show(x, y, scoreValue, main);
 * score.showMessage("Game Over", main);
 * score.showGameOver(main);
 * score.showWin(main);
 * }
 * </pre>
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
     * </p>
     * <pre>
     * {@code
     * score.show(x, y, scoreValue, main);
     * }
     * </pre>
     */
    public void show(final double x, final double y, int score, final Stage main) {

    }

    /**
     * Displays a pop-up message.
     *
     * @param message The message to be displayed.
     * @param primaryStage The primary stage of the application.
     * <p>
     * Example usage:
     * </p>
     * <pre>
     * {@code
     * new Score().showMessage("Game Saved", main.primaryStage());
     * }
     * </pre>
     */
    public void showMessage(String message, Stage primaryStage) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText(message);

            // Set the owner of the alert to the primary stage
            alert.initOwner(primaryStage);

            // Show and wait for the user to close the dialog
            alert.showAndWait();
        });
    }

    /**
     * Displays the game over message and a restart button.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     * @param score The final score achieved in the game.
     * <p>
     * Example usage:
     * </p>
     * <pre>
     * {@code
     * score.showGameOver(primaryStage);
     * }
     * </pre>
     */
    public void showGameOver(final Stage primaryStage, int score) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("Game Over! Your final score is: " + score);

            // Ensure the alert is displayed on top of the main stage
            alert.initOwner(primaryStage);

            alert.showAndWait();
        });
    }

    /**
     * Displays the win message when the player reaches level 5.
     *
     * @param primaryStage The primary stage of the application.
     * <p>
     *  Example usage:
     * </p>
     *  <pre>
     *  {@code
     *  score.showWin(primaryStage);
     *  }
     *  </pre>
     */
    public void showWin(final Stage primaryStage) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText(null);
            alert.setContentText("You have won the game! Congratulations!");

            // Ensure the alert is displayed on top of the main stage
            alert.initOwner(primaryStage);

            alert.showAndWait();
        });
    }
}
