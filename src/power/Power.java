package power;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Power
 *
 * abstract class for Power up/down objects
 * where all methods are defined
 *
 * @author Brooke Keene
 */
public abstract class Power {
    private boolean hit;   // boolean that signals whether the power has been hit or not
    protected double powerWidth;
    protected double powerHeight;
    protected ImageView power;


    /**
     * Constructor
     */
    public Power(int x, int y) {
        hit = false;
    }

    /**
     * returns the current ImageView object of the Power
     *
     * @return  ImageView of Power
     */
    public ImageView getPowerImage() {
        return power;
    }

    /**
     * returns the minimum x position of the Power
     *
     * @return  Power's minimum x-coordinate
     */
    public double getMinX() {
        return power.getBoundsInParent().getMinX();
    }

    /**
     * returns the minimum y position of the Power
     *
     * @return  Power's minimum y-coordinate
     */
    public double getMinY() {
        return power.getBoundsInParent().getMinY();
    }

    /**
     * returns the maximum x position of the Power
     *
     * @return  Power's maximum x-coordinate
     */
    public double getMaxX() {
        return power.getBoundsInParent().getMaxX();
    }

    /**
     * returns the maximum y position of the Power
     *
     * @return  Power's maximum y-coordinate
     */
    public double getMaxY() {
        return power.getBoundsInParent().getMaxY();
    }

    /**
     * returns width of the Power image
     *
     * @return  Power width
     */
    public double getWidth() {
        return powerWidth;
    }

    /**
     * returns height of the Power image
     *
     * @return  Power height
     */
    public double getHeight() {
        return powerHeight;
    }

    /**
     * sets Power's hit indicator to True
     */
    public void setHitTrue() {
        hit = true;
    }

    /**
     * returns a boolean indicating whether the Power
     * has been hit or not
     *
     * @return if Power is hit
     */
    public boolean isHit() {
        return hit;
    }
}