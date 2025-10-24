package io.github.followsclosely.rebrickable;

public interface RebrkApiRateLimiter {

    /**
     * Borrows additional milliseconds to be added to the next wait time.
     * This can be used to dynamically adjust the wait time based on
     * external factors or specific API requirements.
     *
     * @param millis The number of milliseconds to borrow.
     */
    default void borrow(long millis){};

    /**
     * Waits for at least one second since the last time this method was called.
     * If the required time has not elapsed, the current thread will sleep until it has.
     * This method is synchronized to ensure thread-safe updates to lastCallTime
     * and to prevent multiple threads from concurrently calculating the wait time.
     */
    default void waitAsNeeded(){};

    /**
     * Resets the last call time to the current system time.
     * This can be used to indicate that a call has just been made,
     * effectively starting the wait timer anew.
     */
    default void resetLastCallTime(){};
}
