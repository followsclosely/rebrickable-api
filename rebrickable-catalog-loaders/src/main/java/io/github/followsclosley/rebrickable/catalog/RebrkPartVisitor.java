package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkPart;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RebrkPartVisitor {

    private final CatalogContext context;

    public RebrkPart visit(RebrkPart part) {
        if (part.getCategoryId() != null) {
            part.setCategory(context.getCategory(part.getCategoryId()));
        }
        return part;
    }
}
