package power;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * lifePower
 *
 * sub-class of Power, a power up that adds
 * an extra life to player's total lives
 *
 * @author Brooke Keene
 */
public class lifePower extends Power {

    /**
     * Constructor
     *
     * @param x x-coordinate of Power
     * @param y y-coordinate of Power
     */
    public lifePower(int x, int y) {
        super(x, y);

        Image image = new Image(getClass().getClassLoader().getResourceAsStream("extralife.gif"));
        power = new ImageView(image);

        power.setX(x);
        power.setY(y);
    }
}
