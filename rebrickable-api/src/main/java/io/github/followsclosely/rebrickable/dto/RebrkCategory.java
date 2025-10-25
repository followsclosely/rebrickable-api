package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a category in the Rebrickable database.
 * Contains metadata such as ID and name.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrkCategory {
    /**
     * The unique identifier of the category.
     */
    private Long id;
    /**
     * The name of the category.
     */
    private String name;
}
