package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Represents a LEGO minifig in the Rebrickable database.
 * Contains metadata and references to images and URLs.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"id", "name"})
public class RebrkMinifig {
    /**
     * The minifig number (unique identifier).
     */
    @JsonProperty("set_num")
    private String id;
    /**
     * The name of the minifig.
     */
    private String name;
    /**
     * The number of parts in the minifig.
     */
    @JsonProperty("num_parts")
    private int parts;
    /**
     * The image URL for the minifig.
     */
    @JsonProperty("set_img_url")
    private String imageUrl;
    /**
     * The URL to the minifig's page.
     */
    @JsonProperty("set_url")
    private String url;
}
