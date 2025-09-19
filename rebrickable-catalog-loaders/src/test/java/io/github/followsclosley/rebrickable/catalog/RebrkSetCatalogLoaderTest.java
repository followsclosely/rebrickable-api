package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkSetCatalogLoaderTest {

    private static CatalogContext context;

    @BeforeAll
    public static void setup() throws IOException {
        RebrkSetCatalogLoaderTest.context = CatalogContext.builder()
                .colors(new RebrkColorCatalogLoader().streamFromCatalog().toList())
                .themes(new RebrkThemeCatalogLoader().streamFromCatalog().toList())
                .build();
    }

    @Test
    void loadFromCatalog() throws IOException {
        RebrkSetCatalogLoader loader = new RebrkSetCatalogLoader(context);
        try (Stream<RebrkSet> stream = loader.streamFromCatalog()) {
            Optional<RebrkSet> first = stream.findFirst();

            assertTrue(first.isPresent());
            assertEquals("0003977811-1", first.get().getNumber());
        }
        //Stop after reading just the first entry from the stream.
    }

    //@Test
    void loadAllFromCatalog() throws IOException {
        RebrkSetCatalogLoader loader = new RebrkSetCatalogLoader(context);
        for (Iterator<RebrkSet> it = loader.streamFromCatalog().iterator(); it.hasNext(); ) {
            RebrkSet set = it.next();
            System.out.println(set);
        }
    }
}