package io.github.followsclosley.rebrickable;

import io.github.followsclosley.rebrickable.dto.RebrkSet;

import java.util.Collection;

public interface RebrkSetClient {
    RebrkSet getSet(String number);

    Collection<RebrkSet> getSets();
}
