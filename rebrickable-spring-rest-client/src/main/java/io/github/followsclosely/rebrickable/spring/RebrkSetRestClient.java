package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkApiRateLimiter;
import io.github.followsclosely.rebrickable.RebrkSetClient;
import io.github.followsclosely.rebrickable.dto.RebrkInventoryMinifig;
import io.github.followsclosely.rebrickable.dto.RebrkInventoryPart;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import io.github.followsclosely.rebrickable.dto.RebrkSet;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.Collection;

public class RebrkSetRestClient extends AbstractRebrkRestClient implements RebrkSetClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkSet>> SET_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    private final static ParameterizedTypeReference<RebrkResponse<RebrkInventoryPart>> PARTS_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    private final static ParameterizedTypeReference<RebrkResponse<RebrkInventoryMinifig>> MINIFIGS_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    public RebrkSetRestClient(RebrkApiRateLimiter rateLimiter, String authorizationKey) {
        super(rateLimiter, authorizationKey);
    }

    public RebrkSetRestClient(RebrkApiRateLimiter rateLimiter, RestClient restClient) {
        super(rateLimiter, restClient);
    }

    public RebrkSet getSet(String number) {
        return this.getSet(number, false, false);
    }

    @Override
    public RebrkSet getSet(String number, boolean loadParts, boolean loadMinifigs) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkSet set = restClient.get()
                .uri(builder -> builder.path("sets/" + number + "/").build())
                .retrieve()
                .body(RebrkSet.class);

        if (loadParts) {
            rebrkApiRateLimiter.borrow(1000);
            //TODO: This may need to support paging at some point
            RebrkResponse<RebrkInventoryPart> parts = restClient.get()
                    .uri(builder -> builder
                            .path("sets/" + number + "/parts/")
                            .queryParam("page_size", "1000")
                            .build())
                    .retrieve()
                    .body(PARTS_TYPE_REF);

            if (parts != null && parts.getResults() != null) {
                assert set != null;
                set.setParts(parts.getResults());
            }
        }

        if (loadMinifigs) {
            rebrkApiRateLimiter.borrow(1000);
            RebrkResponse<RebrkInventoryMinifig> minifigs = restClient.get()
                    .uri(builder -> builder
                            .path("sets/" + number + "/minifigs/")
                            .queryParam("page_size", "1000")
                            .build())
                    .retrieve()
                    .body(MINIFIGS_TYPE_REF);

            if (minifigs != null && minifigs.getResults() != null) {
                assert set != null;
                set.setMinifigs(minifigs.getResults());
            }
        }

        rebrkApiRateLimiter.resetLastCallTime();
        return set;
    }


    @Override
    public RebrkResponse<RebrkSet> getSets(Query query) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkResponse<RebrkSet> result = restClient.get()
                .uri(builder -> {
                    builder.path("sets/");
                    if (query != null) {
                        queryParam(builder, "page", query.getPage());
                        queryParam(builder, "page_size", query.getPageSize());
                        queryParam(builder, "in_theme_id", query.getInThemeId());
                        queryParam(builder, "min_year", query.getMinYear());
                        queryParam(builder, "max_year", query.getMaxYear());
                        queryParam(builder, "min_parts", query.getMinParts());
                        queryParam(builder, "max_parts", query.getMaxParts());
                        queryParam(builder, "search", query.getQuery());
                    }
                    return builder.build();
                })
                .retrieve()
                .body(SET_TYPE_REF);

        rebrkApiRateLimiter.resetLastCallTime();
        assert result != null;
        return result;
    }

    @Override
    public Collection<RebrkSet> getSetsThatContainMinifig(String number) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkResponse<RebrkSet> result = restClient.get()
                .uri(builder -> builder
                        .path("minifigs/" + number + "/sets/")
                        .queryParam("page_size", "1000").build())
                .retrieve()
                .body(SET_TYPE_REF);

        rebrkApiRateLimiter.resetLastCallTime();
        assert result != null;
        return result.getResults();
    }
}
