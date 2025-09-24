package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.AuthorizationKey;
import io.github.followsclosely.rebrickable.RebrkSetClient;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import io.github.followsclosely.rebrickable.dto.RebrkSet;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkSetRestClientTest {

    private final RebrkSetClient rebrkSetClient = new RebrkSetRestClient(AuthorizationKey.VALUE);

    @Test
    void getSet() {
        RebrkSet result = rebrkSetClient.getSet("10305-1");

        assertNotNull(result);
        assertEquals("10305-1", result.getNumber());
        assertEquals("Lion Knights' Castle", result.getName());
    }

    @Test
    void getSetWithParts() {
        RebrkSet result = rebrkSetClient.getSet("10305-1", true, false);

        assertNotNull(result);
        assertEquals("10305-1", result.getNumber());
        assertEquals("Lion Knights' Castle", result.getName());
    }

    @Test
    void getSetWithMinifigs() {
        RebrkSet result = rebrkSetClient.getSet("10305-1", false, true);

        assertNotNull(result);
        assertEquals("10305-1", result.getNumber());
        assertEquals("Lion Knights' Castle", result.getName());
    }

    @Test
    void getSetsThatContainMinifig() {
        Collection<RebrkSet> result = rebrkSetClient.getSetsThatContainMinifig("fig-004551");

        assertNotNull(result);
        assertEquals(6, result.size());
    }

    @Test
    void getSets() {
        RebrkResponse<RebrkSet> result = rebrkSetClient.getSets(RebrkSetClient.Query.builder().minParts(50).maxParts(50).pageSize(1000).build());

        assertNotNull(result);
        assertEquals(197, result.getResults().size());
    }
}