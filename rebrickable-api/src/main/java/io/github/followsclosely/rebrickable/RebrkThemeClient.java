package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkTheme;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Provides access to {@link RebrkTheme} objects from the Rebrickable API.
 * Implementations of this interface allow retrieval of themes by ID, as well as fetching collections of themes
 * with optional query parameters for pagination and ordering.
 */
public interface RebrkThemeClient {

    /**
     * Retrieves a {@link RebrkTheme} by its unique identifier.
     *
     * @param id the unique identifier of the theme
     * @return the {@code RebrkTheme} object corresponding to the given ID, or {@code null} if not found
     */
    RebrkTheme getTheme(Long id);

    /**
     * Retrieves a collection of all {@link RebrkTheme} objects without any query parameters.
     *
     * @return a {@code Collection} of all {@code RebrkTheme} objects
     */
    default Collection<RebrkTheme> getThemes(){
        return getThemes(null);
    }
    /**
     * Retrieves a collection of {@link RebrkTheme} objects based on the provided query parameters.
     *
     * @param query an optional {@link Query} object containing pagination and ordering parameters
     * @return a {@code Collection} of {@code RebrkTheme} objects matching the query criteria
     */
    Collection<RebrkTheme> getThemes(Query query);

    /**
     * Retrieves a hierarchical collection of {@link RebrkTheme} objects, organizing them into parent-child
     * relationships based on their parent IDs.
     * @return a {@code Collection} of top-level {@code RebrkTheme} objects, each containing their respective child themes
     */
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

    @Data
    @Builder
    class Query {
        private Integer page;
        private Integer pageSize;
        private String ordering;
    }
}
