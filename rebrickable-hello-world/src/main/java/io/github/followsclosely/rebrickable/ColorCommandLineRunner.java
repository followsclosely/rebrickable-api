package io.github.followsclosely.rebrickable;

import io.github.followsclosely.rebrickable.catalog.RebrkColorCatalogLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ColorCommandLineRunner implements CommandLineRunner {

    @Autowired
    public RebrkColorCatalogLoader colorCatalogLoader;

    @Override
    public void run(String... args) throws IOException {
        System.out.println("Starting color catalog load...");
        this.colorCatalogLoader.stream().forEach(System.out::println);
    }
}
