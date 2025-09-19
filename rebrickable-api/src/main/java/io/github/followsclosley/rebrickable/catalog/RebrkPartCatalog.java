package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkPart;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of RebrkPart objects.
 * Implementations of this interface allow streaming all available parts from the catalog.
 */
public interface RebrkPartCatalog {
    /**
     * Returns a stream of {@link RebrkPart} objects from the catalog.
     *
     * @return a {@code Stream} of {@code RebrkPart} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<RebrkPart> stream() throws IOException;
}
