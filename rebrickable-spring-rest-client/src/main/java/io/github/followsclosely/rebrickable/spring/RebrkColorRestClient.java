package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkApiRateLimiter;
import io.github.followsclosely.rebrickable.RebrkColorClient;
import io.github.followsclosely.rebrickable.dto.RebrkColor;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

/**
 * REST client implementation for accessing Rebrickable color resources.
 * <p>
 * This class provides methods to retrieve individual colors and lists of colors from the Rebrickable API.
 * It supports rate limiting and flexible construction using an API key, custom rate limiter, or custom RestClient.
 * </p>
 * <p>
 * Usage examples:
 * <pre>
 *     // Using an API key
 *     RebrkColorRestClient client = new RebrkColorRestClient("your-api-key");
 *
 *     // Using a custom rate limiter
 *     RebrkColorRestClient client = new RebrkColorRestClient("your-api-key", customRateLimiter);
 *
 *     // Using a custom RestClient
 *     RebrkColorRestClient client = new RebrkColorRestClient(customRestClient);
 * </pre>
 * </p>
 */
public class RebrkColorRestClient extends AbstractRebrkRestClient implements RebrkColorClient {

    /**
     * Type reference for deserializing color responses.
     */
    private final static ParameterizedTypeReference<RebrkResponse<RebrkColor>> COLOR_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    /**
     * Constructs a client using the provided API key and the default rate limiter.
     *
     * @param authorizationKey The Rebrickable API key.
     */
    public RebrkColorRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    /**
     * Constructs a client using the provided API key and a custom rate limiter.
     *
     * @param authorizationKey The Rebrickable API key.
     * @param rateLimiter The rate limiter to use for API calls.
     */
    public RebrkColorRestClient(String authorizationKey, RebrkApiRateLimiter rateLimiter) {
        super(authorizationKey, rateLimiter);
    }

    /**
     * Constructs a client using a custom RestClient and the default rate limiter.
     *
     * @param rebrickableRestClient The RestClient to use for API calls.
     */
    public RebrkColorRestClient(RestClient rebrickableRestClient) {
        super(rebrickableRestClient);
    }

    /**
     * Constructs a client using a custom RestClient and a custom rate limiter.
     *
     * @param rebrickableRestClient The RestClient to use for API calls.
     * @param rateLimiter The rate limiter to use for API calls.
     */
    public RebrkColorRestClient(RestClient rebrickableRestClient, RebrkApiRateLimiter rateLimiter) {
        super(rebrickableRestClient, rateLimiter);
    }

    /**
     * Retrieves a single color by its ID from the Rebrickable API.
     *
     * @param id The ID of the color to retrieve.
     * @return The {@link RebrkColor} object for the specified ID, or null if not found.
     */
    public RebrkColor getColor(Long id) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkColor color = restClient.get()
                .uri(builder -> builder.path("colors/" + id + "/").build())
                .retrieve()
                .body(RebrkColor.class);
        rebrkApiRateLimiter.resetLastCallTime();
        return color;
    }

    /**
     * Retrieves all colors from the Rebrickable API.
     *
     * @return A {@link RebrkResponse} containing a list of {@link RebrkColor} objects.
     */
    @Override
    public RebrkResponse<RebrkColor> getColors() {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkResponse<RebrkColor> colors = getColors(null);
        rebrkApiRateLimiter.resetLastCallTime();
        return colors;
    }

    /**
     * Retrieves colors from the Rebrickable API using query parameters for pagination and ordering.
     *
     * @param query The query parameters for pagination and ordering (may be null).
     * @return A {@link RebrkResponse} containing a list of {@link RebrkColor} objects.
     */
    @Override
    public RebrkResponse<RebrkColor> getColors(Query query) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkResponse<RebrkColor> colors = restClient.get()
                .uri(builder -> {
                    builder.path("colors/");
                    if (query != null) {
                        queryParam(builder, "page", query.getPage());
                        queryParam(builder, "page_size", query.getPageSize());
                        queryParam(builder, "ordering", query.getOrdering());
                    }
                    return builder.build();
                })
                .retrieve()
                .body(COLOR_TYPE_REF);
        rebrkApiRateLimiter.resetLastCallTime();
        return colors;
    }
}
