package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkColor;
import io.github.followsclosley.rebrickable.dto.RebrkMinifig;
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
        try (Stream<RebrkMinifig> stream = loader.streamFromCatalog()) {
            Optional<RebrkMinifig> first = stream.findFirst();

            assertTrue(first.isPresent());
            assertEquals("fig-000001", first.get().getId());
            System.out.println(first.get());
        }
        //Stop after reading just the first entry from the stream.
    }
}