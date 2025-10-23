package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkSet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkSetCatalogLoaderTest {

    @Test
    void loadFromCatalog() throws IOException {
        RebrkSetCatalogLoader loader = new RebrkSetCatalogLoader();
        try (Stream<RebrkSet> stream = loader.stream()) {
            Optional<RebrkSet> first = stream.findFirst();

            first.ifPresent(System.out::println);

            assertTrue(first.isPresent());
            assertEquals("0003977811-1", first.get().getNumber());
        }
        //Stop after reading just the first entry from the stream.
    }

    //@Test
    void loadAllFromCatalog() throws IOException {
        RebrkSetCatalogLoader loader = new RebrkSetCatalogLoader();
        for (Iterator<RebrkSet> it = loader.stream().iterator(); it.hasNext(); ) {
            RebrkSet set = it.next();
            System.out.println(set);
        }
    }
}