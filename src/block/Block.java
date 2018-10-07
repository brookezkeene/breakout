package block;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * abstract class for Block objects
 * where all methods are defined
 *
 * @author Brooke Keene
 */
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
     * @param num   number of lives block has
     * @param x     x-coordinate of block position
     * @param y     y-coordinate of block position
     * @param row   block row in game map
     */
    public Block(int num, int x, int y, int row) {
        lives = num;
        blockRow = row;
        blockArray = new Image[3];
    }

    /**
     * returns the current ImageView object of the Block
     *
     * @return  ImageView of Block
     */
    public ImageView getBlockImage() {
        return block;
    }

    /**
     * returns row of the Block in the game map
     *
     * @return  Block's row
     */
    public int getRow() {
        return blockRow;
    }

    /**
     * returns the minimum x position of the Block
     *
     * @return  Block's minimum x-coordinate
     */
    public double getMinX() {
        return block.getBoundsInParent().getMinX();
    }

    /**
     * returns the minimum y position of the Block
     *
     * @return  Block's minimum y-coordinate
     */
    public double getMinY() {
        return block.getBoundsInParent().getMinY();
    }

    /**
     * returns the maximum x position of the Block
     *
     * @return  Block's maximum x-coordinate
     */
    public double getMaxX() {
        return block.getBoundsInParent().getMaxX();
    }

    /**
     * returns the maximum y position of the Block
     *
     * @return  Block's maximum y-coordinate
     */
    public double getMaxY() {
        return block.getBoundsInParent().getMaxY();
    }

    /**
     * returns width of the Block image
     *
     * @return  Block width
     */
    public double getWidth() {
        return blockWidth;
    }

    /**
     * returns height of the Block image
     *
     * @return  Block height
     */
    public double getHeight() {
        return blockHeight;
    }

    /**
     * returns number of Block lives
     *
     * @return  Block lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * decreases Block lives by one and updates Block image
     */
    public void decrementLives() {
        lives--;
        block.setImage(blockArray[lives]);
    }
}