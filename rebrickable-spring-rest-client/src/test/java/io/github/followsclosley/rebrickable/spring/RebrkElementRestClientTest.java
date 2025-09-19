package io.github.followsclosley.rebrickable.spring;

import io.github.followsclosley.rebrickable.AuthorizationKey;
import io.github.followsclosley.rebrickable.RebrkElementClient;
import io.github.followsclosley.rebrickable.dto.RebrkElement;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RebrkElementRestClientTest {

    private final RebrkElementClient rebrkElementClient = new RebrkElementRestClient(AuthorizationKey.VALUE);

    @Test
    void getElement() {
        RebrkElement result = rebrkElementClient.getElement("4190230");

        System.out.println(result);

        assertNotNull(result);
        assertEquals("4190230", result.getId());
    }

    @Test
    void getPartsFromMinifig() {
        Collection<RebrkElement> elements = rebrkElementClient.getElementsFromMinifig("fig-004551");

        System.out.println(elements);
    }
}