package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.AuthorizationKey;
import io.github.followsclosley.rebrickable.RebrkColorClient;
import io.github.followsclosley.rebrickable.dto.RebrkColor;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkColorRestClientTest {
    private final RebrkColorClient rebrkColorClient = new RebrkColorRestClient(AuthorizationKey.VALUE);

    @Test
    void getColor() {
        RebrkColor result = rebrkColorClient.getColor(1L);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Blue", result.getName());
    }

    @Test
    void getColors() {
        Collection<RebrkColor> result = rebrkColorClient.getColors();

        assertNotNull(result);
        assertEquals(273, result.size());
    }
}