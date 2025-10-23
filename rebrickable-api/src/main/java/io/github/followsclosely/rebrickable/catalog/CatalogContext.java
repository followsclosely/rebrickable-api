package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkCategory;
import io.github.followsclosely.rebrickable.dto.RebrkColor;
import io.github.followsclosely.rebrickable.dto.RebrkTheme;

import java.util.HashMap;
import java.util.Map;


public class CatalogContext {
    private final Map<Long, RebrkColor> colorsById = new HashMap<>();
    private final Map<Long, RebrkTheme> themesById = new HashMap<>();
    private final Map<Long, RebrkCategory> categoriesById = new HashMap<>();

    public CatalogContext add(RebrkColor color) {
        if (color != null) {
            this.colorsById.put(color.getId(), color);
        }
        return this;
    }

    public CatalogContext add(RebrkTheme theme) {
        if (theme != null) {
            this.themesById.put(theme.getId(), theme);
        }
        return this;
    }

    public CatalogContext add(RebrkCategory category) {
        if (category != null) {
            this.categoriesById.put(category.getId(), category);
        }
        return this;
    }

    public RebrkColor getColor(Long id) {
        return colorsById.get(id);
    }

    public RebrkTheme getTheme(Long id) {
        return themesById.get(id);
    }

    public RebrkCategory getCategory(Long id) {
        return categoriesById.get(id);
    }
}