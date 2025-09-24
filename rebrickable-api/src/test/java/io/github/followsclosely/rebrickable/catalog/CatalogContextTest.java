package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkColor;
import io.github.followsclosely.rebrickable.dto.RebrkTheme;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CatalogContextTest {

    private final CatalogContext context = CatalogContext.builder()
            .colors(List.of(RebrkColor.builder().id(1L).name("Color 1").build()))
            .themes(List.of(RebrkTheme.builder().id(1L).name("Theme 1").build()))
            .build();

    @Test
    void getColor() {
        RebrkColor color = context.getColor(1L);

        assertNotNull(color);
        assertEquals("Color 1", color.getName());
    }

    @Test
    void getTheme() {
        RebrkTheme theme = context.getTheme(1L);

        assertNotNull(theme);
        assertEquals("Theme 1", theme.getName());
    }
}