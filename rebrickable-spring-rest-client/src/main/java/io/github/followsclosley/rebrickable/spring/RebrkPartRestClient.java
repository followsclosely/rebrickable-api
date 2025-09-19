package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.RebrkPartClient;
import io.github.followsclosley.rebrickable.dto.RebrkColorDetails;
import io.github.followsclosley.rebrickable.dto.RebrkPart;
import io.github.followsclosley.rebrickable.dto.RebrkResponse;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Collection;

public class RebrkPartRestClient extends AbstractRebrkRestClient implements RebrkPartClient {

    private final static ParameterizedTypeReference<RebrkResponse<RebrkPart>> PART_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    private final static ParameterizedTypeReference<RebrkResponse<RebrkColorDetails>> COLOR_DETAILS_TYPE_REF
            = new ParameterizedTypeReference<>() {
    };

    public RebrkPartRestClient(String authorizationKey) {
        super(authorizationKey);
    }

    public RebrkPart getPart(String id) {
        return restClient.get()
                .uri(builder -> builder
                        .path("parts/" + id + "/")
                        .build())
                .retrieve()
                .body(RebrkPart.class);
    }

    @Override
    public RebrkResponse<RebrkPart> getParts(Query query) {
        RebrkResponse<RebrkPart> result = restClient.get()
                .uri(builder -> {
                    builder.path("parts/");
                    if (query != null) {
                        queryParam(builder, "page", query.getPage());
                        queryParam(builder, "page_size", query.getPageSize());
                        queryParam(builder, "part_number", query.getPartNumber());
                        //TODO: Verify that this field works!
                        queryParam(builder, "part_numbers", query.getPartNumbers());
                        queryParam(builder, "part_cat_id", query.getCategoryId());
                        queryParam(builder, "color_id", query.getColorId());
                        queryParam(builder, "bricklink_id", query.getBricklinkId());
                        queryParam(builder, "brickowl_id", query.getBrickowlId());
                        queryParam(builder, "lego_id", query.getLegoId());
                        queryParam(builder, "ldraw_id", query.getLdrawId());
                        queryParam(builder, "search", query.getQuery());
                    }
                    return builder.build();
                })
                .retrieve()
                .body(PART_TYPE_REF);

        assert result != null;
        return result;
    }

    @Override
    public Collection<RebrkColorDetails> getColorsOfPart(String id) {
        RebrkResponse<RebrkColorDetails> result = restClient.get()
                .uri(builder -> builder
                        .path("parts/" + id + "/colors/")
                        .queryParam("page_size", "1000")
                        .build())
                .retrieve()
                .body(COLOR_DETAILS_TYPE_REF);

        assert result != null;
        return result.getResults();
    }

}
