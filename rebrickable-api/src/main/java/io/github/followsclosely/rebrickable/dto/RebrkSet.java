package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * Represents a LEGO set in the Rebrickable database.
 * Contains metadata and references to parts and minifigs.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"number", "name"})
public class RebrkSet {
    /**
     * The set number (unique identifier).
     */
    @JsonProperty("set_num")
    private String number;
    /**
     * The name of the set.
     */
    private String name;
    /**
     * The release year of the set.
     */
    private Integer year;
    /**
     * The theme ID associated with the set.
     */
    @JsonProperty("theme_id")
    private Long themeId;
    /**
     * The number of parts in the set.
     */
    @JsonProperty("num_parts")
    private int numberOfParts;
    /**
     * The image URL for the set.
     */
    @JsonProperty("set_img_url")
    private String imageUrl;
    /**
     * The URL to the set's page.
     */
    @JsonProperty("set_url")
    private String url;
    /**
     * The last modified date/time of the set.
     */
    @JsonProperty("last_modified_dt")
    private String lastModified;


    /**
     * The theme object, if loaded.
     */
    @JsonIgnore
    private RebrkTheme theme;
    /**
     * The list of inventory parts in the set.
     */
    @JsonIgnore
    private List<RebrkInventoryPart> parts;
    /**
     * The list of minifigs in the set.
     */
    @JsonIgnore
    private List<RebrkInventoryMinifig> minifigs;
}
