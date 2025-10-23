package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkInventorySet;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of {@link RebrkInventorySet} objects.
 * Implementations of this interface allow streaming all available themes from the catalog.
 */
public interface RebrkInventorySetCatalog {
    /**
     * Returns a stream of {@link RebrkInventorySet} objects from the catalog.
     *
     * @return a {@code Stream} of {@code RebrkInventorySet} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<RebrkInventorySet> stream() throws IOException;
}