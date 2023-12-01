package brickGame;

import java.io.Serializable;

/**
 * The {@code BlockSerializable} class represents a serializable version of the Block object.
 * It is used for saving and loading game state.
 * <p>
 * <b>HTML Note:</b> The HTML tags can be used for formatting purposes in the generated Javadocs.
 * Example usage:
 * </p>
 * <pre>
 * {@code
 * BlockSerializable blockSerializable = new BlockSerializable(row, j, type);
 * }
 * </pre>
 */
public class BlockSerializable implements Serializable {
    /**
     * The row of the block.
     */
    public final int row;

    /**
     * The column of the block.
     */
    public final int j;

    /**
     * The type of the block.
     */
    public final int type;

    /**
     * Constructs a {@code BlockSerializable} object with the specified row, column, and type.
     *
     * @param row   The row of the block.
     * @param j     The column of the block.
     * @param type  The type of the block.
     * <p>
     * Example usage:
     * </p>
     * <pre>
     * {@code
     * BlockSerializable blockSerializable = new BlockSerializable(row, j, type);
     * }
     * </pre>
     */
    public BlockSerializable(int row, int j, int type) {
        this.row = row;
        this.j = j;
        this.type = type;
    }
}
