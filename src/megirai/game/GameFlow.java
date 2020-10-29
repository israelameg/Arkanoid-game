package megirai.game;

import biuoop.KeyboardSensor;
import megirai.generalbehaiors.Counter;
import megirai.interfaces.LevelInformation;

import java.util.List;

/**
 * GameFlow.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter numberOfLives;
    private LivesIndicator livesIndicator;
    private Counter score = new Counter();
    private ScoreIndicator scoreIndicator;
    private final int lives;
    private LevelNameIndicator levelNameIndicator;
    private boolean isWin = true;

    /**
     * Constructor.
     *
     * @param ar - gets the animation.
     * @param ks - gets the keyboard.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        //GUI gui = new GUI("Arkanoid", 700, 600);
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        //this.animationRunner = new AnimationRunner(gui, 60);
        this.lives = 7;
    }

    /**
     * Run over the levels list and call to the functions -
     * initialize and playOneTurn for each level.
     *
     * @param levels - gets the list of the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        // lives counter
        numberOfLives = new Counter();
        numberOfLives.increase(lives);
        livesIndicator = new LivesIndicator(numberOfLives);


        //score counter
        scoreIndicator = new ScoreIndicator(score);

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.numberOfLives, this.score);

            level.initialize();

            scoreIndicator.addToGame(level);
            livesIndicator.addToGame(level);
            levelNameIndicator = new LevelNameIndicator(levelInfo.levelName());
            levelNameIndicator.addToGame(level);

            while (this.numberOfLives.getValue() > 0 && level.getBlockCounter().getValue() > 0) {
                level.playOneTurn();
            }

            if (this.numberOfLives.getValue() == 0) {
                isWin = false;
                break;
            }

        }

        //end screen
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                "space", new EndScreen(this.keyboardSensor, score, isWin)));

    }

    /**
     * Return the score.
     *
     * @return the score.
     */
    public Counter getScore() {
        return this.score;
    }
}
