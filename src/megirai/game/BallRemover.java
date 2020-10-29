package megirai.game;

import megirai.generalbehaiors.Counter;
import megirai.geometry.Ball;
import megirai.geometry.Block;
import megirai.interfaces.HitListener;

/**
 * BallRemover class implements HitListener.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter removedBalls;

    /**
     * Constructor.
     *
     * @param game - the current game.
     * @param removedBalls - counter of removed balls.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.removedBalls = removedBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * Remove the ball that hits the block from the game.
     *
     * @param beingHit - the hit block.
     * @param hitter   - the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
    }
}
