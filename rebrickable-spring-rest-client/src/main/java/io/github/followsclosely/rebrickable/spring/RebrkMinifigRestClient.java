package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkMinifigClient;
import io.github.followsclosely.rebrickable.dto.RebrkMinifig;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

public class RebrkMinifigRestClient extends AbstractRebrkRestClient implements RebrkMinifigClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkMinifig>> TYPE = new ParameterizedTypeReference<>() {
    };

    public RebrkMinifigRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    public RebrkMinifigRestClient(RestClient restClient) {
        super(restClient);
    }

    @Override
    public RebrkMinifig getMinifig(String id) {
        waitAsNeeded();
        RebrkMinifig minifig = restClient.get()
                .uri(builder -> builder.path("minifigs/" + id + "/").build())
                .retrieve()
                .body(RebrkMinifig.class);
        resetLastCallTime();
        return minifig;
    }

    @Override
    public RebrkResponse<RebrkMinifig> getMinifigs(Query query) {
        waitAsNeeded();
        RebrkResponse<RebrkMinifig> result = restClient.get()
                .uri(builder -> {
                    builder.path("minifigs/");
                    if (query != null) {
                        queryParam(builder, "page", query.getPage());
                        queryParam(builder, "page_size", query.getPageSize());
                        queryParam(builder, "min_parts", query.getMinParts());
                        queryParam(builder, "max_parts", query.getMaxParts());
                        queryParam(builder, "in_set_num", query.getInSetNumber());
                        queryParam(builder, "in_theme_id", query.getInThemeId());
                        queryParam(builder, "search", query.getQuery());
                    }
                    return builder.build();
                })
                .retrieve()
                .body(TYPE);

        resetLastCallTime();
        assert result != null;
        return result;
    }
}
