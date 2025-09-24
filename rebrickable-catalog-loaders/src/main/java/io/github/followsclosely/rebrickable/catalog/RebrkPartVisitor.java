package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkPart;
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
