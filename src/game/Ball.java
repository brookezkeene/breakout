package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Ball
 *
 * class that creates a Ball object
 *
 * @author Brooke Keene
 */
public class Ball {
    private int origX;
    private int origY;
    private int ballDiam;
    private double ballXDir;
    private double ballYDir;
    private int ballSpeed;

    private static final String BALL_IMAGE = "ball.gif";
    private ImageView ball;

    /**
     * Constructor
     *
     * @param x         x-coordinate of ball position
     * @param y         y-coordinate of ball position
     * @param diameter  ball diameter
     * @param speed     ball speed
     */
    public Ball(int x, int y, int diameter, int speed) {
        origX = x - diameter/2;
        origY = y - diameter;
        ballDiam = diameter;
        ballXDir = -1;
        ballYDir = -2;
        ballSpeed = speed;

        Image image = new Image(getClass().getClassLoader().getResourceAsStream(BALL_IMAGE));
        ball = new ImageView(image);

        ball.setX(origX);
        ball.setY(origY);
    }

    /**
     * returns the current ImageView object of the Ball
     *
     * @return  ImageView of Ball
     */
    public ImageView getBallImage() {
        return ball;
    }

    /**
     * returns the x position of the upper left corner of the Ball
     *
     * @return  Ball x-coordinate
     */
    public double getXPos() {
        return ball.getX();
    }

    /**
     * returns the y position of the upper left corner of the Ball
     *
     * @return  Ball y-coordinate
     */
    public double getYPos() {
        return ball.getY();
    }

    /**
     * returns the x direction of the Ball's current motion
     *
     * @return  Ball's x direction
     */
    public double getXDir() {
        return ballXDir;
    }

    /**
     * returns the y direction of the Ball's current motion
     *
     * @return  Ball's y direction
     */
    public double getYDir() {
        return ballYDir;
    }

    /**
     * changes the x direction of the Ball's motion by flipping the sign
     */
    public void changeXDir() {
        ballXDir = -ballXDir;
    }

    /**
     * changes the y direction of the Ball's motion by flipping the sign
     */
    public void changeYDir() {
        ballYDir = -ballYDir;
    }

    /**
     * returns the current speed of the Ball
     *
     * @return  Ball's speed
     */
    public int getSpeed() {
        return ballSpeed;
    }

    /**
     * changes the speed of the Ball to the new speed specified
     *
     * @param newSpeed  Ball's new speed
     */
    public void setSpeed(int newSpeed) {
        ballSpeed = newSpeed;
    }

    /**
     * returns the diameter of the Ball image
     *
     * @return  Ball diameter
     */
    public int getDiam() {
        return ballDiam;
    }

    /**
     * resets the position of the Ball for a new game or new level
     */
    public void reset() {
        ball.setX(origX);
        ball.setY(origY);
        ballXDir = -1;
        ballYDir = -2;
    }
}
