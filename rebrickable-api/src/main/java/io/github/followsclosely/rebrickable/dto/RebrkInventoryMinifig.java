package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an inventory entry for a LEGO minifig in the Rebrickable database.
 * Contains references to the minifig and quantity information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrkInventoryMinifig {
    /**
     * The unique identifier of the inventory entry.
     */
    private Long id;
    /**
     * The minifig number associated with this inventory entry.
     */
    @JsonProperty("set_num")
    private String minifigId;
    /**
     * The name of the minifig.
     */
    @JsonProperty("set_name")
    private String name;
    /**
     * The image URL for the minifig.
     */
    @JsonProperty("set_img_url")
    private String imageUrl;
    /**
     * The quantity of the minifig in the inventory.
     */
    private Integer quantity;


    /**
     * The minifig object, if loaded.
     */
    @JsonIgnore
    private RebrkMinifig minifig;
}
