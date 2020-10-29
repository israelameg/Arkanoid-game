package megirai.levels;

import megirai.backgrounds.Bg1;
import megirai.generalbehaiors.Velocity;
import megirai.geometry.Block;
import megirai.interfaces.LevelInformation;
import megirai.interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * DirectHit class implements LevelInformation.
 */
public class DirectHit implements LevelInformation {

    private int numberOfBalls = 1;
    private int puddleSpeed = 7;
    private int paddleWidth = 100;
    private int numberOfBlocks = 1;
    private String levelName = "Direct Hit";


    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        //Velocity velocity = new Velocity(4,-4);
        Velocity velocity = Velocity.fromAngleAndSpeed(180, -4);
        velocities.add(velocity);
        //velocities.add(velocity);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return puddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        Block backGround = new Block(0, 0, 700, 700, Color.BLACK, -1);
        Bg1 bg = new Bg1();
        return bg;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Block block = new Block(350 - 25, 150, 50, 40, Color.RED, 1);
        blockList.add(block);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocks;
    }
}
