package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.AuthorizationKey;
import io.github.followsclosely.rebrickable.RebrkCategoryClient;
import io.github.followsclosely.rebrickable.dto.RebrkCategory;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkCategoryRestClientTest {

    private final RebrkCategoryClient rebrkCategoryClient = new RebrkCategoryRestClient(AuthorizationKey.VALUE);

    @Test
    void getSet() {
        RebrkCategory result = rebrkCategoryClient.getCategory(1L);

        assertNotNull(result);
        assertEquals("Baseplates", result.getName());
    }

    @Test
    void getSets() {
        Collection<RebrkCategory> result = rebrkCategoryClient.getCategories();

        assertNotNull(result);
        assertEquals(76, result.size());
    }
}