package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkApiRateLimiter;
import io.github.followsclosely.rebrickable.RebrkPartClient;
import io.github.followsclosely.rebrickable.dto.RebrkColorDetails;
import io.github.followsclosely.rebrickable.dto.RebrkPart;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

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

    public RebrkPartRestClient(String authorizationKey, RebrkApiRateLimiter rateLimiter) {
        super(authorizationKey, rateLimiter);
    }

    public RebrkPartRestClient(RestClient rebrickableRestClient) {
        super(rebrickableRestClient);
    }

    public RebrkPartRestClient(RestClient rebrickableRestClient, RebrkApiRateLimiter rateLimiter) {
        super(rateLimiter, rebrickableRestClient);
    }


    public RebrkPart getPart(String id) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkPart part = restClient.get()
                .uri(builder -> builder
                        .path("parts/" + id + "/")
                        .build())
                .retrieve()
                .body(RebrkPart.class);

        rebrkApiRateLimiter.resetLastCallTime();
        return part;
    }

    @Override
    public RebrkResponse<RebrkPart> getParts(Query query) {
        rebrkApiRateLimiter.waitAsNeeded();
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
                        queryParam(builder, "inc_part_details", Boolean.TRUE.equals(query.getIncludePartDetails()) ? 1 : 0);
                    }
                    return builder.build();
                }).retrieve().body(PART_TYPE_REF);

        rebrkApiRateLimiter.resetLastCallTime();
        assert result != null;
        return result;
    }

    @Override
    public Collection<RebrkColorDetails> getColorsOfPart(String id) {
        rebrkApiRateLimiter.waitAsNeeded();
        RebrkResponse<RebrkColorDetails> result = restClient.get()
                .uri(builder -> builder
                        .path("parts/" + id + "/colors/")
                        .queryParam("page_size", "1000")
                        .build())
                .retrieve()
                .body(COLOR_DETAILS_TYPE_REF);

        rebrkApiRateLimiter.resetLastCallTime();
        assert result != null;
        return result.getResults();
    }

}
