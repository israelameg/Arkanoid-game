package megirai.levels;

import megirai.backgrounds.Bg4;
import megirai.generalbehaiors.Velocity;
import megirai.geometry.Block;
import megirai.interfaces.LevelInformation;
import megirai.interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * FinalFour class implements LevelInformation.
 */
public class FinalFour implements LevelInformation {
    private int numberOfBalls = 3;
    private int puddleSpeed = 6;
    private int paddleWidth = 100;
    private int numberOfBlocks = 105;
    private String levelName = "Final Four";


    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity velocity1 = new Velocity(4, -4);
        Velocity velocity2 = new Velocity(3, 2);
        Velocity velocity3 = new Velocity(5, -5);
        velocities.add(velocity1);
        velocities.add(velocity2);
        velocities.add(velocity3);
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
        Bg4 bg = new Bg4();

        return bg;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int rows = 7;
        int numBlocks = 15;
        int upperX = 680 - 50;
        int upperY = 100;
        Color color = Color.black;

        for (int i = 0; i < rows; i++) {
            upperX = 680 - 44;
            for (int j = 0; j < numBlocks; j++) {
                Block block;
                switch (i) {
                    case 0:
                        color = Color.gray;
                        break;

                    case 1:
                        color = Color.red;
                        break;

                    case 2:
                        color = Color.yellow;
                        break;

                    case 3:
                        color = Color.green;
                        break;

                    case 4:
                        color = Color.white;
                        break;

                    case 5:
                        color = Color.pink;
                        break;

                    case 6:
                        color = Color.cyan;
                        break;
                    default:
                        break;

                }

                block = new Block(upperX, upperY, 44, 30, color, 1);
                blockList.add(block);

                upperX = upperX - 44;

            }
            upperY = upperY + 30;
        }

        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocks;
    }
}
