package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.dto.RebrkColor;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkColorRestClientTest {

    private static final String AUTHORIZATION_KEY = "683adc46fe0b60add9c6c5920ec4f0f0";

    @Test
    void getColor() {

        RebrkColorRestClient client = new RebrkColorRestClient(AUTHORIZATION_KEY);

        RebrkColor result = client.getColor(1L);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Blue", result.getName());
    }

    @Test
    void getColors() {

        RebrkColorRestClient client = new RebrkColorRestClient(AUTHORIZATION_KEY);

        Collection<RebrkColor> result = client.getColors();
        assertNotNull(result);
        assertEquals(273, result.size());
    }
}