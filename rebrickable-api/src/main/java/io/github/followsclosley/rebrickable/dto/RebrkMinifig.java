package io.github.followsclosley.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrkMinifig {
    private String id;
    private String name;
    @JsonProperty("num_parts")
    private int parts;
    @JsonProperty("set_img_url")
    private String imageUrl;
    @JsonProperty("set_url")
    private String url;
}
