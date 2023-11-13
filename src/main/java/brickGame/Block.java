package brickGame;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

/**
 * The {@code Block} class represents a block in the brick game.
 * It includes methods for drawing the block, checking hits, and retrieving block properties.
 * <p>
 * <b>HTML Note:</b> The HTML tags can be used for formatting purposes in the generated Javadocs.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * Block block = new Block(row, column, color, type);
 * int hitResult = block.checkHitToBlock(xBall, yBall);
 * }
 * </pre>
 * </p>
 */
public class Block implements Serializable {
    private static Block block = new Block(-1, -1, Color.TRANSPARENT, 99);

    /**
     * The row position of the block.
     */
    public int row;

    /**
     * The column position of the block.
     */
    public int column;

    /**
     * Indicates whether the block is destroyed.
     */
    public boolean isDestroyed = false;

    private Color color;

    /**
     * The type of the block.
     */
    public int type;

    /**
     * The x-coordinate of the block.
     */
    public int x;

    /**
     * The y-coordinate of the block.
     */
    public int y;

    private int width = 100;
    private int height = 30;
    private int paddingTop = height * 2;
    private int paddingH = 50;
    public Rectangle rect;

    /**
     * Represents no hit direction.
     */
    public static int NO_HIT = -1;

    /**
     * Represents a hit to the right direction.
     */
    public static int HIT_RIGHT = 0;

    /**
     * Represents a hit to the bottom direction.
     */
    public static int HIT_BOTTOM = 1;

    /**
     * Represents a hit to the left direction.
     */
    public static int HIT_LEFT = 2;

    /**
     * Represents a hit to the top direction.
     */
    public static int HIT_TOP = 3;

    /**
     * Represents a normal block type.
     */
    public static int BLOCK_NORMAL = 99;

    /**
     * Represents a choco block type.
     */
    public static int BLOCK_CHOCO = 100;

    /**
     * Represents a star block type.
     */
    public static int BLOCK_STAR = 101;

    /**
     * Represents a heart block type.
     */
    public static int BLOCK_HEART = 102;

    /**
     * Constructs a {@code Block} object with the specified row, column, color, and type.
     *
     * @param row    The row position of the block.
     * @param column The column position of the block.
     * @param color  The color of the block.
     * @param type   The type of the block.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * Block block = new Block(row, column, color, type);
     * }
     * </pre>
     * </p>
     */
    public Block(int row, int column, Color color, int type) {
        this.row = row;
        this.column = column;
        this.color = color;
        this.type = type;

        draw();
    }

    private void draw() {
        x = (column * width) + paddingH;
        y = (row * height) + paddingTop;

        rect = new Rectangle();
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setX(x);
        rect.setY(y);

        if (type == BLOCK_CHOCO) {
            Image image = new Image("choco.jpg");
            ImagePattern pattern = new ImagePattern(image);
            rect.setFill(pattern);
        } else if (type == BLOCK_HEART) {
            Image image = new Image("heart.jpg");
            ImagePattern pattern = new ImagePattern(image);
            rect.setFill(pattern);
        } else if (type == BLOCK_STAR) {
            Image image = new Image("star.jpg");
            ImagePattern pattern = new ImagePattern(image);
            rect.setFill(pattern);
        } else {
            rect.setFill(color);
        }
    }

    /**
     * Checks the hit direction of the ball to the block.
     *
     * @param xBall The x-coordinate of the ball.
     * @param yBall The y-coordinate of the ball.
     * @return The hit direction constant.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * int hitResult = block.checkHitToBlock(xBall, yBall);
     * }
     * </pre>
     * </p>
     */
    public int checkHitToBlock(double xBall, double yBall) {

        if (isDestroyed) {
            return NO_HIT;
        }

        if (xBall >= x && xBall <= x + width && yBall == y + height) {
            return HIT_BOTTOM;
        }

        if (xBall >= x && xBall <= x + width && yBall == y) {
            return HIT_TOP;
        }

        if (yBall >= y && yBall <= y + height && xBall == x + width) {
            return HIT_RIGHT;
        }

        if (yBall >= y && yBall <= y + height && xBall == x) {
            return HIT_LEFT;
        }

        return NO_HIT;
    }

    /**
     * Gets the padding at the top of the blocks.
     *
     * @return The padding at the top of the blocks.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * int paddingTop = Block.getPaddingTop();
     * }
     * </pre>
     * </p>
     */
    public static int getPaddingTop() {
        return block.paddingTop;
    }

    /**
     * Gets the horizontal padding of the blocks.
     *
     * @return The horizontal padding of the blocks.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * int paddingH = Block.getPaddingH();
     * }
     * </pre>
     * </p>
     */
    public static int getPaddingH() {
        return block.paddingH;
    }

    /**
     * Gets the height of the blocks.
     *
     * @return The height of the blocks.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * int height = Block.getHeight();
     * }
     * </pre>
     * </p>
     */
    public static int getHeight() {
        return block.height;
    }

    /**
     * Gets the width of the blocks.
     *
     * @return The width of the blocks.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * int width = Block.getWidth();
     * }
     * </pre>
     * </p>
     */
    public static int getWidth() {
        return block.width;
    }
}
