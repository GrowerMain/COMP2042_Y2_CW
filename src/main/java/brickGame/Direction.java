package brickGame;

/**
 * The {@code Direction} enum represents the possible directions for movement in the game.
 * It includes LEFT and RIGHT, indicating leftward and rightward movements, respectively.
 * Usage Example:
 * <pre>
 * {@code
 * // To represent a leftward movement
 * Direction left = Direction.LEFT;
 *
 * // To represent a rightward movement
 * Direction right = Direction.RIGHT;
 * }
 * </pre>
 */
public enum Direction {
    /**
     * Represents the left direction.
     * <p>Usage example:</p>
     * <pre>
     * {@code
     * Direction playerDirection = Direction.LEFT;
     * // Perform actions for left movement
     * }
     * </pre>
     */
    LEFT,

    /**
     * Represents the right direction.
     * <p>Usage example:</p>
     * <pre>
     * {@code
     * Direction playerDirection = Direction.RIGHT;
     * // Perform actions for right movement
     * }
     * </pre>
     */
    RIGHT
}
