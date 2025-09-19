package io.github.followsclosley.rebrickable.dto;

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
public class RebrkColor {
    @JsonProperty("external_ids")
    Map<String, RebrkExternalIds> externalIds;
    private Long id;
    private String name;
    private String rgb;
    @JsonProperty("is_trans")
    private Boolean transparent;
}
