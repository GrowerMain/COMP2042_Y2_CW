/**
 * The {@code brickGame} module configuration for JavaFX application.
 * <p>
 * This module specifies the required dependencies and package access permissions
 * for the Brick Game JavaFX application.
 * </p>
 * <p>
 * The module requires the JavaFX FXML and Controls modules and opens the {@code brickGame}
 * package to JavaFX FXML. It exports the {@code brickGame} package, making it accessible
 * to other modules.
 * Example module-info.java:
 * </p>
 * <pre>
 * {@code
 * module brickGame {
 *     requires javafx.fxml;
 *     requires javafx.controls;
 *
 *     opens brickGame to javafx.fxml;
 *     exports brickGame;
 * }
 * }
 * </pre>
 */
module brickGame {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;

    opens brickGame to javafx.fxml;
    exports brickGame;
}