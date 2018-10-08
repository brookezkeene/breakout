package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Paddle
 *
 * class that creates a Paddle object
 *
 * @author Brooke Keene
 */
public class Paddle {
    private int origX;
    private int origY;
    private static final String PADDLE_IMAGE = "paddle.gif";
    private ImageView paddle;
    private int paddleSpeed;

    /**
     * Constructor
     *
     * @param x         x-coordinate of paddle position
     * @param height    height of play window
     */
    public Paddle(double x, int height) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        paddle = new ImageView(image);
        paddle.setX(origX);
        paddle.setY(origY);

        paddleSpeed = 20;

        origX = (int) (x - paddle.getBoundsInLocal().getWidth()/2);
        origY = height - (int) paddle.getBoundsInLocal().getHeight();
    }

    /**
     * returns the current ImageView object of the Paddle
     *
     * @return  ImageView of Paddle
     */
    public ImageView getPaddleImage() {
        return paddle;
    }

    /**
     * returns the x position of the upper left corner of the Paddle
     *
     * @return  Paddle x-coordinate
     */
    public double getXPos() {
        return paddle.getX();
    }

    /**
     * increases the x position of the upper left corner of the Paddle
     * by adding the Paddle's speed to the current x position
     */
    public void increaseXPos() {
        paddle.setX(paddle.getX() + paddleSpeed);
    }

    /**
     * decreases the x position of the upper left corner of the Paddle
     * by subtracting the Paddle's speed from the current x position
     */
    public void decreaseXPos() {
        paddle.setX(paddle.getX() - paddleSpeed);
    }

    /**
     * returns the maximum x position of the Paddle
     *
     * @return  Paddle's maximum x-coordinate
     */
    public double getMaxX() {
        return paddle.getBoundsInParent().getMaxX();
    }

    /**
     * returns the current speed of the Paddle
     *
     * @return  Paddle's speed
     */
    public int getSpeed() {
        return paddleSpeed;
    }

    /**
     * changes the speed of the Paddle to the new speed specified
     *
     * @param newSpeed   Paddle's new speed
     */
    public void setSpeed(int newSpeed) {
        paddleSpeed = newSpeed;
    }

    /**
     * returns the width of the Paddle image
     *
     * @return  Paddle width
     */
    public double getWidth() {
        return paddle.getBoundsInLocal().getWidth();
    }

    /**
     * returns the height of the Paddle image
     *
     * @return  Paddle height
     */
    public double getHeight() {
        return paddle.getBoundsInLocal().getHeight();
    }

    /**
     * resets the position of the Paddle for a new game or new level
     */
    public void reset() {
        paddle.setX(origX);
        paddle.setY(origY);
    }
}
