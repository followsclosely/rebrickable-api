package io.github.followsclosley.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RebrkExternalIds {
    @JsonProperty("ext_ids")
    private Integer[] ids;
    @JsonProperty("ext_descrs")
    private String[][] names;
}
