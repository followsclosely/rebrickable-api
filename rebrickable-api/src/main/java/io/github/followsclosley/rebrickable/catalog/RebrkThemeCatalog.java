package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkTheme;

import java.io.IOException;
import java.util.stream.Stream;

public interface RebrkThemeCatalog {
    public Stream<RebrkTheme> streamFromCatalog() throws IOException;
}
