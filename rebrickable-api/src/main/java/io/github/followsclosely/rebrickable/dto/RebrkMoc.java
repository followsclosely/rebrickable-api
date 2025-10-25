package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Represents a LEGO MOC in the Rebrickable database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"number", "name", "designer"})
public class RebrkMoc {
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
    @JsonProperty("moc_img_url")
    private String imageUrl;
    /**
     * The URL to the set's page.
     */
    @JsonProperty("moc_url")
    private String url;

    @JsonProperty("designer_name")
    private String designer;

    @JsonProperty("designer_url")
    private String designerUrl;
}
