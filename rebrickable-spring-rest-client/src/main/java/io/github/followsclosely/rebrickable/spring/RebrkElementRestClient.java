package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkElementClient;
import io.github.followsclosely.rebrickable.dto.RebrkElement;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Collection;

public class RebrkElementRestClient extends AbstractRebrkRestClient implements RebrkElementClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkElement>> ELEMENT_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    public RebrkElementRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    public RebrkElementRestClient(org.springframework.web.client.RestClient restClient) {
        super(restClient);
    }

    @Override
    public RebrkElement getElement(String id) {
        return restClient.get()
                .uri(builder -> builder
                        .path("elements/" + id + "/")
                        .build())
                .retrieve()
                .body(RebrkElement.class);
    }

    @Override
    public Collection<RebrkElement> getElementsFromMinifig(String id) {
        RebrkResponse<RebrkElement> result = restClient.get()
                .uri(builder -> builder
                        .path("minifigs/" + id + "/parts/")
                        .queryParam("page_size", "1000")
                        .build())
                .retrieve()
                .body(ELEMENT_TYPE_REF);

        assert result != null;
        return result.getResults();
    }
}
