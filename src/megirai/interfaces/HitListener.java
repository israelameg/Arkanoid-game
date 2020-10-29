package megirai.interfaces;

import megirai.geometry.Ball;
import megirai.geometry.Block;

/**
 *  HitListener interface.
 * (Objects that want to be notified of hit events, should implement the megirai.interfaces.HitListener interface).
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the megirai.geometry.Ball that's doing the hitting.
     *
     * @param beingHit - the hit block.
     * @param hitter   - the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
