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

    private static CatalogContext context;

    @BeforeAll
    public static void setup() throws IOException {
        RebrkElementCatalogLoaderTest.context = CatalogContext.builder()
                .colors(new RebrkColorCatalogLoader().stream().toList())
                .themes(new RebrkThemeCatalogLoader().stream().toList())
                .categories(new RebrkCategoryCatalogLoader().stream().toList())
                .build();
    }

    @Test
    void loadFromCatalog() throws IOException {
        RebrkElementCatalogLoader loader = new RebrkElementCatalogLoader(context);
        try (Stream<RebrkElement> stream = loader.stream()) {
            Optional<RebrkElement> first = stream.findFirst();

            first.ifPresent(System.out::println);

            assertTrue(first.isPresent());
            assertEquals("4190230", first.get().getId());
        }
        //Stop after reading just the first entry from the stream.
    }
}