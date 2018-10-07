package block;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * sub-class of Block, a yellow Block
 *
 * @author Brooke Keene
 */
public class yellowBlock extends Block {
    private static final String yellow = "brick4.gif";
    private static final String yellowBroken = "brick4broken.gif";

    /**
     * Constructor
     *
     * @param num   number of lives block has
     * @param x     x-coordinate of block position
     * @param y     y-coordinate of block position
     * @param row   block row in game map
     */
    public yellowBlock(int num, int x, int y, int row) {
        super(num, x, y, row);

        Image brokenImage = new Image(getClass().getClassLoader().getResourceAsStream(yellowBroken));
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(yellow));

        // Fill array of block images
        blockArray[0] = null;
        blockArray[1] = brokenImage;
        blockArray[2] = image;

        block = new ImageView(blockArray[lives]);

        block.setX(x);
        block.setY(y);

        blockWidth = block.getBoundsInLocal().getWidth();
        blockHeight = block.getBoundsInLocal().getHeight();
    }
}
