package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.dto.RebrkSet;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkSetRestClientTest {

    private static final String AUTHORIZATION_KEY = "683adc46fe0b60add9c6c5920ec4f0f0";

    @Test
    void getSet() {
        RebrkSetRestClient client = new RebrkSetRestClient(AUTHORIZATION_KEY);
        RebrkSet result = client.getSet("10305-1");

        assertNotNull(result);
        assertEquals("10305-1", result.getNumber());
        assertEquals("Lion Knights' Castle", result.getName());
    }

    //@Test
    void getSets() {

        RebrkSetRestClient client = new RebrkSetRestClient(AUTHORIZATION_KEY);

        Collection<RebrkSet> result = client.getSets();
        assertNotNull(result);
        assertEquals(273, result.size());
    }
}