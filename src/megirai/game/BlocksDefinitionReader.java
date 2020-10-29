package megirai.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BlocksDefinitionReader class.
 */
public class BlocksDefinitionReader {

    /**
     * Read from the block's file.
     *
     * @param reader - gets the file.
     * @return blocksFromSymbolsFactory object that include the block's information.
     * @throws IOException - if buffer reader have not succeeded to read from the file.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws IOException {
        Map<String, Integer> spacerWidths = new HashMap<>();
        Map<String, GeneralBlock> blockCreators = new HashMap<>();
        Pattern p;
        Matcher m;
        String defLine = null;

        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            GeneralBlock b = new GeneralBlock();

            //default def
            p = Pattern.compile("default");
            m = p.matcher(line);
            if (m.find() && !(line.contains("#"))) {
                defLine = line;
            }

            //block def
            p = Pattern.compile("bdef");
            m = p.matcher(line);
            if (m.find()) {
                String symbol = line.split(" ")[1].split(":")[1];
                if (defLine != null) {
                    System.out.println(defLine);
                    b.setBlock(defLine);
                }
                b.setBlock(line);
                blockCreators.put(symbol, b);
            }

            //symbol def
            p = Pattern.compile("sdef");
            m = p.matcher(line);
            if (m.find()) {
                String symbol = line.split(" ")[1].split(":")[1];
                int width = Integer.parseInt(line.split(" ")[2].split(":")[1]);
                spacerWidths.put(symbol, width);
            }
        }

        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }
}
