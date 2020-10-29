package megirai.game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import megirai.interfaces.Animation;
import megirai.generalbehaiors.Counter;
import megirai.generalbehaiors.SpriteCollection;
import megirai.geometry.Ball;
import megirai.geometry.Block;
import megirai.interfaces.Collidable;
import megirai.interfaces.LevelInformation;
import megirai.interfaces.Sprite;

import java.awt.Color;

/**
 * GameLevel class.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper = new Sleeper();
    //counters
    private Counter blockCounter = new Counter();
    private Counter ballCounter = new Counter();
    private Counter score = new Counter();
    private Counter numberOfLives;
    //paddle
    private Paddle paddle;
    //indicators
    private ScoreIndicator scoreIndicator;
    private LivesIndicator livesIndicator;

    //new organization
    private AnimationRunner runner;
    private boolean running;

    private KeyboardSensor keyboard;
    private LevelInformation level;

    /**
     * Constructor.
     *
     * @param level - gets the level.
     * @param ks    - gets the keyboard.
     * @param ar    - gets the animation.
     * @param lives - gets the lives counter.
     * @param score - gets the score counter.
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar, Counter lives, Counter score) {
        this.level = level;
        this.keyboard = ks;
        this.runner = ar;
        this.running = true;
        this.numberOfLives = lives;
        this.score = score;
    }

    /**
     * Add the collidables to the collidable list.
     *
     * @param c - a collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add the sprite to the sprite list.
     *
     * @param s - a sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /*** Initialize a new game: create the Blocks and megirai.geometry.Ball (and megirai.game.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        environment = new GameEnvironment();
        sprites = new SpriteCollection();

        //set back ground
        Sprite backGround = level.getBackground();
        ((Sprite) backGround).addToGame(this);

        //set paddle
        paddle = new Paddle(400 - level.paddleWidth() / 2, 550, level.paddleWidth(), 20, keyboard, level.paddleSpeed());
        paddle.addToGame(this);

        //borders
        /*Collidable rightBorder = new Block(680, 0, 700, 700, Color.gray, -1);
        Collidable leftBorder = new Block(-700, 0, 720, 700, Color.gray, -1);
        Collidable upBorder = new Block(0, -700, 700, 740, Color.gray, -1);
        Collidable downBorder = new Block(0, 610, 700, 50, Color.gray, -1);
        ((Block) rightBorder).addToGame(this);
        ((Block) leftBorder).addToGame(this);
        ((Block) upBorder).addToGame(this);
        ((Block) downBorder).addToGame(this);*/

        Collidable rightBorder = new Block(780, 0, 800, 700, Color.gray, -1);
        Collidable leftBorder = new Block(-800, 0, 820, 700, Color.gray, -1);
        Collidable upBorder = new Block(0, -700, 800, 740, Color.gray, -1);
        Collidable downBorder = new Block(0, 610, 800, 50, Color.gray, -1);
        ((Block) rightBorder).addToGame(this);
        ((Block) leftBorder).addToGame(this);
        ((Block) upBorder).addToGame(this);
        ((Block) downBorder).addToGame(this);

        // Register the BallRemover as a listener of the death-region block
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        ((Block) downBorder).addHitListener(ballRemover);


        //set blocks
        for (Block block : level.blocks()) {

            blockCounter.increase(1);
            block.addToGame(this);

            // ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);
            ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);
            block.addHitListener(scoreListener);

            //lives
            Counter blockLives = new Counter();
            blockLives.increase(block.getLives());

            //block remover
            BlockRemover blockRemove = new BlockRemover(this, blockLives);
            block.addHitListener(blockRemove);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {

        //create balls
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 450, 5, Color.white, environment);
            ball.setVelocity((level.initialBallVelocities().get(i)).getDx(),
                    (level.initialBallVelocities().get(i)).getDy());
            ball.addToGame(this);
            ballCounter.increase(1);
        }

        //put the paddle in the center
        paddle.getRect().getUpperLeft().setX(400 - level.paddleWidth() / 2);

        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

    }

    /**
     * Remove the colllidable.
     *
     * @param c - a collidable.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove the sprite.
     *
     * @param s - a sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Return the block's counter.
     *
     * @return the block's counter.
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    /**
     * Return the ball's counter.
     *
     * @return the ball's counter.
     */
    public Counter getBallCounter() {
        return this.ballCounter;
    }

    /**
     * run the game - call to the function playOneTurn while there are more lives left,
     * else end the game.
     */
    public void run() {
        while (numberOfLives.getValue() != 0) {
            playOneTurn();
            numberOfLives.decrease(1);
        }
        //gui.close();
        return;
    }

    /**
     * Return true if the animation should stop, else - return false.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * The function run the animation(the current level).
     *
     * @param d - gets the DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous playOneTurn method goes here.

        // game-specific logic
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        //stopping condition
        // if all the blocks are removed
        if (this.blockCounter.getValue() == 0) {
            //add 100 points when all the blocks are removed
            score.increase(100);
            this.running = false;
        }

        // if all the balls are removed
        if (this.ballCounter.getValue() == 0) {
            this.numberOfLives.decrease(1);
            this.running = false;
        }

        //pause screen
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen(this.keyboard)));
        }

    }

}
