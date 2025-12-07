package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.DefaultRebrkApiRateLimiter;
import io.github.followsclosely.rebrickable.RebrkApiRateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;

import java.util.Objects;

/**
 * Abstract base class for Rebrickable REST API clients.
 * <p>
 * Provides common configuration and utility methods for interacting with the Rebrickable API using Spring's RestClient.
 * Handles authorization, rate limiting, and query parameter construction. Subclasses should implement specific API operations.
 * </p>
 * <p>
 * Usage examples:
 * <pre>
 *     // Using an API key and default rate limiter
 *     AbstractRebrkRestClient client = new ConcreteRebrkRestClient("your-api-key");
 *
 *     // Using a custom rate limiter
 *     AbstractRebrkRestClient client = new ConcreteRebrkRestClient("your-api-key", customRateLimiter);
 *
 *     // Using a custom RestClient
 *     AbstractRebrkRestClient client = new ConcreteRebrkRestClient(customRestClient);
 * </pre>
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractRebrkRestClient {

    /**
     * The RestClient used to make HTTP requests to the Rebrickable API.
     */
    protected final RestClient restClient;
    /**
     * The rate limiter used to control the frequency of API calls.
     */
    protected final RebrkApiRateLimiter rebrkApiRateLimiter;

    /**
     * Constructs a client with the given authorization key and the default rate limiter.
     *
     * @param authorizationKey The Rebrickable API key.
     */
    public AbstractRebrkRestClient(String authorizationKey) {
        this(authorizationKey, DefaultRebrkApiRateLimiter.DEFAULT_INSTANCE);
    }

    /**
     * Constructs a client with the given authorization key and a custom rate limiter.
     *
     * @param authorizationKey    The Rebrickable API key.
     * @param rebrkApiRateLimiter The rate limiter to use for API calls.
     */
    public AbstractRebrkRestClient(String authorizationKey, RebrkApiRateLimiter rebrkApiRateLimiter) {
        this(RestClient.builder()
                        .baseUrl("https://rebrickable.com/api/v3/lego/")
                        .defaultHeaders(headers -> {
                            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
                            headers.add("Authorization", "key " + authorizationKey);
                        }).build(),
                Objects.requireNonNull(rebrkApiRateLimiter, "RebrkApiRateLimiter must not be null")
        );
    }

    /**
     * Constructs a client with a custom RestClient and the default rate limiter.
     *
     * @param rebrickableRestClient The RestClient to use for API calls.
     */
    public AbstractRebrkRestClient(RestClient rebrickableRestClient) {
        this(
                Objects.requireNonNull(rebrickableRestClient, "rebrickableRestClient must not be null"),
                DefaultRebrkApiRateLimiter.DEFAULT_INSTANCE
        );
    }

    /**
     * Adds a query parameter to the URI builder if the value is not null.
     *
     * @param builder The UriBuilder to add the parameter to.
     * @param name    The name of the query parameter.
     * @param value   The value of the query parameter (single value).
     */
    public void queryParam(UriBuilder builder, String name, Object value) {
        if (value != null) {
            builder.queryParam(name, value);
        }
    }

    /**
     * Adds a query parameter to the URI builder if the array is not null or empty.
     * The values are joined with commas.
     *
     * @param builder The UriBuilder to add the parameter to.
     * @param name    The name of the query parameter.
     * @param values  The array of values for the query parameter.
     */
    public void queryParam(UriBuilder builder, String name, String[] values) {
        if (values != null && values.length > 0) {
            builder.queryParam(name, String.join(",", values));
        }
    }
}
