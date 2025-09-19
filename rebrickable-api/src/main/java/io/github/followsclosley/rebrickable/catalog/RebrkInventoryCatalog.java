package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkInventory;
import io.github.followsclosley.rebrickable.dto.RebrkTheme;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of {@link RebrkTheme} objects.
 * Implementations of this interface allow streaming all available themes from the catalog.
 */
public interface RebrkInventoryCatalog {
    /**
     * Returns a stream of {@link RebrkInventory} objects from the catalog.
     *
     * @return a {@code Stream} of {@code RebrkInventory} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<RebrkInventory> stream() throws IOException;
}