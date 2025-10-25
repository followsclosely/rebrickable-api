package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

/**
 * Represents a LEGO part in the Rebrickable database.
 * Contains metadata, external IDs, and references to categories.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"id", "name"})
public class RebrkPart {
    /**
     * External IDs for the part from other databases.
     */
    @JsonProperty("external_ids")
    Map<String, String[]> externalIds;
    /**
     * The part number (unique identifier).
     */
    @JsonProperty("part_num")
    private String id;
    /**
     * The name of the part.
     */
    private String name;
    /**
     * The category ID for the part.
     */
    @JsonProperty("part_cat_id")
    private Long categoryId;
    /**
     * The material of the part.
     */
    @JsonProperty("part_material")
    private String material;
    /**
     * The year the part was first released.
     */
    @JsonProperty("year_from")
    private Integer yearFrom;
    /**
     * The year the part was last released.
     */
    @JsonProperty("year_to")
    private Integer yearTo;
    /**
     * The image URL for the part.
     */
    @JsonProperty("part_img_url")
    private String imageUrl;
    /**
     * The URL to the part's page.
     */
    @JsonProperty("part_url")
    private String url;
    /**
     * Array of print variants for the part.
     */
    private String[] prints;
    /**
     * Array of mold variants for the part.
     */
    private String[] molds;
    /**
     * Array of alternate part numbers.
     */
    private String[] alternates;
    /**
     * The print variant this part is based on, if any.
     */
    @JsonProperty("print_of")
    private String print;

    /**
     * The category object, if loaded.
     */
    @JsonIgnore
    private RebrkCategory category;
}
