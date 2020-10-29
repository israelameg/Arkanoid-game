package megirai.levels;

import megirai.backgrounds.Bg2;
import megirai.generalbehaiors.Velocity;
import megirai.geometry.Block;
import megirai.interfaces.LevelInformation;
import megirai.interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * WideEasy class implements LevelInformation.
 */
public class WideEasy implements LevelInformation {
    private int numberOfBalls = 10;
    private int puddleSpeed = 4;
    private int paddleWidth = 350;
    private int numberOfBlocks = 15;
    private String levelName = "Wide Easy";


    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int v = 105;
        for (int i = 0; i < numberOfBalls; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(v, -4);
            velocities.add(velocity);
            if (v == 165) {
                v = v + 30;
            } else {
                v = v + 15;
            }
        }
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
        Bg2 bg = new Bg2();
        return bg;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Block block1 = new Block(636, 250, 44, 40, Color.cyan, 1);
        blockList.add(block1);
        Block block2 = new Block(592, 250, 44, 40, Color.cyan, 1);
        blockList.add(block2);
        Block block3 = new Block(548, 250, 44, 40, Color.pink, 1);
        blockList.add(block3);
        Block block4 = new Block(504, 250, 44, 40, Color.pink, 1);
        blockList.add(block4);
        Block block5 = new Block(460, 250, 44, 40, Color.blue, 1);
        blockList.add(block5);
        Block block6 = new Block(416, 250, 44, 40, Color.blue, 1);
        blockList.add(block6);
        Block block7 = new Block(372, 250, 44, 40, Color.green, 1);
        blockList.add(block7);
        Block block8 = new Block(328, 250, 44, 40, Color.green, 1);
        blockList.add(block8);
        Block block9 = new Block(284, 250, 44, 40, Color.green, 1);
        blockList.add(block9);
        Block block10 = new Block(240, 250, 44, 40, Color.yellow, 1);
        blockList.add(block10);
        Block block11 = new Block(196, 250, 44, 40, Color.yellow, 1);
        blockList.add(block11);
        Block block12 = new Block(152, 250, 44, 40, Color.orange, 1);
        blockList.add(block12);
        Block block13 = new Block(108, 250, 44, 40, Color.orange, 1);
        blockList.add(block13);
        Block block14 = new Block(64, 250, 44, 40, Color.red, 1);
        blockList.add(block14);
        Block block15 = new Block(20, 250, 44, 40, Color.red, 1);
        blockList.add(block15);


        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocks;
    }
}
