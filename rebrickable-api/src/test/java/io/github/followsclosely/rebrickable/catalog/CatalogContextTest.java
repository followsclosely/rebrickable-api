package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkColor;
import io.github.followsclosely.rebrickable.dto.RebrkTheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CatalogContextTest {

    private static CatalogContext context;

    @BeforeAll
    static void setup() {
        context = new CatalogContext()
        .add(RebrkColor.builder().id(1L).name("Color 1").build())
        .add(RebrkColor.builder().id(2L).name("Color 2").build())
        .add(RebrkTheme.builder().id(1L).name("Theme 1").build());
    }


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