package io.github.followsclosely.rebrickable;

public class RebrkTestUtilities {
    /**
     * Rate limiter for Rebrickable API requests in tests.
     */
    public static final RebrkApiRateLimiter RATE_LIMITER = new DefaultRebrkApiRateLimiter();
}
