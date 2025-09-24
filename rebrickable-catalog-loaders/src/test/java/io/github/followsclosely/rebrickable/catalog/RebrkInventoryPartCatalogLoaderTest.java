package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkInventory;
import io.github.followsclosely.rebrickable.dto.RebrkInventoryPart;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkInventoryPartCatalogLoaderTest {

    @Test
    void loadFromCatalog() throws IOException {
        RebrkInventoryPartCatalogLoader loader = new RebrkInventoryPartCatalogLoader(null);
        try (Stream<RebrkInventoryPart> stream = loader.stream()) {
            Optional<RebrkInventoryPart> first = stream.findFirst();

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