package io.github.followsclosley.rebrickable;

import io.github.followsclosley.rebrickable.dto.RebrkColor;

import java.util.Collection;

public interface RebrkColorClient {
    RebrkColor getColor(Long id);

    Collection<RebrkColor> getColors();
}
