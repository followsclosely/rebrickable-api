package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkApiRateLimiter;
import io.github.followsclosely.rebrickable.RebrkThemeClient;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import io.github.followsclosely.rebrickable.dto.RebrkTheme;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.Collection;

public class RebrkThemeRestClient extends AbstractRebrkRestClient implements RebrkThemeClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkTheme>> Theme_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    public RebrkThemeRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    public RebrkThemeRestClient(String authorizationKey, RebrkApiRateLimiter rateLimiter) {
        super(authorizationKey, rateLimiter);
    }

    public RebrkThemeRestClient(RestClient rebrickableRestClient) {
        super(rebrickableRestClient);
    }

    public RebrkThemeRestClient(RestClient rebrickableRestClient, RebrkApiRateLimiter rateLimiter) {
        super(rebrickableRestClient, rateLimiter);
    }


    @Override
    public RebrkTheme getTheme(Long id) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkTheme theme = restClient.get()
                .uri(builder -> builder.path("themes/" + id + "/").build())
                .retrieve()
                .body(RebrkTheme.class);

        rebrkApiRateLimiter.resetLastCallTime();
        return theme;
    }

    @Override
    public Collection<RebrkTheme> getThemes(Query query) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkResponse<RebrkTheme> result = restClient.get()
                .uri(builder -> {
                    builder.path("themes/");
                    if (query != null) {
                        queryParam(builder, "page", query.getPage());
                        queryParam(builder, "page_size", query.getPageSize());
                        queryParam(builder, "ordering", query.getOrdering());
                    }
                    return builder.build();
                })
                .retrieve()
                .body(Theme_TYPE_REF);

        rebrkApiRateLimiter.resetLastCallTime();
        assert result != null;
        return result.getResults();
    }
}
