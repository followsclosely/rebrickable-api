package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkApiRateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class AbstractRebrkRestClient {

    protected final RebrkApiRateLimiter rebrkApiRateLimiter;
    protected final RestClient restClient;

    public AbstractRebrkRestClient(RebrkApiRateLimiter rebrkApiRateLimiter, String authorizationKey) {
        this(Objects.requireNonNull(rebrkApiRateLimiter, "RebrkApiRateLimiter must not be null"),
            RestClient.builder()
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
}
