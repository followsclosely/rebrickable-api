package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.RebrkColorClient;
import io.github.followsclosley.rebrickable.dto.RebrkColor;
import io.github.followsclosley.rebrickable.dto.RebrkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

import java.util.Collection;

public class RebrkColorRestClient extends AbstractRebrkRestClient implements RebrkColorClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkColor>> COLOR_TYPE_REF
            = new ParameterizedTypeReference<>() {};

    public RebrkColorRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    public RebrkColor getColor(Long id) {
        return restClient.get()
                .uri(builder -> builder.path("colors/" + id + "/").build())
                .retrieve()
                .body(RebrkColor.class);
    }

    @Override
    public Collection<RebrkColor> getColors() {

        RebrkResponse<RebrkColor> result = restClient.get()
                .uri(builder -> builder.path("colors/").queryParam("page_size", "1000").build())
                .retrieve()
                .body(COLOR_TYPE_REF);

        assert result != null;
        return result.getResults();
    }
}
