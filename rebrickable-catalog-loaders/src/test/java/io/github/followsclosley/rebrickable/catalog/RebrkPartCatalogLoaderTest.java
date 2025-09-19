package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkPart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkPartCatalogLoaderTest {

    private static CatalogContext context;

    @BeforeAll
    public static void setup() throws IOException {
        RebrkPartCatalogLoaderTest.context = CatalogContext.builder()
                .colors(new RebrkColorCatalogLoader().stream().toList())
                .themes(new RebrkThemeCatalogLoader().stream().toList())
                .categories(new RebrkCategoryCatalogLoader().stream().toList())
                .build();
    }

    @Test
    void loadFromCatalog() throws IOException {
        RebrkPartCatalogLoader loader = new RebrkPartCatalogLoader(context);
        try (Stream<RebrkPart> stream = loader.stream()) {
            Optional<RebrkPart> first = stream.findFirst();

            first.ifPresent(System.out::println);

            assertTrue(first.isPresent());
            assertEquals("003381", first.get().getId());
        }
        //Stop after reading just the first entry from the stream.
    }
}