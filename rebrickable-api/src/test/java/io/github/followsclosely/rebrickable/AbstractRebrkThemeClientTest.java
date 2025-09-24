package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkTheme;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractRebrkThemeClientTest {

    private final RebrkThemeClient rebrkThemeClient = new RebrkThemeClient() {

        private final Map<Long, RebrkTheme> immutableMap = Map.of(
                1L, RebrkTheme.builder().id(1L).name("Stink").build(),
                2L, RebrkTheme.builder().id(2L).name("Stink Child #1").parentId(1L).build(),
                3L, RebrkTheme.builder().id(3L).name("Stink Child #2").parentId(1L).build(),
                4L, RebrkTheme.builder().id(4L).name("Poop").build(),
                5L, RebrkTheme.builder().id(5L).name("Fart").build()
        );

        public RebrkTheme getTheme(Long id) {
            return immutableMap.get(id);
        }

        public Collection<RebrkTheme> getThemes(Query query) {
            return immutableMap.values();
        }
    };

    @Test
    void getThemesHierarchical() {
        Collection<RebrkTheme> themes = rebrkThemeClient.getThemesHierarchical();
        Map<Long, RebrkTheme> themesById = themes.stream().collect(Collectors.toMap(RebrkTheme::getId, Function.identity()));

        assertEquals(3, themes.size());

        assertEquals(2, themesById.get(1L).getChildren().size());
        assertEquals(0, themesById.get(4L).getChildren().size());
        assertEquals(0, themesById.get(5L).getChildren().size());
    }
}