package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkInventoryMinifig;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkInventoryMinifigCatalogLoaderTest {

    @Test
    void loadFromCatalog() throws IOException {
        RebrkInventoryMinifigCatalogLoader loader = new RebrkInventoryMinifigCatalogLoader();
        try (Stream<RebrkInventoryMinifig> stream = loader.stream()) {
            Optional<RebrkInventoryMinifig> first = stream.findFirst();

            first.ifPresent(System.out::println);

            assertTrue(first.isPresent());
            assertEquals(3L, first.get().getId());
        }
        //Stop after reading just the first entry from the stream.
    }

    void loadAllFromCatalog() throws IOException {
        RebrkInventoryMinifigCatalogLoader loader = new RebrkInventoryMinifigCatalogLoader();
        try (Stream<RebrkInventoryMinifig> stream = loader.stream()) {
            stream.forEach(System.out::println);
        }
    }
}