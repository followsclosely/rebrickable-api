package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkTheme;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Provides access to a catalog of {@link RebrkTheme} objects.
 * Implementations of this interface allow streaming all available themes from the catalog.
 */
public interface RebrkThemeCatalog {
    /**
     * Returns a stream of {@link RebrkTheme} objects from the catalog.
     *
     * @return a {@code Stream} of {@code RebrkTheme} objects
     * @throws IOException if an I/O error occurs while accessing the catalog
     */
    Stream<RebrkTheme> stream() throws IOException;
}