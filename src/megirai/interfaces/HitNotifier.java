package megirai.interfaces;

/**
 * HitNotifier interface - indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl - an megirai.interfaces.HitListener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl - an megirai.interfaces.HitListener.
     */
    void removeHitListener(HitListener hl);
}
