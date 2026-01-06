package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a theme in the Rebrickable database.
 * Themes can be nested, forming a hierarchy with parent and child themes.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"id", "name", "parentId"})
public class RebrkTheme {

    /**
     * The unique identifier of the theme.
     */
    private Long id;

    /**
     * The name of the theme.
     */
    private String name;

    /**
     * The ID of the parent theme, if any.
     */
    @JsonProperty("parent_id")
    private Long parentId;

    /**
     * The parent theme object, if loaded.
     */
    @JsonIgnore
    private RebrkTheme parent;

    /**
     * The child themes of this theme.
     */
    @JsonIgnore
    @Builder.Default
    private List<RebrkTheme> children = new ArrayList<>();
}
