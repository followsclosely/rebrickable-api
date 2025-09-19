package io.github.followsclosley.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrkResponse<E> {
    private Integer count;
    private String next;
    private String previous;
    private List<E> results;
}
