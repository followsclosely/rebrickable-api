package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkMinifig;

import java.io.IOException;
import java.util.stream.Stream;

public interface RebrkMinifigCatalog {
    public Stream<RebrkMinifig> streamFromCatalog() throws IOException;
}
