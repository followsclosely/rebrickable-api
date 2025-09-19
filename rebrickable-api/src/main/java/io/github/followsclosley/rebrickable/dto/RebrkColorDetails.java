package io.github.followsclosley.rebrickable.dto;

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
public class RebrkColorDetails {
    @JsonProperty("color_id")
    private Long id;
    @JsonProperty("color_name")
    private String name;
    @JsonProperty("num_sets")
    private int numberOfSets;
    //"num_set_parts": 3,
    @JsonProperty("part_img_url")
    private String imageUrl;
    private String[] elements;
}
