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
public class RebrkSet {
    @JsonProperty("set_num")
    private String number;
    private String name;
    private Integer year;
    @JsonProperty("theme_id")
    private Long themeId;
    @JsonProperty("num_parts")
    private int parts;
    @JsonProperty("set_img_url")
    private String imageUrl;
    @JsonProperty("set_url")
    private String url;
    @JsonProperty("last_modified_dt")
    private String lastModified;

    private RebrkTheme theme;
}
