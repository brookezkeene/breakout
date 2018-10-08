package power;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * freezePower
 *
 * sub-class of Power, a power down that
 * freezes the player's paddle completely
 *
 * @author Brooke Keene
 */
public class freezePower extends Power {

    /**
     * Constructor
     *
     * @param x x-coordinate of Power
     * @param y y-coordinate of Power
     */
    public freezePower(int x, int y) {
        super(x, y);

        Image image = new Image(getClass().getClassLoader().getResourceAsStream("freezepaddle.gif"));
        power = new ImageView(image);

        power.setX(x);
        power.setY(y);
    }
}
