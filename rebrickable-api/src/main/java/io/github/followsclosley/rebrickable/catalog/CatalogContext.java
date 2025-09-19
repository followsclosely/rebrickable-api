package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkColor;
import io.github.followsclosley.rebrickable.dto.RebrkTheme;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CatalogContext {

    private final Map<Long, RebrkColor> colorsById;
    private final Map<Long, RebrkTheme> themesById;

    private CatalogContext(Builder builder) {
        this.colorsById = (builder.colorsById != null && !builder.colorsById.isEmpty()) ? builder.colorsById : new HashMap<>();
        this.themesById = (builder.themesById != null && !builder.themesById.isEmpty()) ? builder.themesById : new HashMap<>();
    }

    public static Builder builder() {
        return new Builder();
    }

    public RebrkColor getColor(Long id) {
        return colorsById.get(id);
    }

    public RebrkTheme getTheme(Long id) {
        return themesById.get(id);
    }

    public static class Builder {
        Map<Long, RebrkColor> colorsById;
        Map<Long, RebrkTheme> themesById;

        public Builder colors(Collection<RebrkColor> colors) {
            this.colorsById = colors.stream().collect(Collectors.toMap(RebrkColor::getId, Function.identity()));
            return this;
        }

        public Builder themes(Collection<RebrkTheme> themes) {
            this.themesById = themes.stream().collect(Collectors.toMap(RebrkTheme::getId, Function.identity()));
            return this;
        }

        public CatalogContext build() {
            return new CatalogContext(this);
        }
    }

}
