package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkApiRateLimiter;
import io.github.followsclosely.rebrickable.RebrkElementClient;
import io.github.followsclosely.rebrickable.dto.RebrkElement;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.Collection;

public class RebrkElementRestClient extends AbstractRebrkRestClient implements RebrkElementClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkElement>> ELEMENT_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    public RebrkElementRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    public RebrkElementRestClient(String authorizationKey, RebrkApiRateLimiter rateLimiter) {
        super(authorizationKey, rateLimiter);
    }

    public RebrkElementRestClient(RestClient restClient) {
        super(restClient);
    }

    public RebrkElementRestClient(RebrkApiRateLimiter rateLimiter, RestClient restClient) {
        super(rateLimiter, restClient);
    }


    @Override
    public RebrkElement getElement(String id) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkElement element = restClient.get()
                .uri(builder -> builder
                        .path("elements/" + id + "/")
                        .build())
                .retrieve()
                .body(RebrkElement.class);
        rebrkApiRateLimiter.resetLastCallTime();
        return element;
    }

    @Override
    public Collection<RebrkElement> getElementsFromMinifig(String id) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkResponse<RebrkElement> result = restClient.get()
                .uri(builder -> builder
                        .path("minifigs/" + id + "/parts/")
                        .queryParam("page_size", "1000")
                        .build())
                .retrieve()
                .body(ELEMENT_TYPE_REF);
        rebrkApiRateLimiter.resetLastCallTime();
        assert result != null;
        return result.getResults();
    }
}
