package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import io.github.followsclosely.rebrickable.dto.RebrkSet;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

public interface RebrkSetClient {
    RebrkSet getSet(String number);

    RebrkSet getSet(String number, boolean loadParts, boolean loadMinifigs);

    RebrkResponse<RebrkSet> getSets(Query query);

    Collection<RebrkSet> getSetsThatContainMinifig(String number);

    @Data
    @Builder
    class Query {
        private Integer page;
        private Integer pageSize;
        private Integer inThemeId;
        private Integer minYear;
        private Integer maxYear;
        private Integer minParts;
        private Integer maxParts;
        private String query;
    }
}
