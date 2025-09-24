package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkInventoryMinifig;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of {@link RebrkInventoryMinifig} objects.
 * Implementations of this interface allow streaming all available themes from the catalog.
 */
public interface RebrkInventoryMinifigCatalog {
    /**
     * Returns a stream of {@link RebrkInventoryMinifig} objects from the catalog.
     *
     * @return a {@code Stream} of {@code RebrkInventoryMinifig} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<RebrkInventoryMinifig> stream() throws IOException;
}