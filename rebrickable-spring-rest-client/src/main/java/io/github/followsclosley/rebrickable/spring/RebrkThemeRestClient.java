package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.RebrkThemeClient;
import io.github.followsclosley.rebrickable.dto.RebrkResponse;
import io.github.followsclosley.rebrickable.dto.RebrkTheme;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Collection;

public class RebrkThemeRestClient extends AbstractRebrkRestClient implements RebrkThemeClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkTheme>> Theme_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    public RebrkThemeRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    @Override
    public RebrkTheme getTheme(Long id) {
        return restClient.get()
                .uri(builder -> builder.path("themes/" + id + "/").build())
                .retrieve()
                .body(RebrkTheme.class);
    }

    @Override
    public Collection<RebrkTheme> getThemes() {

        RebrkResponse<RebrkTheme> result = restClient.get()
                .uri(builder -> builder.path("themes/").queryParam("page_size", "1000").build())
                .retrieve()
                .body(Theme_TYPE_REF);

        assert result != null;
        return result.getResults();
    }
}
