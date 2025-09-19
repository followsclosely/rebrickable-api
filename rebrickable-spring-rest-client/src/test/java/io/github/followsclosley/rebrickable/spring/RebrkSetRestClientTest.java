package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.AuthorizationKey;
import io.github.followsclosley.rebrickable.dto.RebrkSet;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkSetRestClientTest {

    private final RebrkSetRestClient client = new RebrkSetRestClient(AuthorizationKey.VALUE);

    @Test
    void getSet() {
        RebrkSet result = client.getSet("10305-1");

        assertNotNull(result);
        assertEquals("10305-1", result.getNumber());
        assertEquals("Lion Knights' Castle", result.getName());
    }

    //@Test
    void getSets() {
        Collection<RebrkSet> result = client.getSets();

        assertNotNull(result);
        assertEquals(273, result.size());
    }
}