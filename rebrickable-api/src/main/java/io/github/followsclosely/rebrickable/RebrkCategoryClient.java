package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.dto.RebrkCategory;

import java.util.Collection;

public interface RebrkCategoryClient {
    RebrkCategory getCategory(Long id);

    Collection<RebrkCategory> getCategories();
}
