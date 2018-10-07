package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Using inheritance as was discussed in class because I realized I was using the two
// classes, Block and Power, very similarly in my game
public class Power extends Block {
    private int powerType; // associates a stored int with a power
    private boolean hit;   // boolean that signals whether the power has been hit or not

    // Different images for different power ups or downs
    private static final String ADD = "addpoints.gif";
    private static final String LIFE = "extralife.gif";
    private static final String GROW = "growpaddle.gif";
    private static final String SHRINK = "shrinkpaddle.gif";
    private static final String SLOW = "slowball.gif";
    private static final String FAST = "fastball.gif";
    private static final String FREEZE = "freezepaddle.gif";

    public Power(int num, int x, int y, int row, int type) {
        super(num, x, y, row);
        String powerImage;
        // determines the image associated with the power
        if(type == 1) {
            powerImage = ADD;
        }
        else if(type == 2) {
            powerImage = LIFE;
        }
        else if(type == 3) {
            powerImage = GROW;
        }
        else if(type == 4) {
            powerImage = SHRINK;
        }
        else if(type == 5) {
            powerImage = SLOW;
        }
        else if(type == 6) {
            powerImage = FAST;
        }
        else {
            powerImage = FREEZE;
        }

        Image image = new Image(getClass().getClassLoader().getResourceAsStream(powerImage));
        block = new ImageView(image);

        block.setX(x);
        block.setY(y);

        powerType = type;
        hit = false;
    }

    // Returns Power Type in the Form of an Integer
    public int getType() {
        return powerType;
    }

    // Sets Power's Hit Parameter
    public void setHitTrue() {
        hit = true;
    }

    // Return Whether Power is Hit
    public boolean isHit() {
        return hit;
    }
}