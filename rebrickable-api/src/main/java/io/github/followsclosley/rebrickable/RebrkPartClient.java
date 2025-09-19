package io.github.followsclosley.rebrickable;

import io.github.followsclosley.rebrickable.dto.RebrkColorDetails;
import io.github.followsclosley.rebrickable.dto.RebrkPart;
import io.github.followsclosley.rebrickable.dto.RebrkResponse;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

public interface RebrkPartClient {
    RebrkPart getPart(String number);

    RebrkResponse<RebrkPart> getParts(Query query);

    Collection<RebrkColorDetails> getColorsOfPart(String partId);

    @Data
    @Builder
    class Query {
        private Integer page;
        private Integer pageSize;
        private String partNumber;
        private String[] partNumbers;
        private String categoryId;
        private String colorId;
        private String bricklinkId;
        private String brickowlId;
        private String legoId;
        private String ldrawId;
        private String query;
    }
}
