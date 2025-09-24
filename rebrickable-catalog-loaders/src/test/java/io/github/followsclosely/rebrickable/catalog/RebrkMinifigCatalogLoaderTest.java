package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkMinifig;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkMinifigCatalogLoaderTest {

    @Test
    void loadFromCatalog() throws IOException {
        RebrkMinifigCatalog loader = new RebrkMinifigCatalogLoader();
        try (Stream<RebrkMinifig> stream = loader.stream()) {
            Optional<RebrkMinifig> first = stream.findFirst();

            first.ifPresent(System.out::println);

            assertTrue(first.isPresent());
            assertEquals("fig-000001", first.get().getId());
        }
        //Stop after reading just the first entry from the stream.
    }
}