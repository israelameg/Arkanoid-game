package megirai.game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import megirai.interfaces.Animation;

/**
 * AnimationRunner class.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * Constructor.
     *
     * @param gui             - get the gui.
     * @param framesPerSecond - gets the number of frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
    }

    /**
     * Run the animation.
     *
     * @param animation - gets the animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);
            if (animation.shouldStop()) {
                return;
            }
            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
