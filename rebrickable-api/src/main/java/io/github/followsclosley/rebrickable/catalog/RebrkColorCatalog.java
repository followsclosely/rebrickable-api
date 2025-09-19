package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkColor;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of RebrkColor objects.
 * Implementations of this interface allow streaming all available colors from the catalog.
 */
public interface RebrkColorCatalog {
    /**
     * Returns a stream of {@link RebrkColor} objects from the catalog.
     *
     * @return a {@code Stream} of {@code RebrkColor} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<RebrkColor> stream() throws IOException;
}
