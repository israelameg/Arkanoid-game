package megirai.game;

import megirai.geometry.Block;

import java.util.Map;

/**
 * BlocksFromSymbolsFactory class.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, GeneralBlock> blockCreators;

    /**
     * Constructor.
     *
     * @param spacerWidths  - a map that store the symbol and his space width.
     * @param blockCreators - a map the store the symbol and his block information.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, GeneralBlock> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * Check if the string is a space symbol.
     *
     * @param s - gets a string.
     * @return true if 's' is a valid space symbol., else return false.
     */
    public boolean isSpaceSymbol(String s) {
        for (String key : spacerWidths.keySet()) {
            if (key.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the string is a block symbol.
     *
     * @param s - gets a string.
     * @return returns true if 's' is a valid block symbol.., else return false.
     */
    public boolean isBlockSymbol(String s) {
        for (String key : blockCreators.keySet()) {
            if (key.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s - gets a string.
     * @param x - gets the x position.
     * @param y - gets the y position.
     * @return a block according to the definitions associated with symbol s.
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s - gets a spacer-symbol.
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
