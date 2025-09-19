package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.RebrkMinifigClient;
import io.github.followsclosley.rebrickable.dto.RebrkMinifig;
import io.github.followsclosley.rebrickable.dto.RebrkSet;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkMinifigRestClientTest {

    private static final String AUTHORIZATION_KEY = "683adc46fe0b60add9c6c5920ec4f0f0";

    @Test
    void getSet() {
        RebrkMinifigRestClient client = new RebrkMinifigRestClient(AUTHORIZATION_KEY);
        RebrkMinifig result = client.getMinifig("fig-000001");

        assertNotNull(result);
        assertEquals("fig-000001", result.getId());
        assertEquals("Toy Store Employee", result.getName());
        assertEquals(4, result.getParts());
        assertNotNull(result.getImageUrl());
    }
}