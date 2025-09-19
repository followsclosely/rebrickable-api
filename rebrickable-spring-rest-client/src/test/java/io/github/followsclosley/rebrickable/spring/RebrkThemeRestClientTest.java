package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.dto.RebrkTheme;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkThemeRestClientTest {

    private static final String AUTHORIZATION_KEY = "683adc46fe0b60add9c6c5920ec4f0f0";
    private final RebrkThemeRestClient client = new RebrkThemeRestClient(AUTHORIZATION_KEY);

    @Test
    void getTheme() {
        RebrkTheme result = client.getTheme(1L);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Technic", result.getName());
    }

    @Test
    void getThemes() {
        Collection<RebrkTheme> result = client.getThemes();
        assertNotNull(result);
        assertEquals(482, result.size());
    }
}