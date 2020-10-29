package megirai.game;

import megirai.geometry.Block;
import megirai.interfaces.BlockCreator;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GeneralBlock class BlockCreator.
 */
public class GeneralBlock implements BlockCreator {

    private Pattern p;
    private Matcher m;
    private double width;
    private double height;
    private int hitPoints;
    private Image image;
    private Color strokeColor;
    private Block block;
    private Map<String, FillBlock> bgBlock = new HashMap<>();

    @Override
    public Block create(int xpos, int ypos) {
        block = new Block(xpos, ypos, width, height, hitPoints);
        if (strokeColor != null) {
            block.setStrokeColor(strokeColor);
        }
        if (bgBlock != null) {
            block.setBgBlock(bgBlock);
        }
        return block;
    }

    /**
     * Gets a line from the file and read the information.
     *
     * @param line - gets a line from the file.
     */
    public void setBlock(String line) {
        bgBlock = new HashMap<>();
        for (String s : line.split(" ")) {
            if (s.contains("fill")) {
                if (line.contains("color")) {
                    for (String string : line.split(" ")) {
                        if (string.contains("color")) {
                            Color color = ColorsParser.colorFromString(string.split(":")[1].substring(
                                    6, string.split(":")[1].length() - 1));
                            FillBlock bg = new FillBlock(color);
                            p = Pattern.compile("-");
                            m = p.matcher(string.split(":")[0]);
                            if (m.find()) {
                                String hitPoint = string.split(":")[0].split("-")[1];
                                bgBlock.put(hitPoint, bg);
                            } else {
                                bgBlock.put("1", bg);
                            }

                        }
                    }
                }

                p = Pattern.compile("image");
                m = p.matcher(line);
                if (m.find()) {
                    try {
                        for (String string : line.split(" ")) {
                            m = p.matcher(string);
                            if (m.find()) {
                                String path = string.split(":")
                                        [1].substring(6, string.split(":")[1].length() - 1);
                                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
                                image = ImageIO.read(is);
                                /*image = ImageIO.read(new File("resources/" + string.split(":")
                                        [1].substring(6, string.split(":")[1].length() - 1)));*/
                                FillBlock bg = new FillBlock(image);
                                p = Pattern.compile("-");
                                m = p.matcher(string.split(":")[0]);
                                if (m.find()) {
                                    String hitPoint = string.split(":")[0].split("-")[1];
                                    bgBlock.put(hitPoint, bg);
                                } else {
                                    bgBlock.put("1", bg);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            switch (s.split(":")[0]) {
                case ("width"):
                    this.width = Double.parseDouble(s.split(":")[1]);
                    break;
                case ("height"):
                    this.height = Double.parseDouble(s.split(":")[1]);
                    break;
                case ("hit_points"):
                    this.hitPoints = Integer.parseInt(s.split(":")[1]);
                    break;
                case ("stroke"):
                    strokeColor = ColorsParser.colorFromString(s.split(":")[1].substring(
                            6, s.split(":")[1].length() - 1));
                    break;
                default:
                    break;
            }
        }
    }
}
