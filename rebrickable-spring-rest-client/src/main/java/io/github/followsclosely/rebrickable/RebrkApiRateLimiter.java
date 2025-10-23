package io.github.followsclosely.rebrickable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RebrkApiRateLimiter {
    // The minimum delay required between calls, in milliseconds.
    public static final long MIN_DELAY_MS = 1000;

    private final long minDelay;
    private final long minDelayBonus;

    // A volatile variable to store the timestamp of the last successful call.
    // 'volatile' ensures visibility across threads, which is important
    // if multiple threads might call waitAsNeeded.
    private volatile long lastCallTime = 0;

    public RebrkApiRateLimiter(){
        this(MIN_DELAY_MS);
    }

    public RebrkApiRateLimiter(long minimumDelay){
        this(MIN_DELAY_MS, 100);
    }

    public RebrkApiRateLimiter(long minDelay, long minDelayBonus){
        this.minDelay = minDelay;
        this.minDelayBonus = minDelayBonus;
    }

    /**
     * Waits for at least one second since the last time this method was called.
     * If the required time has not elapsed, the current thread will sleep until it has.
     * This method is synchronized to ensure thread-safe updates to lastCallTime
     * and to prevent multiple threads from concurrently calculating the wait time.
     */
    public synchronized void waitAsNeeded() {
        long currentTime = System.currentTimeMillis();
        long timeSinceLastCall = currentTime - lastCallTime;

        if (timeSinceLastCall < minDelay) {
            long timeToWait = minDelay - timeSinceLastCall;

            try {
                long timeToWaitPlus =  (timeToWait + ((long) (Math.random() * minDelayBonus)));
                log.info("Need to wait {}ms, but waiting for {}ms to enforce the {}ms delay (plus {}ms)...", timeToWait, timeToWaitPlus, minDelay, timeToWaitPlus-timeToWait);
                Thread.sleep(timeToWait);
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
}
