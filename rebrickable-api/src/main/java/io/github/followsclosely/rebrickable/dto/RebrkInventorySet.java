package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * Represents an inventory entry for a LEGO set in the Rebrickable database.
 * Contains references to the set and quantity information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"id", "setId", "quantity"})
public class RebrkInventorySet {
    /**
     * The unique identifier of the inventory entry.
     */
    private Long id;
    /**
     * The set number associated with this inventory entry.
     */
    private String setId;
    /**
     * The quantity of the set in the inventory.
     */
    private Integer quantity;
    /**
     * The set object, if loaded.
     */
    private RebrkSet set;
}
