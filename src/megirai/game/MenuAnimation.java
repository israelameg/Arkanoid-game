package megirai.game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import megirai.generalbehaiors.Selction;
import megirai.interfaces.Menu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * MenuAnimation<T> implements Menu<T>.
 *
 * @param <T> - gets a task.
 */
public class MenuAnimation<T> implements Menu<T> {

    private List<Selction> selctions;
    private String menuTitle;
    private KeyboardSensor ks;
    private boolean stop;
    private List<T> menuReturnValues;
    private List<String> menuMessage;
    private List<String> menuKeys;
    private List<Boolean> isSubMenu;
    private List<Menu<T>> subMenus;
    private T status;
    private AnimationRunner ar;

    /**
     * Constructor.
     *
     * @param menuTitle - gets the menuTitle.
     * @param ks        - gets the KeyboardSensor.
     * @param ar        - gets the animation runner.
     */
    public MenuAnimation(String menuTitle, KeyboardSensor ks, AnimationRunner ar) {
        this.ks = ks;
        selctions = new LinkedList<>();
        this.menuTitle = menuTitle;
        this.stop = false;
        this.ar = ar;
        this.menuKeys = new ArrayList();
        this.menuMessage = new ArrayList();
        this.menuReturnValues = new ArrayList();
        this.isSubMenu = new ArrayList();
        this.subMenus = new ArrayList();
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.menuKeys.add(key);
        this.menuMessage.add(message);
        this.menuReturnValues.add(returnVal);
        this.isSubMenu.add(false);
        this.subMenus.add(null);
        //selctions.add(new Selction(key,message,returnVal));
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * Change stop to be false for the animation to continue.
     */
    public void dontStop() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, 800, 700);
        d.setColor(Color.yellow);
        d.drawText(10, 50, menuTitle, 32);
        d.setColor(Color.green);
        int y = 100;

        for (String s : menuMessage) {
            d.drawText(50, y, s, 25);
            y = y + 30;
        }

        for (String s : menuKeys) {
            if (ks.isPressed(s)) {
                this.stop = true;
                if (this.isSubMenu.get(menuKeys.indexOf(s))) {
                    Menu<T> subMenu = subMenus.get(menuKeys.indexOf(s));
                    this.ar.run(subMenu);
                    this.status = subMenu.getStatus();
                    this.stop = true;
                } else {
                    this.status = this.menuReturnValues.get(menuKeys.indexOf(s));
                    this.stop = true;
                }
                break;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    /**
     * Add sub menu.
     *
     * @param key     - gets the key.
     * @param message - gets the message.
     * @param subMenu - gets the sub menu.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.menuKeys.add(key);
        this.menuMessage.add(message);
        this.menuReturnValues.add(null);
        this.isSubMenu.add(true);
        this.subMenus.add(subMenu);
    }
}
