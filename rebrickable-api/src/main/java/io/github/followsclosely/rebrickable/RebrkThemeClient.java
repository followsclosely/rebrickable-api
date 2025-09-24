package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkTheme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface RebrkThemeClient {

    RebrkTheme getTheme(Long id);

    Collection<RebrkTheme> getThemes();

    default Collection<RebrkTheme> getThemesHierarchical() {

        List<RebrkTheme> results = new ArrayList<>();

        Collection<RebrkTheme> themes = getThemes();
        Map<Long, RebrkTheme> themesById = getThemes().stream().collect(Collectors.toMap(RebrkTheme::getId, Function.identity()));

        for (RebrkTheme theme : themes) {
            if (theme.getParentId() != null) {
                RebrkTheme parent = themesById.get(theme.getParentId());
                if (parent != null) {
                    parent.getChildren().add(theme);
                }
            } else {
                results.add(theme);
            }
        }

        return results;
    }
}
