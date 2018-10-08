package power;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * growPower
 *
 * sub-class of Power, a power up that increases
 * the size of the player's paddle
 *
 * @author Brooke Keene
 */
public class growPower extends Power {

    /**
     * Constructor
     *
     * @param x x-coordinate of Power
     * @param y y-coordinate of Power
     */
    public growPower(int x, int y) {
        super(x, y);

        Image image = new Image(getClass().getClassLoader().getResourceAsStream("growpaddle.gif"));
        power = new ImageView(image);

        power.setX(x);
        power.setY(y);
    }
}
