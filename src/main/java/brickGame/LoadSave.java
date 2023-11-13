package brickGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * The {@code LoadSave} class is responsible for reading saved game data from a file.
 * It provides methods to deserialize and load the saved state of the game.
 * <p>
 * <b>HTML Note:</b> The HTML tags can be used for formatting purposes in the generated Javadocs.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * LoadSave loader = new LoadSave();
 * loader.read();
 * }
 * </pre>
 * </p>
 */

public class LoadSave {
    /** Indicates whether a heart block exists in the saved game state. */
    public boolean isExistHeartBlock;

    /** Indicates whether the game is in a gold status. */
    public boolean isGoldStauts;

    /** Indicates the direction of the ball's vertical movement. */
    public boolean goDownBall;

    /** Indicates the direction of the ball's horizontal movement. */
    public boolean goRightBall;

    /** Indicates collision with the break (paddle). */
    public boolean collideToBreak;

    /** Indicates collision with the break and moving to the right. */
    public boolean collideToBreakAndMoveToRight;

    /** Indicates collision with the right wall. */
    public boolean collideToRightWall;

    /** Indicates collision with the left wall. */
    public boolean collideToLeftWall;

    /** Indicates collision with a block on the right. */
    public boolean collideToRightBlock;

    /** Indicates collision with the bottom of a block. */
    public boolean collideToBottomBlock;

    /** Indicates collision with a block on the left. */
    public boolean collideToLeftBlock;

    /** Indicates collision with the top of a block. */
    public boolean collideToTopBlock;

    /** Represents the current level in the saved game state. */
    public int level;

    /** Represents the score in the saved game state. */
    public int score;

    /** Represents the number of remaining lives (heart) in the saved game state. */
    public int heart;

    /** Represents the count of destroyed blocks in the saved game state. */
    public int destroyedBlockCount;

    /** Represents the x-coordinate of the ball in the saved game state. */
    public double xBall;

    /** Represents the y-coordinate of the ball in the saved game state. */
    public double yBall;

    /** Represents the x-coordinate of the break (paddle) in the saved game state. */
    public double xBreak;

    /** Represents the y-coordinate of the break (paddle) in the saved game state. */
    public double yBreak;

    /** Represents the center x-coordinate of the break (paddle) in the saved game state. */
    public double centerBreakX;

    /** Represents the time in the saved game state. */
    public long time;

    /** Represents the gold time in the saved game state. */
    public long goldTime;

    /** Represents the velocity of the ball in the saved game state. */
    public double vX;

    /** Represents the list of serialized blocks in the saved game state. */
    public ArrayList<BlockSerializable> blocks = new ArrayList<>();

    /**
     * Reads the saved game data from a file and initializes the state of the game.
     * <p>
     * <b>HTML Note:</b> This method demonstrates the usage of HTML in the Javadoc comments.
     * </p>
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * LoadSave loader = new LoadSave();
     * loader.read();
     * }
     * </pre>
     * </p>
     */
    public void read() {


        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File(Main.savePath)));


            level = inputStream.readInt();
            score = inputStream.readInt();
            heart = inputStream.readInt();
            destroyedBlockCount = inputStream.readInt();


            xBall = inputStream.readDouble();
            yBall = inputStream.readDouble();
            xBreak = inputStream.readDouble();
            yBreak = inputStream.readDouble();
            centerBreakX = inputStream.readDouble();
            time = inputStream.readLong();
            goldTime = inputStream.readLong();
            vX = inputStream.readDouble();


            isExistHeartBlock = inputStream.readBoolean();
            isGoldStauts = inputStream.readBoolean();
            goDownBall = inputStream.readBoolean();
            goRightBall = inputStream.readBoolean();
            collideToBreak = inputStream.readBoolean();
            collideToBreakAndMoveToRight = inputStream.readBoolean();
            collideToRightWall = inputStream.readBoolean();
            collideToLeftWall = inputStream.readBoolean();
            collideToRightBlock = inputStream.readBoolean();
            collideToBottomBlock = inputStream.readBoolean();
            collideToLeftBlock = inputStream.readBoolean();
            collideToTopBlock = inputStream.readBoolean();


            try {
                blocks = (ArrayList<BlockSerializable>) inputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
