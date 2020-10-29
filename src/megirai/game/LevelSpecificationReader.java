package megirai.game;

import megirai.generalbehaiors.Velocity;
import megirai.geometry.Block;
import megirai.interfaces.LevelInformation;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LevelSpecificationReader.
 */
public class LevelSpecificationReader {

    private String levelName;
    private List<Velocity> velocities = new ArrayList<>();
    private int blocksStartX;
    private int blocksStartY;
    private List<LevelInformation> levelInformations = new LinkedList<>();
    private int paddleSpeed;
    private int paddleWidth;
    private String blockDefinitions;
    private int rowHeight;
    private int numBlocks;
    private List<Block> blockList = new LinkedList<>();
    private BackGround bg;

    /**
     * Reads the level file.
     *
     * @param reader - get the file.
     * @return a list of levels.
     * @throws IOException - if the loading failed.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            if (line.equals("START_LEVEL")) {
                line = bufferedReader.readLine();
                while (!(line).equals("END_LEVEL")) {
                    loadLevel(line);
                    if (line.equals("START_BLOCKS")) {
                        line = bufferedReader.readLine();
                        while (!(line).equals("END_BLOCKS")) {
                            loadBlocks(line);
                            line = bufferedReader.readLine();
                        }
                    }
                    line = bufferedReader.readLine();
                }
                GeneralLevel level = new GeneralLevel(levelName, velocities, paddleSpeed,
                        paddleWidth, numBlocks, blockList, bg);
                levelInformations.add(level);

            }
            blockList = new LinkedList<>();
            line = bufferedReader.readLine();
        }

        return levelInformations;
    }

    /**
     * Reads a line from the file that include block's information.
     *
     * @param line - a line from the file.
     */
    public void loadBlocks(String line) {
        try {
           /* BlocksFromSymbolsFactory b = BlocksDefinitionReader.fromReader(
                    new FileReader(new File("resources/" + blockDefinitions)));*/
            BlocksFromSymbolsFactory b = BlocksDefinitionReader.fromReader(
                    new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(blockDefinitions)));
            int blockX = blocksStartX;
            for (int i = 0; i < line.length(); i++) {
                if (b.isBlockSymbol(line.substring(i, i + 1))) {
                    blockList.add(b.getBlock(line.substring(i, i + 1), blockX, blocksStartY));
                    blockX = blockX + (int) b.getBlock(line.substring(i, i + 1), blockX, blocksStartY)
                            .getCollisionRectangle().getWidth();
                } else if (b.isSpaceSymbol(line.substring(i, i + 1))) {
                    blockX = blockX + b.getSpaceWidth(line.substring(i, i + 1));
                }
            }
            blocksStartY = blocksStartY + rowHeight;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read a line from file that include level's information.
     *
     * @param line - a line from the file.
     */
    public void loadLevel(String line) {
        String fieldName = line.split(":")[0];
        if (fieldName.equals("level_name")) {
            levelName = line.split(":")[1];
        } else if (fieldName.equals("ball_velocities")) {
            velocities = new LinkedList<>();
            String velocityString = line.split(":")[1];
            for (String s1 : velocityString.split(" ")) {
                Velocity v = Velocity.fromAngleAndSpeed(Double.parseDouble(s1.split(",")[0]),
                        Double.parseDouble(s1.split(",")[1]));
                velocities.add(v);
            }
        } else if (fieldName.equals("blocks_start_x")) {
            this.blocksStartX = Integer.parseInt(line.split(":")[1]);
        } else if (fieldName.equals("blocks_start_y")) {
            this.blocksStartY = Integer.parseInt(line.split(":")[1]);
        } else if (fieldName.equals("paddle_speed")) {
            this.paddleSpeed = Integer.parseInt(line.split(":")[1]);
        } else if (fieldName.equals("paddle_width")) {
            this.paddleWidth = Integer.parseInt(line.split(":")[1]);
        } else if (fieldName.equals("block_definitions")) {
            this.blockDefinitions = line.split(":")[1];
        } else if (fieldName.equals("row_height")) {
            this.rowHeight = Integer.parseInt(line.split(":")[1]);
        } else if (fieldName.equals("num_blocks")) {
            this.numBlocks = Integer.parseInt(line.split(":")[1]);
        } else if (fieldName.equals("background")) {
            Pattern p = Pattern.compile("color");
            Matcher m = p.matcher(line);
            if (m.find()) {
                Color color = ColorsParser.colorFromString(line.split(
                        ":")[1].substring(6, line.split(":")[1].length() - 1));
                bg = new BackGround(color);
            }

            p = Pattern.compile("image");
            m = p.matcher(line);
            if (m.find()) {
                try {
                    String path = line.split(
                            ":")[1].substring(6, line.split(":")[1].length() - 1);
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
                    Image image = ImageIO.read(is);
                    //InputStreamReader(
                    //ClassLoader.getSystemClassLoader().getResourceAsStream(path)));
                    /*Image image = ImageIO.read(new File("resources/" + line.split(
                            ":")[1].substring(6, line.split(":")[1].length() - 1)));*/

                    bg = new BackGround(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
