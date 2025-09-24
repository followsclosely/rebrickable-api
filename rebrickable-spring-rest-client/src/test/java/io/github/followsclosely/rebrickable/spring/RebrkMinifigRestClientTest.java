package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.AuthorizationKey;
import io.github.followsclosely.rebrickable.RebrkMinifigClient;
import io.github.followsclosely.rebrickable.dto.RebrkMinifig;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkMinifigRestClientTest {

    private final RebrkMinifigClient rebrkMinifigClient = new RebrkMinifigRestClient(AuthorizationKey.VALUE);

    @Test
    void getMinifig() {
        RebrkMinifig result = rebrkMinifigClient.getMinifig("fig-013000");

        assertNotNull(result);
        assertEquals("fig-013000", result.getId());
        assertEquals("Knight, Black Falcon", result.getName());
        assertEquals(5, result.getParts());
        assertNotNull(result.getImageUrl());
    }

    @Test
    void getMinifigs() {
        RebrkMinifigClient.Query query = RebrkMinifigClient.Query.builder()
                .page(1)
                .pageSize(50)
                .minParts(25)
                .maxParts(25)
                .build();
        RebrkResponse<RebrkMinifig> result = rebrkMinifigClient.getMinifigs(query);

        assertNotNull(result);
        assertNotNull(result.getResults());
        assertEquals(8, result.getResults().size());
    }

}