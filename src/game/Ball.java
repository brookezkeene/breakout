package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball {
    private int origX;
    private int origY;
    private int ballDiam;
    private double ballXDir;
    private double ballYDir;
    private static final String BALL_IMAGE = "ball.gif";
    private ImageView ball;
    private int ballSpeed;

    /**
     * Constructor
     *
     * @param x - x-coordinate of ball position
     * @param y - y-coordinate of ball position
     * @param diameter - ball diameter
     * @param speed - ball speed
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

    // Returns ImageView Ball Object
    public ImageView getBallImage() {
        return ball;
    }

    // Returns X Position of Ball
    public double getXPos() {
        return ball.getX();
    }

    // Returns Y Position of Ball
    public double getYPos() {
        return ball.getY();
    }

    // Returns X Direction of Ball's Motion
    public double getXDir() {
        return ballXDir;
    }

    // Returns Y Direction of Ball's Motion
    public double getYDir() {
        return ballYDir;
    }

    // Changes X Direction of Ball's Motion to a double newDir
    public void setXDir(double newDir) {
        ballXDir = newDir;
    }

    // Changes Y Direction of Ball's Motion to a double newDir
    public void setYDir(double newDir) {
        ballYDir = newDir;
    }

    // Returns Ball's Speed
    public int getSpeed() {
        return ballSpeed;
    }

    // Changes Ball's Speed to an int newSpeed
    public void setSpeed(int newSpeed) {
        ballSpeed = newSpeed;
    }

    // Returns Ball's Diameter
    public int getDiam() {
        return ballDiam;
    }

    // Resets Ball's Position for New Game or Level
    public void reset() {
        ball.setX(origX);
        ball.setY(origY);
        ballXDir = -1;
        ballYDir = -2;
    }
}
