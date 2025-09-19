package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.RebrkCategoryClient;
import io.github.followsclosley.rebrickable.dto.RebrkCategory;
import io.github.followsclosley.rebrickable.dto.RebrkResponse;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Collection;

public class RebrkCategoryRestClient extends AbstractRebrkRestClient implements RebrkCategoryClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkCategory>> TYPE_REF = new ParameterizedTypeReference<>() {
    };

    public RebrkCategoryRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    @Override
    public RebrkCategory getCategory(Long id) {
        return restClient.get()
                .uri(builder -> builder.path("part_categories/" + id + "/").build())
                .retrieve()
                .body(RebrkCategory.class);
    }

    @Override
    public Collection<RebrkCategory> getCategories() {

        RebrkResponse<RebrkCategory> result = restClient.get()
                .uri(builder -> builder.path("part_categories/").queryParam("page_size", "1000").build())
                .retrieve()
                .body(TYPE_REF);

        assert result != null;
        return result.getResults();
    }
}
