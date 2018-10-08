package power;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * fastPower
 *
 * sub-class of Power, a power down that makes
 * the ball's speed increase
 *
 * @author Brooke Keene
 */
public class fastPower extends Power {

    /**
     * Constructor
     *
     * @param x x-coordinate of Power
     * @param y y-coordinate of Power
     */
    public fastPower(int x, int y) {
        super(x, y);

        Image image = new Image(getClass().getClassLoader().getResourceAsStream("fastball.gif"));
        power = new ImageView(image);

        power.setX(x);
        power.setY(y);
    }
}
