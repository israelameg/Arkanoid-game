package megirai.interfaces;

import megirai.geometry.Block;

/**
 * BlockCreator interface.
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     *
     * @param xpos - gets the x position.
     * @param ypos - gets the y position.
     * @return a new block at the specified location..
     */
    Block create(int xpos, int ypos);
}
