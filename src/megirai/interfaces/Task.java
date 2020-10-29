package megirai.interfaces;

/**
 * Task interface.
 * @param <T> - a generic object.
 */
public interface Task<T> {
    /**
     * Run the task.
     *
     * @return the task.
     */
    T run();
}
