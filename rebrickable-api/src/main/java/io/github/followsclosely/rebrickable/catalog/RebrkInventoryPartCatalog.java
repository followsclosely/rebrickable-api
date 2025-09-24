package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkInventoryPart;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of {@link RebrkInventoryPart} objects.
 * Implementations of this interface allow streaming all available themes from the catalog.
 */
public interface RebrkInventoryPartCatalog {
    /**
     * Returns a stream of {@link RebrkInventoryPart} objects from the catalog.
     *
     * @return a {@code Stream} of {@code RebrkInventoryPart} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<RebrkInventoryPart> stream() throws IOException;
}