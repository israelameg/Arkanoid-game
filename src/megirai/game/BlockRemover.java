package megirai.game;

import megirai.generalbehaiors.Counter;
import megirai.geometry.Ball;
import megirai.geometry.Block;
import megirai.interfaces.HitListener;

/**
 * BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game          - the current game.
     * @param removedBlocks - counter of removed blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit - the hit block.
     * @param hitter   - the Ball that's doing the hitting.
     */

    public void hitEvent(Block beingHit, Ball hitter) {
        if (this.remainingBlocks.getValue() == 1) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
        }
        this.remainingBlocks.decrease(1);
    }
}