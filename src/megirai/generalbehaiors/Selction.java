package megirai.generalbehaiors;

/**
 * Selction class.
 */
public class Selction {

    private String key;
    private String message;
    private Object returnValue;

    /**
     * Constructor.
     *
     * @param key         - gets a key.
     * @param message     - gets a message.
     * @param returnValue - gets a returnValue.
     */
    public Selction(String key, String message, Object returnValue) {
        this.key = key;
        this.message = message;
        this.returnValue = returnValue;
    }

    /**
     * Return the return value.
     *
     * @return the return value.
     */
    public Object getReturnValue() {
        return returnValue;
    }

    /**
     * Return the key.
     *
     * @return the key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Return the message.
     *
     * @return the message.
     */
    public String getMessage() {
        return message;
    }
}
