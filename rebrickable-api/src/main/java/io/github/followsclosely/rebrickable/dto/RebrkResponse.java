package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrkResponse<E> {
    public RebrkResponse(List<E> results){
        this.results = results;
    }
    private Integer count;
    private String next;
    private String previous;
    private List<E> results;
}
