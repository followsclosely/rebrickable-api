package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkColor;

import java.util.Collection;

public interface RebrkColorClient {
    RebrkColor getColor(Long id);

    Collection<RebrkColor> getColors();
}
