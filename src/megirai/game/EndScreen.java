package megirai.game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import megirai.generalbehaiors.Counter;
import megirai.interfaces.Animation;

/**
 * EndScreen Class implements Animation.
 * Once the game is over,we will display the final score.
 */
public class EndScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private Boolean isWin;

    /**
     * Constructor.
     *
     * @param k     - gets the keyboard.
     * @param score - gets the score counter.
     * @param win   - gets true if the player win, else gets false.
     */
    public EndScreen(KeyboardSensor k, Counter score, Boolean win) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.isWin = win;
    }

    @Override
    /**
     * The function run the animation.
     * Display a message if the player win or lose and shows the score.
     *
     * @param d - gets the DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        if (isWin) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), 32);
        }
    }

    @Override
    /**
     * Return true if the animation should stop, else - return false.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return false;
    }
}
