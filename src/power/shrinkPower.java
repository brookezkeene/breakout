package power;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * shrinkPower
 *
 * sub-class of Power, a power down that decreases
 * the size of the player's paddle
 *
 * @author Brooke Keene
 */
public class shrinkPower extends Power {

    /**
     * Constructor
     *
     * @param x x-coordinate of Power
     * @param y y-coordinate of Power
     */
    public shrinkPower(int x, int y) {
        super(x, y);

        Image image = new Image(getClass().getClassLoader().getResourceAsStream("shrinkpaddle.gif"));
        power = new ImageView(image);

        power.setX(x);
        power.setY(y);
    }
}
