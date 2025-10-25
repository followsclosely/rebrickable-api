package io.github.followsclosely.rebrickable.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Generic response wrapper for paginated results from the Rebrickable API.
 * Contains metadata about pagination and the actual results.
 *
 * @param <E> the type of result contained in the response
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrkResponse<E> {
    /**
     * The total number of results available.
     */
    private Integer count;
    /**
     * The URL to the next page of results, if any.
     */
    private String next;
    /**
     * The URL to the previous page of results, if any.
     */
    private String previous;
    /**
     * The list of results for the current page.
     */
    private List<E> results;
    /**
     * Constructs a response with the given results.
     *
     * @param results the list of results
     */
    public RebrkResponse(List<E> results) {
        this.results = results;
    }
}
