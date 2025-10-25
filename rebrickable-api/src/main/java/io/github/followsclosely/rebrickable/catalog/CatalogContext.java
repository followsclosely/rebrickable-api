package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkCategory;
import io.github.followsclosely.rebrickable.dto.RebrkColor;
import io.github.followsclosely.rebrickable.dto.RebrkTheme;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides a context for catalog lookups of colors, themes, and categories by their IDs.
 * This class allows adding and retrieving {@link RebrkColor}, {@link RebrkTheme}, and {@link RebrkCategory} objects.
 * It is useful for resolving references and caching catalog data for fast access.
 */
public class CatalogContext {
    /**
     * Map of color IDs to {@link RebrkColor} objects.
     */
    private final Map<Long, RebrkColor> colorsById = new HashMap<>();
    /**
     * Map of theme IDs to {@link RebrkTheme} objects.
     */
    private final Map<Long, RebrkTheme> themesById = new HashMap<>();
    /**
     * Map of category IDs to {@link RebrkCategory} objects.
     */
    private final Map<Long, RebrkCategory> categoriesById = new HashMap<>();

    /**
     * Adds a {@link RebrkColor} to the context.
     *
     * @param color the color to add
     * @return this context for chaining
     */
    public CatalogContext add(RebrkColor color) {
        if (color != null) {
            this.colorsById.put(color.getId(), color);
        }
        return this;
    }

    /**
     * Adds a {@link RebrkTheme} to the context.
     *
     * @param theme the theme to add
     * @return this context for chaining
     */
    public CatalogContext add(RebrkTheme theme) {
        if (theme != null) {
            this.themesById.put(theme.getId(), theme);
        }
        return this;
    }

    /**
     * Adds a {@link RebrkCategory} to the context.
     *
     * @param category the category to add
     * @return this context for chaining
     */
    public CatalogContext add(RebrkCategory category) {
        if (category != null) {
            this.categoriesById.put(category.getId(), category);
        }
        return this;
    }

    /**
     * Retrieves a {@link RebrkColor} by its ID.
     *
     * @param id the color ID
     * @return the color, or {@code null} if not found
     */
    public RebrkColor getColor(Long id) {
        return colorsById.get(id);
    }

    /**
     * Retrieves a {@link RebrkTheme} by its ID.
     *
     * @param id the theme ID
     * @return the theme, or {@code null} if not found
     */
    public RebrkTheme getTheme(Long id) {
        return themesById.get(id);
    }

    /**
     * Retrieves a {@link RebrkCategory} by its ID.
     *
     * @param id the category ID
     * @return the category, or {@code null} if not found
     */
    public RebrkCategory getCategory(Long id) {
        return categoriesById.get(id);
    }
}