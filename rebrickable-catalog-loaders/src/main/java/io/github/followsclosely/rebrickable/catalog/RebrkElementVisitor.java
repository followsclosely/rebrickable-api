package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkElement;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RebrkElementVisitor {
    private final CatalogContext context;

    public RebrkElement visit(RebrkElement element) {
        if (element.getColorId() != null) {
            element.setColor(context.getColor(element.getColorId()));
        }
        if (element.getPartId() != null) {
            //todo: I have no parts cache
        }
        return element;
    }
}
