package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkColorDetails;
import io.github.followsclosely.rebrickable.dto.RebrkPart;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

/**
 * Client interface for interacting with Rebrickable's part-related endpoints.
 */
public interface RebrkPartClient {

    /**
     * Retrieves a part by its part number.
     *
     * @param number the part number
     * @return the part corresponding to the given number
     */
    RebrkPart getPart(String number);

    /**
     * Retrieves parts based on the provided query parameters.
     *
     * @param query the query parameters for filtering parts
     * @return a response containing a collection of parts matching the query
     */
    RebrkResponse<RebrkPart> getParts(Query query);

    /**
     * Retrieves color details associated with a specific part.
     *
     * @param partId the ID of the part
     * @return a collection of color details for the specified part
     */
    Collection<RebrkColorDetails> getColorsOfPart(String partId);

    @Data
    @Builder
    class Query {
        private Integer page;
        private Integer pageSize;
        private String partNumber;
        private String[] partNumbers;
        private String categoryId;
        private String colorId;
        private String bricklinkId;
        private String brickowlId;
        private String legoId;
        private String ldrawId;
        private String query;
        @Builder.Default
        private Boolean includePartDetails = Boolean.TRUE;
    }
}
