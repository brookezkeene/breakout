package power;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * slowPower
 *
 * sub-class of Power, a power up that makes
 * the ball's speed decrease
 *
 * @author Brooke Keene
 */
public class slowPower extends Power {

    /**
     * Constructor
     *
     * @param x x-coordinate of Power
     * @param y y-coordinate of Power
     */
    public slowPower(int x, int y) {
        super(x, y);

        Image image = new Image(getClass().getClassLoader().getResourceAsStream("slowball.gif"));
        power = new ImageView(image);

        power.setX(x);
        power.setY(y);
    }
}
