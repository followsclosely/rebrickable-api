package io.github.followsclosely.rebrickable.catalog.visitor;

import io.github.followsclosely.rebrickable.catalog.CatalogContext;
import io.github.followsclosely.rebrickable.catalog.RebrkCategoryCatalogLoader;
import io.github.followsclosely.rebrickable.catalog.RebrkPartCatalogLoader;
import io.github.followsclosely.rebrickable.dto.RebrkPart;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RebrkPartVisitorTest {

    @Test
    void visit() throws IOException {
        CatalogContext context = new CatalogContext();
        new RebrkCategoryCatalogLoader().stream().forEach(context::add);

        RebrkPartVisitor visitor = new RebrkPartVisitor(context);
        Optional<RebrkPart> result = new RebrkPartCatalogLoader().stream().map(visitor::visit).findFirst();
        System.out.println(result);

        assertNotNull(result, "Expected result to be non-null");
        assertTrue(result.isPresent(), "Expected at least one part in the catalog");
        RebrkPart part = result.get();
        assertNotNull(part.getCategory(), "Expected category to be set");
        assertNotNull(part.getName(), "Expected part name to be set");
        assertEquals(1, visitor.getVisitCount(), "Expected exactly one visit, this is more of a test on the streaming behavior");
    }

    @Test
    void visitAllParts_categoryIsSetForAll() throws IOException {
        CatalogContext context = new CatalogContext();
        new RebrkCategoryCatalogLoader().stream().forEach(context::add);

        RebrkPartVisitor visitor = new RebrkPartVisitor(context);
        long count = new RebrkPartCatalogLoader().stream()
                .map(visitor::visit)
                .peek(part -> assertNotNull(part.getCategory(), "Category should be set for all parts"))
                .count();
        assertTrue(count > 0, "Expected at least one part in the catalog");
        assertEquals(count, visitor.getVisitCount(), "Visit count should match the number of parts visited");
    }

    @Test
    void visit_partWithMissingCategory() throws IOException {
        CatalogContext context = new CatalogContext();
        // Do not add any categories to context to simulate missing category
        RebrkPartVisitor visitor = new RebrkPartVisitor(context);
        Optional<RebrkPart> result = new RebrkPartCatalogLoader().stream().map(visitor::visit).findFirst();
        assertNotNull(result, "Expected result to be non-null");
        assertTrue(result.isPresent(), "Expected at least one part in the catalog");
        assertNull(result.get().getCategory(), "Expected category to be null when not present in context");
    }
}