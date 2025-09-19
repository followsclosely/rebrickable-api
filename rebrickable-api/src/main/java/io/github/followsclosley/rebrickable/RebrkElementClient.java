package io.github.followsclosley.rebrickable;

import io.github.followsclosley.rebrickable.dto.RebrkElement;

import java.util.Collection;

public interface RebrkElementClient {
    RebrkElement getElement(String number);

    Collection<RebrkElement> getElementsFromMinifig(String id);
}
