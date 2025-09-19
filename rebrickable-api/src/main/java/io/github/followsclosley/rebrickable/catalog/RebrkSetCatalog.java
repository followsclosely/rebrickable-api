package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkSet;

import java.io.IOException;
import java.util.stream.Stream;

public interface RebrkSetCatalog {
    public Stream<RebrkSet> streamFromCatalog() throws IOException;
}
