package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Paddle {
    private int origX;
    private int origY;
    private static final String PADDLE_IMAGE = "paddle.gif";
    private ImageView paddle;
    private int paddleSpeed;

    /**
     * Constructor
     *
     * @param x - x-coordinate of paddle position
     * @param height - height of play window
     */
    public Paddle(double x, int height) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        paddle = new ImageView(image);
        origX = (int) (x - paddle.getBoundsInLocal().getWidth()/2);
        origY = height - (int) paddle.getBoundsInLocal().getHeight();
        paddle.setX(origX);
        paddle.setY(origY);
        paddleSpeed = 20;
    }

    // Returns ImageView Paddle Object
    public ImageView getPaddleImage() {
        return paddle;
    }

    // Changes Paddle's Position to newPos
    public void setXPos(double newPos) {
        paddle.setX(newPos);
    }

    // Returns Paddle's X Position
    public double getXPos() {
        return paddle.getX();
    }

    // Returns Paddle's Maximum X Position
    public double getMaxX() {
        return paddle.getBoundsInParent().getMaxX();
    }

    // Returns Paddle's Speed
    public int getSpeed() {
        return paddleSpeed;
    }

    // Changes Ball's Speed to an int newSpeed
    public void setSpeed(int newSpeed) {
        paddleSpeed = newSpeed;
    }

    // Returns Paddle's Width
    public double getWidth() {
        return paddle.getBoundsInLocal().getWidth();
    }

    // Returns Paddle's Height
    public double getHeight() {
        return paddle.getBoundsInLocal().getHeight();
    }

    // Resets Paddle Position for New Game or Level
    public void reset() {
        paddle.setX(origX);
        paddle.setY(origY);
    }
}
