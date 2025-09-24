package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkCategory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkCategoryCatalogLoaderTest {

    @Test
    void loadFromCatalog() throws IOException {
        RebrkCategoryCatalogLoader loader = new RebrkCategoryCatalogLoader();
        try (Stream<RebrkCategory> stream = loader.stream()) {
            Optional<RebrkCategory> first = stream.findFirst();

            first.ifPresent(System.out::println);

            assertTrue(first.isPresent());
            assertEquals(1L, first.get().getId());
            assertEquals("Baseplates", first.get().getName());
        }
        //Stop after reading just the first entry from the stream.
    }
}