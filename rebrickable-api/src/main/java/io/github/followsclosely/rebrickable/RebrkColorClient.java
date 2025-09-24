package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkColor;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import lombok.Builder;
import lombok.Data;

/**
 * Client interface for interacting with Rebrickable's color-related endpoints.
 */
public interface RebrkColorClient {
    /**
     * Retrieves a color by its ID.
     *
     * @param id The ID of the color to retrieve.
     * @return The RebrkColor object corresponding to the given ID.
     */
    RebrkColor getColor(Long id);

    /**
     * Retrieves a paginated list of colors.
     *
     * @return A RebrkResponse containing a list of RebrkColor objects.
     */
    RebrkResponse<RebrkColor> getColors();
    /**
     * Retrieves a paginated list of colors based on the provided query parameters.
     *
     * @param query The query parameters for pagination and ordering.
     * @return A RebrkResponse containing a list of RebrkColor objects.
     */
    RebrkResponse<RebrkColor> getColors(Query query);

    @Data
    @Builder
    class Query {
        private Integer page;
        private Integer pageSize;
        private String ordering;
    }
}
