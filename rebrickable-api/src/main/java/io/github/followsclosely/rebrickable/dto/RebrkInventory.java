package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * Represents an inventory record for a LEGO set in the Rebrickable database.
 * Contains metadata such as version and references to the set object.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"id","version","setId"})
public class RebrkInventory {
    /**
     * The unique identifier of the inventory record.
     */
    private Long id;
    /**
     * The version number of the inventory.
     */
    private Integer version;
    /**
     * The set number associated with this inventory.
     */
    private String setId;

    /**
     * The set object, if loaded.
     */
    @JsonIgnore
    private RebrkSet set;
}
