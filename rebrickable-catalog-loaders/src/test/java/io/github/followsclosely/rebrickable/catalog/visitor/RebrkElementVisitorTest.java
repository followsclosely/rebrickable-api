package io.github.followsclosely.rebrickable.catalog.visitor;

import io.github.followsclosely.rebrickable.catalog.CatalogContext;
import io.github.followsclosely.rebrickable.dto.RebrkColor;
import io.github.followsclosely.rebrickable.dto.RebrkElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RebrkElementVisitorTest {

    private static final CatalogContext context = new CatalogContext();

    @BeforeAll
    static void beforeAll() {
        context.add(RebrkColor.builder().id(123L).name("Red").build());
    }

    @Test
    void visit_setsColor_whenColorIdIsPresent() {
        RebrkElement element = new RebrkElementVisitor(context).visit(RebrkElement.builder().colorId(123L).partId("321").build());

        assertEquals("Red", element.getColor().getName());
    }

    @Test
    void visit_doesNotSetColor_whenColorIdIsNull() {
        RebrkElement element = new RebrkElementVisitor(context).visit(new RebrkElement());
        assertNull(element.getColor());
    }

    @Test
    void visit_returnsElement() {
        RebrkElement element = new RebrkElement();
        RebrkElement result = new RebrkElementVisitor(context).visit(element);
        assertSame(element, result);
    }
}
