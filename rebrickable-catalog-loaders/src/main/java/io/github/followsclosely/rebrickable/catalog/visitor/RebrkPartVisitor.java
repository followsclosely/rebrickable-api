package io.github.followsclosely.rebrickable.catalog.visitor;

import io.github.followsclosely.rebrickable.catalog.CatalogContext;
import io.github.followsclosely.rebrickable.dto.RebrkPart;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RebrkPartVisitor {
    @Getter
    private long visitCount = 0;
    private final CatalogContext context;

    public RebrkPart visit(RebrkPart part) {
        visitCount++;
        if (part.getCategoryId() != null) {
            part.setCategory(context.getCategory(part.getCategoryId()));
        }
        return part;
    }
}
