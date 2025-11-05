package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkApiRateLimiter;
import io.github.followsclosely.rebrickable.RebrkCategoryClient;
import io.github.followsclosely.rebrickable.dto.RebrkCategory;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.Collection;

public class RebrkCategoryRestClient extends AbstractRebrkRestClient implements RebrkCategoryClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkCategory>> TYPE_REF = new ParameterizedTypeReference<>() {
    };

    public RebrkCategoryRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    public RebrkCategoryRestClient(String authorizationKey, RebrkApiRateLimiter rateLimiter) {
        super(authorizationKey, rateLimiter);
    }

    public RebrkCategoryRestClient(RestClient rebrickableRestClient) {
        super(rebrickableRestClient);
    }

    public RebrkCategoryRestClient(RestClient rebrickableRestClient, RebrkApiRateLimiter rateLimiter) {
        super(rateLimiter, rebrickableRestClient);
    }

    @Override
    public RebrkCategory getCategory(Long id) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkCategory rebrkCategory = restClient.get()
                .uri(builder -> builder.path("part_categories/" + id + "/").build())
                .retrieve()
                .body(RebrkCategory.class);
        rebrkApiRateLimiter.resetLastCallTime();
        return rebrkCategory;
    }

    @Override
    public Collection<RebrkCategory> getCategories(Query query) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkResponse<RebrkCategory> result = restClient.get()
                .uri(builder -> {
                    builder.path("part_categories/");
                    if (query != null) {
                        queryParam(builder, "page", query.getPage());
                        queryParam(builder, "page_size", query.getPageSize());
                        queryParam(builder, "ordering", query.getOrdering());
                    }
                    return builder.build();
                })
                .retrieve()
                .body(TYPE_REF);
        rebrkApiRateLimiter.resetLastCallTime();
        assert result != null;
        return result.getResults();
    }
}
