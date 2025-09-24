package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.AuthorizationKey;
import io.github.followsclosely.rebrickable.RebrkPartClient;
import io.github.followsclosely.rebrickable.dto.RebrkColorDetails;
import io.github.followsclosely.rebrickable.dto.RebrkPart;
import io.github.followsclosely.rebrickable.dto.RebrkResponse;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkPartRestClientTest {

    private final RebrkPartClient rebrkPartClient = new RebrkPartRestClient(AuthorizationKey.VALUE);

    @Test
    void getPart() {
        RebrkPart result = rebrkPartClient.getPart("3846");

        System.out.println(result);

        assertNotNull(result);
        assertEquals("3846", result.getId());
        assertEquals("Shield Triangular", result.getName());
    }

    @Test
    void getColorsOfPart() {
        Collection<RebrkColorDetails> colors = rebrkPartClient.getColorsOfPart("3846");
        assertNotNull(colors);
    }

    @Test
    void getParts() {
        RebrkResponse<RebrkPart> result = rebrkPartClient.getParts(RebrkPartRestClient.Query.builder().bricklinkId("4972pb01").build());

        assertNotNull(result);
        assertEquals(1, result.getResults().size());
        assertEquals("4972pr0001", result.getResults().get(0).getId());
    }
}