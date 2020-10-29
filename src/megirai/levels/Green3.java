package megirai.levels;

import megirai.backgrounds.Bg3;
import megirai.generalbehaiors.Velocity;
import megirai.geometry.Block;
import megirai.interfaces.LevelInformation;
import megirai.interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Green3 class implements LevelInformation.
 */
public class Green3 implements LevelInformation {
    private int numberOfBalls = 2;
    private int puddleSpeed = 7;
    private int paddleWidth = 100;
    private int numberOfBlocks = 40;
    private String levelName = "Green 3";


    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity velocity1 = new Velocity(4, -4);
        Velocity velocity2 = new Velocity(7, -4);
        velocities.add(velocity1);
        velocities.add(velocity2);
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
        Bg3 bg = new Bg3();
        return bg;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int rows = 5;
        int numBlocks = 10;
        int upperX = 680 - 50;
        int upperY = 150;

        for (int i = 0; i < rows; i++) {
            upperX = 680 - 50;
            for (int j = 0; j < numBlocks; j++) {
                Block block;
                if (numBlocks == 10) {
                    block = new Block(upperX, upperY, 50, 30, Color.gray, 1);
                    blockList.add(block);
                }
                if (numBlocks == 9) {
                    block = new Block(upperX, upperY, 50, 30, Color.red, 1);
                    blockList.add(block);
                }
                if (numBlocks == 8) {
                    block = new Block(upperX, upperY, 50, 30, Color.yellow, 1);
                    blockList.add(block);
                }
                if (numBlocks == 7) {
                    block = new Block(upperX, upperY, 50, 30, Color.blue, 1);
                    blockList.add(block);
                }
                if (numBlocks == 6) {
                    block = new Block(upperX, upperY, 50, 30, Color.white, 1);
                    blockList.add(block);
                }
                upperX = upperX - 50;

            }
            upperY = upperY + 30;
            numBlocks--;
        }

        return blockList;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocks;
    }
}
