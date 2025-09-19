package io.github.followsclosley.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrkTheme {
    private Long id;
    private String name;
    @JsonProperty("parent_id")
    private Long parentId;

    @JsonIgnore
    private RebrkTheme parent;
    @JsonIgnore
    @Builder.Default
    private List<RebrkTheme> children = new ArrayList<>();
}
