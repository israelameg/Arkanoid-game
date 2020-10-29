package megirai.interfaces;

/**
 * Menu<T> interface extends Animation.
 * @param <T> - a generic object.
 */
public interface Menu<T> extends Animation {

    /**
     * Add a selection to the menu.
     *
     * @param key       - gets the key,
     * @param message   - gets the instruction for the user.
     * @param returnVal - gets the return value - a task
     *                  that should be happen after the click.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Return the status.
     *
     * @return the status.
     */
    T getStatus();
}
