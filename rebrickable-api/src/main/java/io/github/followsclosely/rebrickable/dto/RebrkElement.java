package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrkElement {

    @JsonProperty("element_id")
    private String id;
    @JsonIgnore
    private String partId;
    @JsonIgnore
    private Long colorId;

    @JsonProperty("design_id")
    private String designId;
    public String getDesign() {
        return ("".equals(designId)) ? null : designId;
    }

    private RebrkPart part;
    private RebrkColor color;

}
