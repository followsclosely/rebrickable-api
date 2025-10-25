package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Represents detailed color information for a LEGO part in the Rebrickable database.
 * Includes color metadata, image URL, and associated elements.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"id", "name"})
public class RebrkColorDetails {
    /**
     * The unique identifier of the color.
     */
    @JsonProperty("color_id")
    private Long id;
    /**
     * The name of the color.
     */
    @JsonProperty("color_name")
    private String name;
    /**
     * The number of sets containing this color.
     */
    @JsonProperty("num_sets")
    private int numberOfSets;
    /**
     * The image URL for the color part.
     */
    @JsonProperty("part_img_url")
    private String imageUrl;
    /**
     * Array of element IDs associated with this color.
     */
    private String[] elements;
}
