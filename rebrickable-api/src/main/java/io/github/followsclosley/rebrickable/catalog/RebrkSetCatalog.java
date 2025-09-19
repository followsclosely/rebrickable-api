package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkSet;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of {@link RebrkSet} objects.
 * Implementations of this interface allow streaming all available sets from the catalog.
 */
public interface RebrkSetCatalog {
    /**
     * Returns a stream of {@link RebrkSet} objects from the catalog.
     *
     * @return a {@code Stream} of {@code RebrkSet} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<RebrkSet> stream() throws IOException;
}