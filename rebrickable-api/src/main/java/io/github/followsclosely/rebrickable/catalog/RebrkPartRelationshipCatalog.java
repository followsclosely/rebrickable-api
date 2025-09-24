package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkPartRelationship;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of RebrkPartRelationship objects.
 * Implementations of this interface allow streaming all available colors from the catalog.
 */
public interface RebrkPartRelationshipCatalog {
    /**
     * Returns a stream of {@link RebrkPartRelationship} objects from the catalog.
     *
     * @return a {@code Stream} of {@code RebrkPartRelationship} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<RebrkPartRelationship> stream() throws IOException;
}
