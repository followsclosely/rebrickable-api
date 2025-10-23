package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkColorClient;
import io.github.followsclosely.rebrickable.dto.RebrkColor;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

public class RebrkColorRestClient extends AbstractRebrkRestClient implements RebrkColorClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkColor>> COLOR_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    public RebrkColorRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    public RebrkColorRestClient(RestClient restClient) {
        super(restClient);
    }

    public RebrkColor getColor(Long id) {
        waitAsNeeded();
        return restClient.get()
                .uri(builder -> builder.path("colors/" + id + "/").build())
                .retrieve()
                .body(RebrkColor.class);
    }

    @Override
    public RebrkResponse<RebrkColor> getColors() {
        waitAsNeeded();
        return getColors(null);
    }

    @Override
    public RebrkResponse<RebrkColor> getColors(Query query) {
        waitAsNeeded();
        return restClient.get()
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
    }
}
