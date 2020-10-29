package megirai.game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import megirai.interfaces.Animation;

/**
 * PauseScreen Class implements Animation.
 */
public class PauseScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     * @param k - gets the keyboard.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    /**
     * The function run the animation.
     * Display a screen with the message paused -- press space to continue
     * until a key is pressed.
     *
     * @param d - gets the DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    /**
     * Return true if the animation should stop, else - return false.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
