package io.github.followsclosley.rebrickable.dto;

import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkColorTest {

    private static final String YOUR_AUTHORIZATION_KEY = "683adc46fe0b60add9c6c5920ec4f0f0";

    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://rebrickable.com/api/v3/lego/")
            .defaultHeaders(headers -> {
                headers.add("Content-Type", "application/json");
                headers.add("Authorization", "key " + YOUR_AUTHORIZATION_KEY);
            })
            .build();

    @Test
    public void getAllColors() {

        ParameterizedTypeReference<RebrkResponse<RebrkColor>> blnkColorTypeRef = new ParameterizedTypeReference<RebrkResponse<RebrkColor>>() {
        };

        RebrkResponse<RebrkColor> result = restClient.get()
                .uri(builder -> builder.path("colors/").queryParam("page_size", "1000").build())
                .retrieve()
                .body(blnkColorTypeRef);

        assertNotNull(result);
        assertNotNull(result.getCount());
        assertEquals(Integer.valueOf(273), result.getCount());
    }

    @Test
    public void getSingleColor() {

        RebrkColor result = restClient.get()
                .uri(builder -> builder.path("colors/1/").build())
                .retrieve()
                .body(RebrkColor.class);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Blue", result.getName());
    }
}