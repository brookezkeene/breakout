package block;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Block {
    protected int lives;
    protected int blockRow;

    protected Image[] blockArray; // an array of images associated with the block at different stages
    protected ImageView block;

    protected double blockWidth;
    protected double blockHeight;

    /**
     * Constructor
     *
     * @param num - number of lives block has
     * @param x - x-coordinate of block position
     * @param y - y-coordinate of block position
     * @param row - block row in game map
     */
    public Block(int num, int x, int y, int row) {
        lives = num;
        blockRow = row;
    }

    // Returns ImageView Block Object
    public ImageView getBlockImage() {
        return block;
    }

    // Returns Block's Row in Game Map
    public int getRow() {
        return blockRow;
    }

    // Returns Block's Minimum X Position
    public double getMinX() {
        return block.getBoundsInParent().getMinX();
    }

    // Returns Block's Minimum Y Position
    public double getMinY() {
        return block.getBoundsInParent().getMinY();
    }

    // Returns Block's Maximum X Position
    public double getMaxX() {
        return block.getBoundsInParent().getMaxX();
    }

    // Returns Block's Maximum Y Position
    public double getMaxY() {
        return block.getBoundsInParent().getMaxY();
    }

    // Returns Block's Width
    public double getWidth() {
        return blockWidth;
    }

    // Returns Block's Height
    public double getHeight() {
        return blockHeight;
    }

    // Returns Block's Lives
    public int getLives() {
        return lives;
    }

    // Changes Block's Lives and Updates Block Image
    public void setLives(int newStatus) {
        lives = newStatus;
        // change block image based on block's new lives count
        block.setImage(blockArray[lives]);
    }
}