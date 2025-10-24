package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.AuthorizationKey;
import io.github.followsclosely.rebrickable.RebrkApiRateLimiter;
import io.github.followsclosely.rebrickable.RebrkTestUtilities;
import io.github.followsclosely.rebrickable.RebrkThemeClient;
import io.github.followsclosely.rebrickable.RebrkThemeClient.Query;
import io.github.followsclosely.rebrickable.dto.RebrkTheme;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkThemeRestClientTest {

    private final RebrkThemeClient rebrkThemeClient = new RebrkThemeRestClient(RebrkTestUtilities.RATE_LIMITER, AuthorizationKey.VALUE);

    @Test
    void getTheme() {
        RebrkTheme result = rebrkThemeClient.getTheme(186L);

        assertNotNull(result);
        assertEquals(186L, result.getId());
        assertEquals("Castle", result.getName());
    }

    @Test
    void getThemes() {
        Collection<RebrkTheme> result = rebrkThemeClient.getThemes(Query.builder().pageSize(500).build());
        assertNotNull(result);
        assertEquals(482, result.size());
    }
}