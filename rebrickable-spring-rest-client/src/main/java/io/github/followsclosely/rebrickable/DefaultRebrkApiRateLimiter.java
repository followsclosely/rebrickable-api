package io.github.followsclosely.rebrickable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultRebrkApiRateLimiter implements RebrkApiRateLimiter {
    // The minimum delay required between calls, in milliseconds.
    public static final long MIN_DELAY_MS = 1000;

    private final long minDelay;
    private final long minDelayBonus;

    // A volatile variable to store the timestamp of the last successful call.
    // 'volatile' ensures visibility across threads, which is important
    // if multiple threads might call waitAsNeeded.
    private volatile long lastCallTime = 0;
    private volatile long borrowedMillis = 0;
    private volatile long totalCallsMade = 0;

    public DefaultRebrkApiRateLimiter(){
        this(MIN_DELAY_MS);
    }

    public DefaultRebrkApiRateLimiter(long minimumDelay){
        this(minimumDelay, 10);
    }

    public DefaultRebrkApiRateLimiter(long minDelay, long minDelayBonus){
        this.minDelay = minDelay;
        this.minDelayBonus = minDelayBonus;
    }

    /**
     * Borrows additional milliseconds to be added to the next wait time.
     * This can be used to dynamically adjust the wait time based on
     * external factors or specific API requirements.
     *
     * @param millis The number of milliseconds to borrow.
     */
    @Override
    public synchronized void borrow(long millis) {
        this.totalCallsMade++;
        this.borrowedMillis += millis;
    }

    /**
     * Waits for at least one second since the last time this method was called.
     * If the required time has not elapsed, the current thread will sleep until it has.
     * This method is synchronized to ensure thread-safe updates to lastCallTime
     * and to prevent multiple threads from concurrently calculating the wait time.
     */
    @Override
    public synchronized void waitAsNeeded() {
        this.totalCallsMade++;
        long currentTime = System.currentTimeMillis();
        long timeSinceLastCall = currentTime - lastCallTime;

        long delayNeeded = minDelay + borrowedMillis;
        if (timeSinceLastCall < delayNeeded) {
            long timeToWait = delayNeeded - timeSinceLastCall;

            try {
                long timeToWaitPlus =  (timeToWait + ((long) (Math.random() * minDelayBonus)));
                log.info("Call-{}: Need to wait {}ms, but waiting for {}ms to enforce the {}ms delay (plus {}ms)...", totalCallsMade, timeToWait, timeToWaitPlus, minDelay, timeToWaitPlus-timeToWait);
                Thread.sleep(timeToWait);
                this.borrowedMillis = 0;
            } catch (InterruptedException e) {
                // Restore the interrupted status
                Thread.currentThread().interrupt();
                log.error("The wait was interrupted.");
                // Note: The lastCallTime will be updated below even if interrupted,
                // which might slightly skew the timing but prevents subsequent waits
                // from being excessively long.
            }
        }

        // Update the last call time to the *current* system time
        // after any necessary waiting has occurred.
        // This ensures the next call waits one second from *this* moment.
        lastCallTime = System.currentTimeMillis();
    }

    /**
     * Resets the last call time to the current system time.
     * This can be used to indicate that a call has just been made,
     * effectively starting the wait timer anew.
     */
    @Override
    public void resetLastCallTime(){
        lastCallTime = System.currentTimeMillis();
    }
}
