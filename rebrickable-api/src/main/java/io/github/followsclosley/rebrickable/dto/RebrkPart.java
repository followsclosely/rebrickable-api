package io.github.followsclosley.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrkPart {
    @JsonProperty("external_ids")
    Map<String, String[]> externalIds;
    @JsonProperty("part_num")
    private String id;
    private String name;
    @JsonProperty("part_cat_id")
    private Long categoryId;
    @JsonProperty("part_material")
    private String material;
    @JsonProperty("year_from")
    private Integer yearFrom;
    @JsonProperty("year_to")
    private Integer yearTo;
    @JsonProperty("part_img_url")
    private String imageUrl;
    @JsonProperty("part_url")
    private String url;
    private String[] prints;
    private String[] molds;
    private String[] alternates;
    @JsonProperty("print_of")
    private String print;

    @JsonIgnore
    private RebrkCategory category;
}
