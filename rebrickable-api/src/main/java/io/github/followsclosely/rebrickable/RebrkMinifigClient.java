package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkMinifig;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import lombok.Builder;
import lombok.Data;

public interface RebrkMinifigClient {

    RebrkMinifig getMinifig(String id);

    RebrkResponse<RebrkMinifig> getMinifigs(Query query);

    @Data
    @Builder
    class Query {
        private Integer page;
        private Integer pageSize;
        private Integer minParts;
        private Integer maxParts;
        private String inSetNumber;
        private Integer inThemeId;
        private String query;
    }
}
