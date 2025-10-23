package io.github.followsclosely.rebrickable.catalog.visitor;

import io.github.followsclosely.rebrickable.catalog.CatalogContext;
import io.github.followsclosely.rebrickable.dto.RebrkElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RebrkElementVisitor {
    private final CatalogContext context;
    @Getter
    private long visitCount = 0;

    public RebrkElement visit(RebrkElement element) {
        this.visitCount++;
        if (element.getColorId() != null) {
            element.setColor(context.getColor(element.getColorId()));
        }
        return element;
    }
}
