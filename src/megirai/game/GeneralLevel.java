package megirai.game;

import megirai.generalbehaiors.Velocity;
import megirai.geometry.Block;
import megirai.interfaces.LevelInformation;
import megirai.interfaces.Sprite;

import java.util.List;

/**
 * GeneralLevel class implements LevelInformation.
 */
public class GeneralLevel implements LevelInformation {

    private String levelName;
    private List<Velocity> velocities;
    private int paddleWidth;
    private int paddleSpeed;
    private int numBlocks;
    private List<Block> blockList;
    private Sprite bg;

    /**
     * Constructor.
     *
     * @param levelName   - gets the level name.
     * @param velocities  - gets a list of ball velocity.
     * @param paddleSpeed - gets the paddle speed.
     * @param paddleWidth - gets the paddle width.
     * @param blocks      - gets the number of blocks.
     * @param blockList   - gets a blocks list.
     * @param bg          - gets the back ground.
     */
    public GeneralLevel(String levelName, List<Velocity> velocities, int paddleSpeed,
                        int paddleWidth, int blocks, List<Block> blockList, Sprite bg) {
        this.levelName = levelName;
        this.velocities = velocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.numBlocks = blocks;
        this.blockList = blockList;
        this.bg = bg;
    }

    @Override
    public int numberOfBalls() {
        return velocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return bg;
    }

    @Override
    public List<Block> blocks() {
        System.out.println("size block list = " + blockList.size());
        for (int i = 0; i < blockList.size(); i++) {
            System.out.println("block - " + blockList.get(i));
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        System.out.println("num Blocks" + numBlocks);
        return numBlocks;
    }
}
