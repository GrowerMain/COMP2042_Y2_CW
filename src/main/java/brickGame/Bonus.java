package brickGame;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.Random;

/**
 * The {@code Bonus} class represents a bonus object in the game.
 * <p>
 * <b>HTML Note:</b> The HTML tags can be used for formatting purposes in the generated Javadocs.
 * </p>
 * <p>
 * Example usage:
 * </p>
 * <pre>
 * {@code
 * Bonus bonus = new Bonus(row, column);
 * }
 * </pre>
 */
public class Bonus implements Serializable {

    /**
     * The instance of the block.
     */
    public Rectangle choco;

    /**
     * The x-coordinate of the bonus item.
     */
    public double x;
    /**
     * The y-coordinate of the bonus item.
     */
    public double y;

    /**
     * The timestamp value.
     */
    public long timeCreated;

    /**
     * The boolean state of the bonus item.
     */
    public boolean taken = false;

    /**
     * Constructs a {@code Bonus} object at the specified row and column.
     *
     * @param row    The row at which the bonus is located.
     * @param column The column at which the bonus is located.
     *               <p>
     *               Example usage:
     *               </p>
     *               <pre>
     *               {@code
     *               Bonus bonus = new Bonus(row, column);
     *               }
     *               </pre>
     */
    public Bonus(int row, int column) {
        x = (column * (Block.getWidth())) + Block.getPaddingH() + (Block.getWidth() / 2) - 15;
        y = (row * (Block.getHeight())) + Block.getPaddingTop() + (Block.getHeight() / 2) - 15;

        draw();
    }

    /**
     * Draws the choco bonus object on the game screen.
     * The method initializes and configures a Rectangle representing the choco bonus,
     * sets its width, height, position (x, y), and fills it with an image pattern.
     * The image used for the pattern is randomly chosen between "bonus1.png" and "bonus2.png"
     * based on a random number generator.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * draw();
     * }
     * </pre>
     * </p>
     */
    private void draw() {
        choco = new Rectangle();
        choco.setWidth(30);
        choco.setHeight(30);
        choco.setX(x);
        choco.setY(y);

        String url;
        if (new Random().nextInt(20) % 2 == 0) {
            url = "bonus1.png";
        } else {
            url = "bonus2.png";
        }

        choco.setFill(new ImagePattern(new Image(url)));
    }


}
