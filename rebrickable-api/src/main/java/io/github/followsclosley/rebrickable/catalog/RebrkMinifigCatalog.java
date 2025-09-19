package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkMinifig;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of {@link RebrkMinifig} objects.
 * Implementations of this interface allow streaming all available minifigs from the catalog.
 */
public interface RebrkMinifigCatalog {
    /**
     * Returns a stream of {@link RebrkMinifig} objects from the catalog.
     *
     * @return a {@code Stream} of {@code RebrkMinifig} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<RebrkMinifig> stream() throws IOException;
}