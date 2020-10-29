package megirai.game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import megirai.interfaces.Animation;

/**
 * KeyPressStoppableAnimation implements Animation.
 * Wrap an existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private Boolean stop;
    private Boolean isAlreadyPressed;

    /**
     * Constructor.
     *
     * @param sensor    - gets the keyboard.
     * @param key       - gets the key press.
     * @param animation - gets the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    /**
     *  Check if the key is press.
     *  If press - stop the animation.
     * @param d - gets the DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        if (this.sensor.isPressed(this.key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }

        this.animation.doOneFrame(d);
    }

    @Override
    /**
     * Return true if the animation should stop, else - return false.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return stop;
    }
}
