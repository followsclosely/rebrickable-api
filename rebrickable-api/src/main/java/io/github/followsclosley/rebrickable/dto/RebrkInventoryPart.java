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
public class RebrkInventoryPart {
    private Long id;
    @JsonIgnore
    private String partId;
    @JsonIgnore
    private Long colorId;
    private Integer quantity;
    @JsonProperty("is_spare")
    private Boolean isSpare;
    @JsonProperty("img_url")
    private String imageUrl;


    private RebrkPart part;
    private RebrkColor color;
}
