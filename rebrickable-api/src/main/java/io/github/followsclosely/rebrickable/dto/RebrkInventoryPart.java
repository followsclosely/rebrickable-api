package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Represents an inventory entry for a LEGO part in the Rebrickable database.
 * Contains references to the part, color, and quantity information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"id", "partId", "colorId"})
public class RebrkInventoryPart {
    /**
     * The unique identifier of the inventory entry.
     */
    private Long id;
    /**
     * The part number associated with this inventory entry.
     */
    @JsonIgnore
    private String partId;
    /**
     * The color ID associated with this inventory entry.
     */
    @JsonIgnore
    private Long colorId;
    /**
     * The quantity of the part in the inventory.
     */
    private Integer quantity;
    /**
     * Indicates if the part is a spare.
     */
    @JsonProperty("is_spare")
    private Boolean isSpare;
    /**
     * The image URL for the part.
     */
    @JsonProperty("img_url")
    private String imageUrl;

    /**
     * The part object, if loaded.
     */
    private RebrkPart part;
    /**
     * The color object, if loaded.
     */
    private RebrkColor color;
}
