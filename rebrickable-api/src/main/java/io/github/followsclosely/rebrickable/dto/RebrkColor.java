package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Represents a color in the Rebrickable database.
 * Contains metadata and external IDs for the color.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrkColor {
    /**
     * External IDs for the color from other databases.
     */
    @JsonProperty("external_ids")
    Map<String, RebrkExternalIds> externalIds;
    /**
     * The unique identifier of the color.
     */
    private Long id;
    /**
     * The name of the color.
     */
    private String name;
    /**
     * The RGB value of the color.
     */
    private String rgb;
    /**
     * Indicates if the color is transparent.
     */
    @JsonProperty("is_trans")
    private Boolean transparent;
}
