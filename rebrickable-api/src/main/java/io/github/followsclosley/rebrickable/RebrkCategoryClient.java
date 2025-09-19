package io.github.followsclosley.rebrickable;

import io.github.followsclosley.rebrickable.dto.RebrkCategory;

import java.util.Collection;

public interface RebrkCategoryClient {
    RebrkCategory getCategory(Long id);

    Collection<RebrkCategory> getCategories();
}
