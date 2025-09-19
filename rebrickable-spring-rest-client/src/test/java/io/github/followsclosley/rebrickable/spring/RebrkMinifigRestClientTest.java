package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.AuthorizationKey;
import io.github.followsclosley.rebrickable.dto.RebrkMinifig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkMinifigRestClientTest {

    private final RebrkMinifigRestClient client = new RebrkMinifigRestClient(AuthorizationKey.VALUE);

    @Test
    void getSet() {
        RebrkMinifig result = client.getMinifig("fig-000001");

        assertNotNull(result);
        assertEquals("fig-000001", result.getId());
        assertEquals("Toy Store Employee", result.getName());
        assertEquals(4, result.getParts());
        assertNotNull(result.getImageUrl());
    }
}