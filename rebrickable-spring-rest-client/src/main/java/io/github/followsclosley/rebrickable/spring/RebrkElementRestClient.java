package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.RebrkElementClient;
import io.github.followsclosley.rebrickable.dto.RebrkElement;
import io.github.followsclosley.rebrickable.dto.RebrkResponse;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Collection;

public class RebrkElementRestClient extends AbstractRebrkRestClient implements RebrkElementClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkElement>> ELEMENT_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    public RebrkElementRestClient(String authorizationKey) {
        super(authorizationKey);
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
