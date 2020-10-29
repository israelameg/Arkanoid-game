package megirai.game;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ColorsParser class.
 */
public class ColorsParser {

    /**
     * Parse color definition and return the specified color.
     *
     * @param s - gets the color name.
     * @return the color.
     */
    public static java.awt.Color colorFromString(String s) {
        Color color = null;
        Pattern c = Pattern.compile("RGB *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        Matcher m = c.matcher(s);

        if (m.matches()) {
            return new Color(Integer.valueOf(m.group(1)),  // r
                    Integer.valueOf(m.group(2)),  // g
                    Integer.valueOf(m.group(3))); // b
        }

        switch (s) {
            case "black":
                color = Color.BLACK;
                break;
            case "blue":
                color = Color.BLUE;
                break;
            case "cyan":
                color = Color.CYAN;
                break;
            case "darkGray":
                color = Color.DARK_GRAY;
                break;
            case "gray":
                color = Color.GRAY;
                break;
            case "green":
                color = Color.GREEN;
                break;

            case "yellow":
                color = Color.YELLOW;
                break;
            case "lightGray":
                color = Color.LIGHT_GRAY;
                break;
            case "magneta":
                color = Color.MAGENTA;
                break;
            case "orange":
                color = Color.ORANGE;
                break;
            case "pink":
                color = Color.PINK;
                break;
            case "red":
                color = Color.RED;
                break;
            case "white":
                color = Color.WHITE;
                break;
            default:
                break;
        }

        return color;
    }
}
