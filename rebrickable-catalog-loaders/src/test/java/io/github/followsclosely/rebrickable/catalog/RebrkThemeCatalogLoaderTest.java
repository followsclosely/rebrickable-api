package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkTheme;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkThemeCatalogLoaderTest {

    @Test
    void loadFromCatalog() throws IOException {
        RebrkThemeCatalogLoader loader = new RebrkThemeCatalogLoader();
        try (Stream<RebrkTheme> stream = loader.stream()) {
            Optional<RebrkTheme> first = stream.findFirst();

            first.ifPresent(System.out::println);

            assertTrue(first.isPresent());
            //assertEquals(-1L, first.get().getId());
        }
        //Stop after reading just the first entry from the stream.
    }
}