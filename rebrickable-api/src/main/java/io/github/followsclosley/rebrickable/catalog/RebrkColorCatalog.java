package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkColor;

import java.io.IOException;
import java.util.stream.Stream;

public interface RebrkColorCatalog {
    public Stream<RebrkColor> streamFromCatalog() throws IOException;
}
