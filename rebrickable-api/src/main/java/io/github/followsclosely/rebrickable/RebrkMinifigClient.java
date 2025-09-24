package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkMinifig;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import lombok.Builder;
import lombok.Data;

/**
 * Client interface for interacting with the Rebrickable Minifig API.
 */
public interface RebrkMinifigClient {

    /**
     * Retrieves a minifig by its unique identifier.
     *
     * @param id The unique identifier of the minifig.
     * @return The minifig corresponding to the provided ID.
     */
    RebrkMinifig getMinifig(String id);

    /**
     * Retrieves a list of minifigs based on the provided query parameters.
     *
     * @param query The query parameters for filtering and pagination.
     * @return A response containing a list of minifigs matching the query.
     */
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
