package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import io.github.followsclosely.rebrickable.dto.RebrkSet;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

/**
 * Client interface for interacting with Rebrickable sets.
 */
public interface RebrkSetClient {

    /**
     * Retrieves a set by its number.
     *
     * @param number the set number
     * @return the corresponding {@link RebrkSet}
     */
    RebrkSet getSet(String number);

    /**
     * Retrieves a set by its number, with options to load parts and minifigs.
     *
     * @param number       the set number
     * @param loadParts    whether to load parts
     * @param loadMinifigs whether to load minifigs
     * @return the corresponding {@link RebrkSet}
     */
    RebrkSet getSet(String number, boolean loadParts, boolean loadMinifigs);

    /**
     * Retrieves sets based on the provided query parameters.
     *
     * @param query the query parameters
     * @return a {@link RebrkResponse} containing the sets
     */
    RebrkResponse<RebrkSet> getSets(Query query);

    /**
     * Retrieves sets that contain a specific minifig.
     *
     * @param number the minifig number
     * @return a collection of {@link RebrkSet} containing the minifig
     */
    Collection<RebrkSet> getSetsThatContainMinifig(String number);

    RebrkResponse<RebrkSet> getSetsThatContainPartAndColor(String partId, String colorId, SimpleQuery query);

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

    @Data
    @Builder
    class SimpleQuery {
        private Integer page;
        private Integer pageSize;
        private String ordering;
    }
}
