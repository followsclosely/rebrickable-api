package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Represents a LEGO element in the Rebrickable database.
 * Contains references to part, color, and design information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"id","partId","colorId"})
public class RebrkElement {
    /**
     * The unique identifier of the element.
     */
    @JsonProperty("element_id")
    private String id;
    /**
     * The part number associated with this element.
     */
    @JsonIgnore
    private String partId;
    /**
     * The color ID associated with this element.
     */
    @JsonIgnore
    private Long colorId;
    /**
     * The design ID for the element.
     */
    @JsonProperty("design_id")
    private String designId;
    /**
     * The part object, if loaded.
     */
    private RebrkPart part;
    /**
     * The color object, if loaded.
     */
    private RebrkColor color;

    /**
     * Returns the design ID, or null if empty.
     */
    public String getDesign() {
        return ("".equals(designId)) ? null : designId;
    }

}
