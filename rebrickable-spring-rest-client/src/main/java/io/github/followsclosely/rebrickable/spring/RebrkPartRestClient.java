package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkPartClient;
import io.github.followsclosely.rebrickable.dto.RebrkColorDetails;
import io.github.followsclosely.rebrickable.dto.RebrkPart;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;

import java.net.URI;
import java.util.Collection;

@Slf4j
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

    public RebrkPartRestClient(org.springframework.web.client.RestClient restClient) {
        super(restClient);
    }

    public RebrkPart getPart(String id) {
        waitAsNeeded();
        RebrkPart part = restClient.get()
                .uri(builder -> builder
                        .path("parts/" + id + "/")
                        .build())
                .retrieve()
                .body(RebrkPart.class);

        resetLastCallTime();
        return part;
    }

    @Override
    public RebrkResponse<RebrkPart> getParts(Query query) {
        waitAsNeeded();
        RebrkResponse<RebrkPart> result = restClient.get()
                .uri(builder -> {
                    builder.path("parts/");
                    if (query != null) {
                        queryParam(builder, "page", query.getPage());
                        queryParam(builder, "page_size", query.getPageSize());
                        queryParam(builder, "part_number", query.getPartNumber());
                        //TODO: Verify that this field works!
                        queryParam(builder, "part_nums", query.getPartNumbers());
                        queryParam(builder, "part_cat_id", query.getCategoryId());
                        queryParam(builder, "color_id", query.getColorId());
                        queryParam(builder, "bricklink_id", query.getBricklinkId());
                        queryParam(builder, "brickowl_id", query.getBrickowlId());
                        queryParam(builder, "lego_id", query.getLegoId());
                        queryParam(builder, "ldraw_id", query.getLdrawId());
                        queryParam(builder, "search", query.getQuery());
                    }
                    return builder.build();
                }).retrieve().body(PART_TYPE_REF);

        resetLastCallTime();
        assert result != null;
        return result;
    }

    @Override
    public Collection<RebrkColorDetails> getColorsOfPart(String id) {
        waitAsNeeded();
        RebrkResponse<RebrkColorDetails> result = restClient.get()
                .uri(builder -> builder
                        .path("parts/" + id + "/colors/")
                        .queryParam("page_size", "1000")
                        .build())
                .retrieve()
                .body(COLOR_DETAILS_TYPE_REF);

        resetLastCallTime();
        assert result != null;
        return result.getResults();
    }

}
