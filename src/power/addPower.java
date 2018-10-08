package power;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * addPower
 *
 * sub-class of Power, a power up that adds
 * 50 points to player's total score
 *
 * @author Brooke Keene
 */
public class addPower extends Power {

    /**
     * Constructor
     *
     * @param x x-coordinate of Power
     * @param y y-coordinate of Power
     */
    public addPower(int x, int y) {
        super(x, y);

        Image image = new Image(getClass().getClassLoader().getResourceAsStream("addpoints.gif"));
        power = new ImageView(image);

        power.setX(x);
        power.setY(y);
    }
}
