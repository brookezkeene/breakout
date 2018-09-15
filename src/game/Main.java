package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * Breakout Game
 *
 * @author Brooke Keene (bzk2@duke.edu)
 */
public class Main extends Application {
    public static final String TITLE = "Breakout";
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.BLACK;

    private Scene myScene;
    // Create a top level collection to organize everything in the scene
    private Group root = new Group();
    private Ball myBall;
    private ImageView myBallIV;
    private Paddle myPaddle;
    private ImageView myPaddleIV;
    private ArrayList<Block> myMap;
    private ArrayList<ImageView> myBlocks;
    private ArrayList<Power> myPowers;
    private Text splash = new Text();
    private Text status = new Text();
    private BorderPane border = new BorderPane();
    private HBox hbox = new HBox();


    private boolean play = false; // Whether game is active or not
    private boolean win = false; // Whether player has won game
    private int myScore = 0; // Player's current score, starting at 0
    private int myLives = 3; // Player's current lives, starting at 3
    private int myLevel = 1; // Player's current level, starting at 1
    private int powerNum = 0; // Number of power ups/downs

    // Game Constants
    private int ballSpeed = 150; // Ball's starting speed
    private int numCol = 5;
    private int numRow = 2;
    private int splashXbuf = 25;
    private int splashYbuf = 250;
    private int statusBuf = 30;

    // Game Controls
    private boolean paddleFrozen = false;


    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        // attach scene to stage and display it
        myScene = setupGame(WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        // attach "game loop" to timeline to play it
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Scene setupGame (int width, int height, Paint background) {

        // create a scene to contain all the shapes
        var scene = new Scene(root, width, height, background);

        // create shapes and set their properties and starting positions
        myPaddle = new Paddle(WIDTH/2, HEIGHT);
        myBall = new Ball(WIDTH/2, HEIGHT - (int) myPaddle.getHeight(), 20, ballSpeed);

        myPaddleIV = myPaddle.getPaddleImage();
        myBallIV = myBall.getBallImage();

        myMap = new ArrayList<>();
        myBlocks = new ArrayList<>();
        myPowers = new ArrayList<>();
        MapGenerator(numRow + myLevel,numCol);

        // create splash screen text
        splash.setText("Welcome to Brooke's Breakout game!\n\n" +
                "The rules are simple. Break the ball through all 3 levels of maps to win the game.\n" +
                "Blocks at the top of the map are worth more points, and white blocks contain either\n" +
                "power ups or power downs.\n\n" +
                "Controls:\n" +
                "Right Arrow Key = Move Paddle Right\n" +
                "Left Arrow Key = Move Paddle Left\n" +
                "Enter Key =  Start Level\n\n" +
                "To clear this message press 'C'");
        splash.setX(splashXbuf);
        splash.setY(splashYbuf);
        splash.setFont(Font.font("Trebuchet MS", 20));
        splash.setFill(Color.WHITE);

        // create status display pane and text
        status.setText("Score: " + myScore + "  Lives: " + myLives + " Level: " + myLevel);
        status.setFont(Font.font("Trebuchet MS", 20));
        status.setFill(Color.WHITE);

        //status.setTextAlignment();
        hbox.setSpacing(10); //** Magic Num
        hbox.setStyle("-fx-background-color: null;");
        hbox.getChildren().add(status);
        border.setTop(hbox);

        // add all shapes and text to collection root
        root.getChildren().add(myBallIV);
        root.getChildren().add(myPaddleIV);
        root.getChildren().addAll(myBlocks);
        root.getChildren().add(splash);
        root.getChildren().add(border);

        // respond to keyboard input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

        return scene;
    }

    /**
     * Create a map of blocks for a level
     *
     * @param row - determines the number of rows of blocks
     * @param col - determines the number of columns of blocks
     */
    private void MapGenerator(int row, int col) {
        Block sizeBlock = new Block(1, 1, 1, 0);
        int width = (int) sizeBlock.getWidth();
        int height = (int) sizeBlock.getHeight();
        int buffer = statusBuf;

        for(int i = 0; i < row; i++) {
            for( int j = 0; j < col; j++) {
                int lives = myLevel > 1 ? 2:1;
                // add power up or down if above level 1
                int power;
                if(myLevel >= 2) {
                    power = addPower();
                    if(power <= 7) {
                        Power tempPow = new Power(1, j * width, i * height + buffer, i, power);
                        root.getChildren().add(tempPow.getBlockImage());
                        myPowers.add(tempPow);
                        powerNum++;
                    }
                }
                Block tempBlock = new Block(lives, j*width, i*height + buffer, i);
                myMap.add(tempBlock);
                myBlocks.add(tempBlock.getBlockImage());
            }
        }
    }

    /**
     * Randomly generates an integer to specify the power
     *
     * @return int that specifies Power
     */
    private int addPower() {
        // Randomly generate type
        int type = (int) (Math.random()*((21-1)+1))+1;
        return type;
    }

    /**
     * Game loop
     *
     * @param elapsedTime
     */
    private void step (double elapsedTime) {
        if (play && myLives > 0) {
            if(myBlocks.isEmpty()) {
                play = false;
                levelUp(root);
            }
            collidesWithWall();
            moveBall(elapsedTime);
            if (myBall.getYPos() >= HEIGHT) {
                loseLife();
            }

            collidesWithBlock();
            collidesWithPaddle();
            collidesWithPower();
        }
    }

    /**
     * Move paddle object to the right
     */
    private void movePaddleRight() {
        if(myPaddle.getMaxX() + myPaddle.getSpeed() < WIDTH) {
            myPaddle.setXPos(myPaddle.getXPos() + myPaddle.getSpeed());
        }

    }

    /**
     * Move paddle object to the left
     */
    private void movePaddleLeft() {
        if(myPaddle.getMaxX() - myPaddle.getWidth() > 0) {
            myPaddle.setXPos(myPaddle.getXPos() - myPaddle.getSpeed());
        }
    }

    /**
     * Move ball object by setting new x and y position of the ball
     *
     * @param elapsedTime
     */
    private void moveBall(double elapsedTime) {
        myBallIV.setX(myBallIV.getX() + ballSpeed * myBall.getXDir() * elapsedTime);
        myBallIV.setY(myBallIV.getY() + ballSpeed * myBall.getYDir() * elapsedTime);
    }

    /**
     * Check if the ball collides with the wall
     *
     * @return - true if the ball hits the wall and false otherwise
     */
    private boolean collidesWithWall() {
        if (myBall.getXPos() < 0 || myBall.getXPos() > WIDTH - myBall.getDiam()) {
            myBall.setXDir(-myBall.getXDir());
            return true;
        }
        else if (myBall.getYPos() < 0) {
            myBall.setYDir(-myBall.getYDir());
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Check whether the ball collides with the paddle
     *
     * If it hits the left side of the paddle, make the ball go left
     * If it hits the right side of the paddle, make the ball go right
     */
    private void collidesWithPaddle() {
        double ballMidX = myBall.getXPos() + myBall.getDiam()/2;
        double paddleMaxX = myPaddle.getMaxX();
        double half = myPaddle.getWidth()/2;

        if (!myBallIV.getBoundsInParent().intersects(myPaddleIV.getBoundsInParent())) {
            return;
        }
        else {
            myBall.setYDir(-myBall.getYDir());

            // check where ball hits paddle
            if (ballMidX <= paddleMaxX - half) {
                if(myBall.getXDir() > 0) {
                    myBall.setXDir(-myBall.getXDir());
                }
            }
            else {
                if(myBall.getXDir() < 0) {
                    myBall.setXDir(-myBall.getXDir());
                }
            }
        }

    }

    /**
     * Check whether the ball collides with a block on its top, bottom, left or right
     *
     * If so add to score and decrement the block lives
     */
    private void collidesWithBlock() {
        for (int i = 0; i < myMap.size(); i++) {
            Block myBlock = myMap.get(i);
            if(myBlocks.contains(myBlock.getBlockImage())) {
                double ballMinX = myBall.getXPos();
                double ballMaxX = myBall.getXPos() + myBall.getDiam();
                double ballMinY = myBall.getYPos();
                double ballMaxY = myBall.getYPos() + myBall.getDiam();

                double blockMinX = myBlock.getMinX();
                double blockMaxX = myBlock.getMaxX();
                double blockMinY = myBlock.getMinY();
                double blockMaxY = myBlock.getMaxY();

                // check for hit on the upper edge
                if (myBall.getYDir() > 0 && ballMaxY >= blockMinY && ballMinY <= blockMinY && ((ballMaxX >= blockMinX) && (ballMaxX <= blockMaxX) || (ballMinX >= blockMinX && ballMinX <= blockMaxX))) {
                    myBall.setYDir(-myBall.getYDir());
                    decrementBlock(myBlock);
                    break;
                }
                // check for hit on the lower edge
                if (myBall.getYDir() < 0 && ballMinY <= blockMaxY && ballMaxY >= blockMaxY && ((ballMaxX >= blockMinX) && (ballMaxX <= blockMaxX) || (ballMinX >= blockMinX && ballMinX <= blockMaxX))) {
                    myBall.setYDir(-myBall.getYDir());
                    decrementBlock(myBlock);
                    break;
                }
                // check for hit on the left edge
                if (myBall.getXDir() > 0 && ballMaxX >= blockMinX && ballMinX <= blockMinX && ((ballMaxY >= blockMinY && ballMaxY <= blockMaxY) || (ballMinY >= blockMinY && ballMinY <= blockMaxY))) {
                    myBall.setXDir(-myBall.getXDir());
                    decrementBlock(myBlock);
                    break;
                }
                // check for hit on the right edge
                if (myBall.getXDir() < 0 && ballMinX <= blockMaxX && ballMaxX >= blockMaxX && ((ballMaxY >= blockMinY && ballMaxY <= blockMaxY) || (ballMinY >= blockMinY && ballMinY <= blockMaxY))) {
                    myBall.setXDir(-myBall.getXDir());
                    decrementBlock(myBlock);
                    break;
                }
            }
        }
    }

    /**
     * Check whether the ball collides with a power
     *
     * If so implement appropriate action
     */
    private void collidesWithPower() {
        for (int i = 0; i < myPowers.size(); i++) {
            Power myPow = myPowers.get(i);
            if(!myPow.isHit()) {
                double ballMinX = myBall.getXPos();
                double ballMaxX = myBall.getXPos() + myBall.getDiam();
                double ballMinY = myBall.getYPos();
                double ballMaxY = myBall.getYPos() + myBall.getDiam();

                double powMinX = myPow.getMinX();
                double powMaxX = myPow.getMaxX();
                double powMinY = myPow.getMinY();
                double powMaxY = myPow.getMaxY();

                // check for hit on the upper edge
                if (ballMaxY >= powMinY && ballMinY <= powMinY && ((ballMaxX >= powMinX) && (ballMaxX <= powMaxX) || (ballMinX >= powMinX && ballMinX <= powMaxX))) {
                    myPow.setHitTrue();
                    implementPower(myPow);
                    break;
                }
                // check for hit on the lower edge
                if (ballMinY <= powMaxY && ballMaxY >= powMaxY && ((ballMaxX >= powMinX) && (ballMaxX <= powMaxX) || (ballMinX >= powMinX && ballMinX <= powMaxX))) {
                    myPow.setHitTrue();
                    implementPower(myPow);
                    break;
                }
                // check for hit on the left edge
                if (ballMaxX >= powMinX && ballMinX <= powMinX && ((ballMaxY >= powMinY && ballMaxY <= powMaxY) || (ballMinY >= powMinY && ballMinY <= powMaxY))) {
                    myPow.setHitTrue();
                    implementPower(myPow);
                    break;
                }
                // check for hit on the right edge
                if (ballMinX <= powMaxX && ballMaxX >= powMaxX && ((ballMaxY >= powMinY && ballMaxY <= powMaxY) || (ballMinY >= powMinY && ballMinY <= powMaxY))) {
                    myPow.setHitTrue();
                    implementPower(myPow);
                    break;
                }
            }
        }
    }

    /**
     * Implement power caught
     *
     * @param myPower
     */
    private void implementPower(Power myPower) {
        int type = myPower.getType();
        // add 50 points
        if(type == 1) {
            myScore += 50;
            updateStatus();
        }
        // extra life
        else if(type == 2) {
            myLives++;
            updateStatus();
        }
        // big paddle
        else if(type == 3) {
            System.out.print("hi");
        }
        // small paddle
        else if(type == 4) {
            System.out.print("hi");
        }
        // slow ball
        else if(type == 5) {
            myBall.setSpeed(myBall.getSpeed()-20);
        }
        // fast ball
        else if(type == 6) {
            myBall.setSpeed(myBall.getSpeed()+50);
        }
        // freeze paddle
        else {
          paddleFrozen = true;
        }
        removePower(myPower);
    }

    /**
     * Decrease block lives and check whether the block is still alive
     *
     * @param myBlock
     */
    private void decrementBlock(Block myBlock) {
        // decrement block lives
        int lives = myBlock.getLives();
        lives--;
        // check if block is still alive
        if(lives == 1) {
            removeBlock(myBlock);
            myBlock.setLives(lives);
            myBlocks.add(myBlock.getBlockImage());
        }
        else {
            addToScore(myBlock.getRow());
            removeBlock(myBlock);
        }
    }

    /**
     * Remove block from screen and ArrayList
     *
     * @param myBlock
     */
    private void removeBlock(Block myBlock) {
        myBlock.getBlockImage().setImage(null);
        myBlocks.remove(myBlock.getBlockImage());
    }

    /**
     * Remove power from screen
     *
     * @param myPower
     */
    private void removePower(Power myPower) {
        myPower.getBlockImage().setImage(null);
        root.getChildren().remove(myPower.getBlockImage());
    }

    /**
     * Decrease player lives
     *
     * If player lost, stop game and print message
     */
    private void loseLife () {
        play = false;
        myLives -= 1;
        if(myLives == 0) {
            // print loss message **
            System.out.println("You Lost!");
        }

        myBall.reset();
        myPaddle.reset();

        updateStatus();
    }

    /**
     * Increases player score
     *
     * @param row
     */
    private void addToScore(int row){
        if (row == 0) {
            myScore += 10 * myLevel;
        }
        else if (row == 1) {
            myScore += 5 * myLevel;
        }
        else if (row == 2) {
            myScore += 3 * myLevel;
        }
        else if (row == 3) {
            myScore += 2 * myLevel;
        }
        else {
            myScore += 1 * myLevel;
        }

        updateStatus();
    }

    /**
     * Increase game level
     *
     * Reset position of ball and paddle and reset the map of blocks
     * @param root
     */
    private void levelUp(Group root) {
        myLevel += 1;
        myLives += 1;
        myBall.reset();
        myPaddle.reset();

        myBall.setSpeed(myBall.getSpeed() + 20);
        myPaddle.setSpeed(myPaddle.getSpeed() + 5);

        myMap = new ArrayList<>();
        myBlocks = new ArrayList<>();
        myPowers = new ArrayList<>();
        MapGenerator(numRow + myLevel,numCol);

        // Add shapes to collection
        root.getChildren().addAll(myBlocks);

        updateStatus();
    }

    /**
     * Update the text of the Status Display
     */
    private void updateStatus() {
        status.setText("Score: " + myScore + "  Lives: " + myLives + " Level: " + myLevel);
    }

    /**
     * Resets the entire game to level 1
     */
    private void resetGame() {
        // Reset variables
        play = false;
        win = false;
        myScore = 0;
        myLives = 3;
        myLevel = 1;

        myBall.setSpeed(150);
        myBall.reset();

        myPaddle.setSpeed(10);
        myPaddle.reset();

        // Get rid of old blocks
        for (int i = 0; i < myMap.size(); i++) {
            if(!myBlocks.isEmpty()) {
                Block myBlock = myMap.get(i);
                removeBlock(myBlock);
            }
        }

        myMap = new ArrayList<>();
        myBlocks = new ArrayList<>();
        MapGenerator(numRow + myLevel, numCol);

        // Add shapes to collection
        root.getChildren().addAll(myBlocks);

        updateStatus();
    }

    /**
     * Handle player's key inputs
     * @param code
     */
    private void handleKeyInput (KeyCode code) {
        // clear splash screen
        if(code == KeyCode.C) {
            root.getChildren().remove(splash);
        }

        // begin game or level
        if (code == KeyCode.ENTER) {
            if(play == false) {
                // Start game
                play = true;
                paddleFrozen = false;

                myBall.reset();
                myPaddle.reset();
            }
        }

        // move paddle right
        if (code == KeyCode.RIGHT && !paddleFrozen) {
            movePaddleRight();
        }

        // move paddle left
        if (code == KeyCode.LEFT && !paddleFrozen) {
            movePaddleLeft();
        }

        // Cheat Keys
        // R - Reset **Needs Work
        if (code == KeyCode.R) {
            play = false;

            resetGame();
        }

        // L - Extra Life
        if(code == KeyCode.L) {
            myLives++;
            updateStatus();
        }

        // S - Skip Level
        if(code == KeyCode.S) {
            play = false;

            // get rid of old blocks
            for (int i = 0; i < myMap.size(); i++) {
                if(!myBlocks.isEmpty()) {
                    Block myBlock = myMap.get(i);
                    removeBlock(myBlock);
                }
            }

            // get rid of old powers
            for (int i = 0; i < myPowers.size(); i++) {
                removePower(myPowers.get(i));
            }

            levelUp(root);
        }

        // Space - Pause or Resume Game
        if(code == KeyCode.SPACE) {
            play = !play;
        }
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
