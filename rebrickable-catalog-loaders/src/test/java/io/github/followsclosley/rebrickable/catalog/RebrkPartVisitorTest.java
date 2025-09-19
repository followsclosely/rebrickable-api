package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkPart;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

class RebrkPartVisitorTest {

    @Test
    void visit() throws IOException {

        CatalogContext context = CatalogContext.builder()
                .categories(new RebrkCategoryCatalogLoader().stream().toList())
                .build();

        RebrkPartVisitor visitor = new RebrkPartVisitor(context);

        RebrkPartCatalogLoader loader = new RebrkPartCatalogLoader(context);


        Optional<RebrkPart> result = loader.stream().map(visitor::visit).findFirst();
        System.out.println(result);

        //List<RebrkPart> results = loader.stream().map(visitor::visit).toList();
        //results.forEach(System.out::println);


    }
}