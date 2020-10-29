package megirai.interfaces;

import megirai.generalbehaiors.Velocity;
import megirai.geometry.Block;

import java.util.List;

/**
 * LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * Return the number of balls.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * Return a list of the initial velocity of each ball.
     *
     * @return list of velocity.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Return the paddle's speed.
     *
     * @return the paddle's speed.
     */
    int paddleSpeed();

    /**
     * Return the paddle's width.
     *
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * Return the level name.
     *
     * @return the level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * Returns a list of the blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return a list of the blocks that make up this level.
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed.
     *
     * @return the number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
