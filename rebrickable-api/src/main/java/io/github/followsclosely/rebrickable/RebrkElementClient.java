package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkElement;

import java.util.Collection;

public interface RebrkElementClient {
    RebrkElement getElement(String number);

    Collection<RebrkElement> getElementsFromMinifig(String id);
}
