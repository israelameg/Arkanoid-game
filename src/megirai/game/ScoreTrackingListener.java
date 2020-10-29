package megirai.game;

import megirai.generalbehaiors.Counter;
import megirai.geometry.Ball;
import megirai.geometry.Block;
import megirai.interfaces.HitListener;

/**
 * ScoreTrackingListener class implement megirai.interfaces.HitListener.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter - a score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * Increase the score by 5 or 15 points.
     *
     * @param beingHit - the hit block.
     * @param hitter   - the megirai.geometry.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // hitting a block is worth 5 points
        currentScore.increase(5);
        if (beingHit.getLives() == 0) {
            //destroying a block is worth and additional 10 points
            currentScore.increase(10);
        }
    }
}
