package io.github.followsclosley.rebrickable.dto;

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
public class RebrkInventoryMinifig {
    private Long id;
    @JsonProperty("set_num")
    private String minifigId;
    @JsonProperty("set_name")
    private String name;
    @JsonProperty("set_img_url")
    private String imageUrl;
    private Integer quantity;


    @JsonIgnore
    private RebrkMinifig minifig;
}
