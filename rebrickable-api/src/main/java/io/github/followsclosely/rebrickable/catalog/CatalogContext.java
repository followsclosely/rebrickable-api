package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkCategory;
import io.github.followsclosely.rebrickable.dto.RebrkColor;
import io.github.followsclosely.rebrickable.dto.RebrkTheme;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Provides a context for accessing catalogs of {@link RebrkColor} and {@link RebrkTheme} objects.
 * Stores mappings from IDs to their corresponding catalog objects for efficient lookup.
 * Use the {@link Builder} to construct instances of this class.
 */
public class CatalogContext {

    /**
     * Maps color IDs to their corresponding {@link RebrkColor} objects.
     */
    private final Map<Long, RebrkColor> colorsById;

    /**
     * Maps theme IDs to their corresponding {@link RebrkTheme} objects.
     */
    private final Map<Long, RebrkTheme> themesById;

    /**
     * Maps category IDs to their corresponding {@link RebrkCategory} objects.
     */
    private final Map<Long, RebrkCategory> categoriesById;

    /**
     * Constructs a {@code CatalogContext} using the provided {@link Builder}.
     *
     * @param builder the builder containing color and theme mappings
     */
    private CatalogContext(Builder builder) {
        this.colorsById = (builder.colorsById != null && !builder.colorsById.isEmpty()) ? builder.colorsById : new HashMap<>();
        this.themesById = (builder.themesById != null && !builder.themesById.isEmpty()) ? builder.themesById : new HashMap<>();
        this.categoriesById = (builder.categoriesById != null && !builder.categoriesById.isEmpty()) ? builder.categoriesById : new HashMap<>();
    }

    /**
     * Creates a new {@link Builder} for constructing a {@code CatalogContext}.
     *
     * @return a new {@code Builder} instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Retrieves a {@link RebrkColor} by its ID.
     *
     * @param id the ID of the color
     * @return the {@code RebrkColor} with the specified ID, or {@code null} if not found
     */
    public RebrkColor getColor(Long id) {
        return colorsById.get(id);
    }

    /**
     * Retrieves a {@link RebrkTheme} by its ID.
     *
     * @param id the ID of the theme
     * @return the {@code RebrkTheme} with the specified ID, or {@code null} if not found
     */
    public RebrkTheme getTheme(Long id) {
        return themesById.get(id);
    }

    public RebrkCategory getCategory(Long id) {
        return categoriesById.get(id);
    }

    /**
     * Builder for {@link CatalogContext}.
     * Allows setting collections of colors and themes to build the context.
     */
    public static class Builder {
        Map<Long, RebrkColor> colorsById;
        Map<Long, RebrkTheme> themesById;
        Map<Long, RebrkCategory> categoriesById;

        /**
         * Sets the collection of {@link RebrkColor} objects for the context.
         *
         * @param colors the collection of colors
         * @return this builder instance
         */
        public Builder colors(Collection<RebrkColor> colors) {
            this.colorsById = colors.stream().collect(Collectors.toMap(RebrkColor::getId, Function.identity()));
            return this;
        }

        /**
         * Sets the collection of {@link RebrkTheme} objects for the context.
         *
         * @param themes the collection of themes
         * @return this builder instance
         */
        public Builder themes(Collection<RebrkTheme> themes) {
            this.themesById = themes.stream().collect(Collectors.toMap(RebrkTheme::getId, Function.identity()));
            return this;
        }

        public Builder categories(Collection<RebrkCategory> categories) {
            this.categoriesById = categories.stream().collect(Collectors.toMap(RebrkCategory::getId, Function.identity()));
            return this;
        }

        /**
         * Builds a new {@link CatalogContext} instance using the provided colors and themes.
         *
         * @return a new {@code CatalogContext}
         */
        public CatalogContext build() {
            return new CatalogContext(this);
        }
    }

}