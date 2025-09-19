package io.github.followsclosley.rebrickable;

import io.github.followsclosley.rebrickable.dto.RebrkMinifig;

import java.util.Collection;

public interface RebrkMinifigClient {
    RebrkMinifig getMinifig(String id);

    Collection<RebrkMinifig> getMinifigs();
}
