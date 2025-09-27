package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkElementCatalogLoaderTest {

    @Test
    void loadFromCatalog() throws IOException {
        RebrkElementCatalogLoader loader = new RebrkElementCatalogLoader();
        try (Stream<RebrkElement> stream = loader.stream()) {
            Optional<RebrkElement> first = stream.findFirst();

            first.ifPresent(System.out::println);

            assertTrue(first.isPresent());
            assertEquals("4190230", first.get().getId());
        }
        //Stop after reading just the first entry from the stream.
    }
}