package megirai.generalbehaiors;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import megirai.interfaces.Sprite;

/**
 * megirai.generalbehaiors.SpriteCollection class.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<>();

    /**
     * Add the sprite to the game.
     *
     * @param s - a sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Remove the sprite from the game.
     *
     * @param s - a sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
        /*for (int i=0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }*/
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d - a DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

}
