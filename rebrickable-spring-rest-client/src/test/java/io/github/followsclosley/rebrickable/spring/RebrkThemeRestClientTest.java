package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.AuthorizationKey;
import io.github.followsclosley.rebrickable.RebrkThemeClient;
import io.github.followsclosley.rebrickable.dto.RebrkTheme;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkThemeRestClientTest {

    private final RebrkThemeClient rebrkThemeClient = new RebrkThemeRestClient(AuthorizationKey.VALUE);

    @Test
    void getTheme() {
        RebrkTheme result = rebrkThemeClient.getTheme(186L);

        assertNotNull(result);
        assertEquals(186L, result.getId());
        assertEquals("Castle", result.getName());
    }

    @Test
    void getThemes() {
        Collection<RebrkTheme> result = rebrkThemeClient.getThemes();
        assertNotNull(result);
        assertEquals(482, result.size());
    }
}