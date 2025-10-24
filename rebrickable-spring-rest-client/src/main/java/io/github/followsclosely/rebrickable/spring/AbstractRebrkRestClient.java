package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkApiRateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;

@Slf4j
@RequiredArgsConstructor
public class AbstractRebrkRestClient {

    protected final RestClient restClient;
    @Setter
    protected RebrkApiRateLimiter rebrkApiRateLimiter;

    public AbstractRebrkRestClient(String authorizationKey) {
        this(RestClient.builder()
                .baseUrl("https://rebrickable.com/api/v3/lego/")
                .defaultHeaders(headers -> {
                    headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
                    headers.add("Authorization", "key " + authorizationKey);
                }).build());
    }

    public void queryParam(UriBuilder builder, String name, Object value) {
        if (value != null) {
            builder.queryParam(name, value);
        }
    }

    public void queryParam(UriBuilder builder, String name, String[] values) {
        if (values != null && values.length > 0) {
            builder.queryParam(name, String.join(",", values));
        }
    }

    /**
     * Borrows additional milliseconds to be added to the next wait time.
     * This can be used to dynamically adjust the wait time based on
     * external factors or specific API requirements.
     *
     * @param millis The number of milliseconds to borrow.
     */
    protected void borrow(long millis) {
        if( rebrkApiRateLimiter == null ) {
            log.warn("Interacting with rebrickable without a rate limiter!");
        } else {
            rebrkApiRateLimiter.borrow(millis);
        }
    }

    /**
     * Waits for at least one second since the last time this method was called.
     * If the required time has not elapsed, the current thread will sleep until it has.
     * This method is synchronized to ensure thread-safe updates to lastCallTime
     * and to prevent multiple threads from concurrently calculating the wait time.
     */
    protected void waitAsNeeded() {
        if( rebrkApiRateLimiter == null ) {
            log.warn("Interacting with rebrickable without a rate limiter!");
        } else {
            rebrkApiRateLimiter.waitAsNeeded();
        }
    }

    protected void resetLastCallTime() {
        if( rebrkApiRateLimiter == null ) {
            log.warn("Interacting with rebrickable without a rate limiter!");
        } else {
            rebrkApiRateLimiter.resetLastCallTime();
        }
    }
}
