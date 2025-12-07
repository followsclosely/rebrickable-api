package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

/**
 * Represents external IDs and descriptions for a LEGO part or color from other databases.
 * Used for mapping Rebrickable objects to external systems.
 */
@Data
@ToString(of = {"ids"})
public class RebrkExternalIds {
    /**
     * Array of external IDs for the object.
     */
    @JsonProperty("ext_ids")
    private Integer[] ids;
    /**
     * Array of external descriptions for the object, grouped by external system.
     */
    @JsonProperty("ext_descrs")
    private String[][] names;
}
