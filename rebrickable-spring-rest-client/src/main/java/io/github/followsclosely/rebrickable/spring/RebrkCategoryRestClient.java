package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkCategoryClient;
import io.github.followsclosely.rebrickable.dto.RebrkCategory;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.Collection;

public class RebrkCategoryRestClient extends AbstractRebrkRestClient implements RebrkCategoryClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkCategory>> TYPE_REF = new ParameterizedTypeReference<>() {
    };

    public RebrkCategoryRestClient(RestClient restClient) {
        super(restClient);
    }

    public RebrkCategoryRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    @Override
    public RebrkCategory getCategory(Long id) {
        waitAsNeeded();
        return restClient.get()
                .uri(builder -> builder.path("part_categories/" + id + "/").build())
                .retrieve()
                .body(RebrkCategory.class);
    }

    @Override
    public Collection<RebrkCategory> getCategories(Query query) {
        waitAsNeeded();
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

        assert result != null;
        return result.getResults();
    }
}
