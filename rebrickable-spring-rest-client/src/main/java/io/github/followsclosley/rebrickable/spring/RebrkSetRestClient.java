package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.RebrkSetClient;
import io.github.followsclosley.rebrickable.dto.RebrkResponse;
import io.github.followsclosley.rebrickable.dto.RebrkSet;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

import java.util.Collection;

public class RebrkSetRestClient extends AbstractRebrkRestClient implements RebrkSetClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkSet>> COLOR_TYPE_REF
            = new ParameterizedTypeReference<>() {};

    public RebrkSetRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    public RebrkSet getSet(String number) {
        return restClient.get()
                .uri(builder -> builder.path("sets/" + number + "/").build())
                .retrieve()
                .body(RebrkSet.class);
    }

    @Override
    public Collection<RebrkSet> getSets() {
        RebrkResponse<RebrkSet> result = restClient.get()
                .uri(builder -> builder.path("sets/").queryParam("page_size", "1000").build())
                .retrieve()
                .body(COLOR_TYPE_REF);

        assert result != null;
        return result.getResults();
    }
}
