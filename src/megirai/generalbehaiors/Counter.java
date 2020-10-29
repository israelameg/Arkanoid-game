package megirai.generalbehaiors;

/**
 * Counter class.
 */
public class Counter {

    private int count;

    /**
     * Constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number - the number that should add to the count.
     */
    public void increase(int number) {
        count = count + number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number - the number that should decrease from the count.
     */
    public void decrease(int number) {
        count = count - number;
    }

    /**
     * Get current count.
     *
     * @return count - the current count.
     */
    public int getValue() {
        return count;
    }
}