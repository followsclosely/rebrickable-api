package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkInventory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkInventoryCatalogLoaderTest {

    @Test
    void loadFromCatalog() throws IOException {
        RebrkInventoryCatalogLoader loader = new RebrkInventoryCatalogLoader(null);
        try (Stream<RebrkInventory> stream = loader.stream()) {
            Optional<RebrkInventory> first = stream.findFirst();

            first.ifPresent(System.out::println);

            assertTrue(first.isPresent());
            assertEquals(1L, first.get().getId());
        }
        //Stop after reading just the first entry from the stream.
    }

    void loadAllFromCatalog() throws IOException {
        RebrkInventoryCatalogLoader loader = new RebrkInventoryCatalogLoader(null);
        try (Stream<RebrkInventory> stream = loader.stream()) {
            stream.forEach(System.out::println);
        }
    }
}