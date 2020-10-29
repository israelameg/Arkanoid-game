package megirai.interfaces;

import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * The function run the animation.
     *
     * @param d - gets the DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Return true if the animation should stop, else - return false.
     *
     * @return true or false.
     */
    boolean shouldStop();
}
