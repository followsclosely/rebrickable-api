package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkColor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkColorCatalogLoaderTest {

    @Test
    void loadFromCatalog() throws IOException {
        RebrkColorCatalogLoader loader = new RebrkColorCatalogLoader();
        try (Stream<RebrkColor> stream = loader.stream()) {
            Optional<RebrkColor> first = stream.findFirst();

            first.ifPresent(System.out::println);

            assertTrue(first.isPresent());
            assertEquals(-1L, first.get().getId());
        }
        //Stop after reading just the first entry from the stream.
    }
}