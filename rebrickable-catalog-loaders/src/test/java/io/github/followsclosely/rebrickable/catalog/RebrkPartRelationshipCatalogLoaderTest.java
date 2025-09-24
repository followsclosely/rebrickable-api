package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkPartRelationship;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RebrkPartRelationshipCatalogLoaderTest {


    @Test
    void loadFromCatalog() throws IOException {
        RebrkPartRelationshipCatalogLoader loader = new RebrkPartRelationshipCatalogLoader();
        try (Stream<RebrkPartRelationship> stream = loader.stream()) {
            Optional<RebrkPartRelationship> first = stream.findFirst();
            first.ifPresent(System.out::println);
            assertTrue(first.isPresent());
        }
        //Stop after reading just the first entry from the stream.
    }
}