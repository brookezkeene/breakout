package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Block {
    // Using private variables so that they cannot be accessed outside this class without my permission
    // and so that I can dictate when and where the rest of my program should have access to them
    private int lives;          // number of lives the block has
    private double blockWidth;  // block's width
    private double blockHeight; // block's height
    private int blockRow;       // the row the block occurs in

    private Image[] blockArray; // an array of images associated with the block at different stages
    protected ImageView block;  // the ImageView object of the block

    // Different images for different blocks and their stages
    // I used private static final Strings as taught in class
    // because I do not want them to  be changed at any point in the code
    private static final String purple = "brick1.gif";
    private static final String purpleBroken = "brick1broken.gif";
    private static final String blue = "brick2.gif";
    private static final String blueBroken = "brick2broken.gif";
    private static final String green = "brick3.gif";
    private static final String greenBroken = "brick3broken.gif";
    private static final String yellow = "brick4.gif";
    private static final String yellowBroken = "brick4broken.gif";
    private static final String orange = "brick5.gif";
    private static final String orangeBroken = "brick5broken.gif";
    private static final String red = "brick6.gif";
    private static final String redBroken = "brick6broken.gif";

    /**
     * Constructor
     *
     * @param num - number of lives block has
     * @param x - x-coordinate of block position
     * @param y - y-coordinate of block position
     * @param row - block row in game map
     */
    public Block(int num, int x, int y, int row) { //** add power?
        lives = num;
        blockRow = row;

        blockArray = new Image[3];
        Image brokenImage;
        Image image;
        // determines the images associated with a block
        if (blockRow == 0) {
            brokenImage = new Image(getClass().getClassLoader().getResourceAsStream(purpleBroken));
            image = new Image(getClass().getClassLoader().getResourceAsStream(purple));
        }
        else if (blockRow == 1) {
            brokenImage = new Image(getClass().getClassLoader().getResourceAsStream(blueBroken));
            image = new Image(getClass().getClassLoader().getResourceAsStream(blue));
        }
        else if (blockRow == 2) {
            brokenImage = new Image(getClass().getClassLoader().getResourceAsStream(greenBroken));
            image = new Image(getClass().getClassLoader().getResourceAsStream(green));
        }
        else if (blockRow == 3) {
            brokenImage = new Image(getClass().getClassLoader().getResourceAsStream(yellowBroken));
            image = new Image(getClass().getClassLoader().getResourceAsStream(yellow));
        }
        else if (blockRow == 4) {
            brokenImage = new Image(getClass().getClassLoader().getResourceAsStream(orangeBroken));
            image = new Image(getClass().getClassLoader().getResourceAsStream(orange));
        }
        else {
            brokenImage = new Image(getClass().getClassLoader().getResourceAsStream(redBroken));
            image = new Image(getClass().getClassLoader().getResourceAsStream(red));
        }

        // Fill array of block images
        blockArray[0] = null;
        blockArray[1] = brokenImage;
        blockArray[2] = image;

        block = new ImageView(blockArray[lives]);

        // Set block's x and y position
        block.setX(x);
        block.setY(y);

        // Set variables of blockWidth and blockHeight
        blockWidth = block.getBoundsInLocal().getWidth();
        blockHeight = block.getBoundsInLocal().getHeight();
    }
    // Using the "tell the other guy" technique in my methods so that I can share useful
    // variables from this object in the Main class without making all of the information
    // about it available or able to be changed anywhere in the code

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