package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a relationship between LEGO parts in the Rebrickable database.
 * Includes parent and child part identifiers and the type of relationship.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrkPartRelationship {
    /**
     * The type of relationship (e.g., mold, alternate, print).
     */
    @JsonProperty("theme_id")
    private String type;
    /**
     * The child part identifier.
     */
    @JsonProperty("theme_id")
    private String childId;
    /**
     * The parent part identifier.
     */
    @JsonProperty("parent_part_num")
    private String parentId;
}
