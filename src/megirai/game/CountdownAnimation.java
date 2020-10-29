package megirai.game;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import megirai.generalbehaiors.SpriteCollection;
import megirai.interfaces.Animation;

import java.awt.Color;

/**
 * CountdownAnimation implements Animation.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScrren;
    private boolean stop;
    private Sleeper sleeper = new Sleeper();
    private int currentNum;
    private boolean isFirstFrame = true;

    /**
     * Constructor.
     *
     * @param numOfSeconds - gets the number of second of the animation.
     * @param countFrom    - gets the number from which the count down begin.
     * @param gameScreen   - gets the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScrren = gameScreen;
        this.stop = false;
        this.currentNum = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        //end of count
        if (currentNum == 0) {
            this.stop = true;
        }
        gameScrren.drawAllOn(d);
        if (isFirstFrame) {
            isFirstFrame = false;
            return;
        }
        d.setColor(Color.lightGray);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, "" + currentNum, 70);
        currentNum--;
        sleeper.sleepFor((long) ((numOfSeconds / countFrom) * 1000));
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
