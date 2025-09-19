package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.RebrkMinifigClient;
import io.github.followsclosley.rebrickable.dto.RebrkMinifig;
import io.github.followsclosley.rebrickable.dto.RebrkResponse;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Collection;

public class RebrkMinifigRestClient extends AbstractRebrkRestClient implements RebrkMinifigClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkMinifig>> TYPE = new ParameterizedTypeReference<>() {
    };

    public RebrkMinifigRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    @Override
    public RebrkMinifig getMinifig(String id) {
        return restClient.get()
                .uri(builder -> builder.path("minifigs/" + id + "/").build())
                .retrieve()
                .body(RebrkMinifig.class);
    }

    @Override
    public Collection<RebrkMinifig> getMinifigs() {
        RebrkResponse<RebrkMinifig> result = restClient.get()
                .uri(builder -> builder.path("minifigs/").queryParam("page_size", "1000").build())
                .retrieve()
                .body(TYPE);

        assert result != null;
        return result.getResults();
    }
}
