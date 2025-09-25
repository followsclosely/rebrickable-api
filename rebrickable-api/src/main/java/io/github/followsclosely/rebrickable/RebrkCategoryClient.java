package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkCategory;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

/**
 * Client interface for interacting with Rebrickable categories.
 */
public interface RebrkCategoryClient {

    /**
     * Retrieves a category by its ID.
     *
     * @param id the ID of the category
     * @return the category with the specified ID
     */
    RebrkCategory getCategory(Long id);

    /**
     * Retrieves all categories.
     *
     * @return a collection of all categories
     */
    default Collection<RebrkCategory> getCategories() {
        return getCategories(null);
    }

    /**
     * Retrieves categories based on the provided query parameters.
     *
     * @param query the query parameters for filtering and pagination
     * @return a collection of categories matching the query parameters
     */
    Collection<RebrkCategory> getCategories(Query query);

    @Data
    @Builder
    class Query {
        private Integer page;
        private Integer pageSize;
        private String ordering;
    }
}
