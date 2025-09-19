package io.github.followsclosley.rebrickable;

import io.github.followsclosley.rebrickable.dto.RebrkMinifig;
import io.github.followsclosley.rebrickable.dto.RebrkSet;

import java.util.Collection;

public interface RebrkMinifigClient {
    RebrkMinifig getMinifig(String id);

    Collection<RebrkMinifig> getMinifigs();
}
