package io.github.followsclosely.rebrickable.catalog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnClass(AbstractCatalogLoader.class)
@ConditionalOnProperty(prefix = "rebrickable.auto-create", name = "loaders", havingValue = "true", matchIfMissing = true)
public class RebrkCatalogLoaderConfiguration {

    @Bean
    @ConditionalOnMissingBean(RebrkColorCatalogLoader.class)
    public RebrkColorCatalogLoader rebrkColorCatalogLoader() {
        return new RebrkColorCatalogLoader();
    }

    @Bean
    @ConditionalOnMissingBean(RebrkCategoryCatalogLoader.class)
    public RebrkCategoryCatalogLoader rebrkCategoryCatalogLoader() {
        return new RebrkCategoryCatalogLoader();
    }

    @Bean
    @ConditionalOnMissingBean(RebrkElementCatalogLoader.class)
    public RebrkElementCatalogLoader rebrkElementCatalogLoader() {
        return new RebrkElementCatalogLoader();
    }

    @Bean
    @ConditionalOnMissingBean(RebrkInventoryCatalogLoader.class)
    public RebrkInventoryCatalogLoader rebrkInventoryCatalogLoader() {
        return new RebrkInventoryCatalogLoader();
    }

    @Bean
    @ConditionalOnMissingBean(RebrkInventoryMinifigCatalogLoader.class)
    public RebrkInventoryMinifigCatalogLoader rebrkInventoryMinifigCatalogLoader() {
        return new RebrkInventoryMinifigCatalogLoader();
    }

    @Bean
    @ConditionalOnMissingBean(RebrkInventorySetCatalogLoader.class)
    public RebrkInventorySetCatalogLoader rebrkInventorySetCatalogLoader() {
        return new RebrkInventorySetCatalogLoader();
    }

    @Bean
    @ConditionalOnMissingBean(RebrkInventoryPartCatalogLoader.class)
    public RebrkInventoryPartCatalogLoader rebrkInventoryPartCatalogLoader() {
        return new RebrkInventoryPartCatalogLoader();
    }

    @Bean
    @ConditionalOnMissingBean(RebrkMinifigCatalogLoader.class)
    public RebrkMinifigCatalogLoader rebrkMinifigCatalogLoader() {
        return new RebrkMinifigCatalogLoader();
    }

    @Bean
    @ConditionalOnMissingBean(RebrkPartCatalogLoader.class)
    public RebrkPartCatalogLoader rebrkPartCatalogLoader() {
        return new RebrkPartCatalogLoader();
    }

    @Bean
    @ConditionalOnMissingBean(RebrkPartRelationshipCatalogLoader.class)
    public RebrkPartRelationshipCatalogLoader rebrkPartRelationshipCatalogLoader() {
        return new RebrkPartRelationshipCatalogLoader();
    }

    @Bean
    @ConditionalOnMissingBean(RebrkSetCatalogLoader.class)
    public RebrkSetCatalogLoader rebrkSetCatalogLoader() {
        return new RebrkSetCatalogLoader();
    }

    @Bean
    @ConditionalOnMissingBean(RebrkThemeCatalogLoader.class)
    public RebrkThemeCatalogLoader rebrkThemeCatalogLoader() {
        return new RebrkThemeCatalogLoader();
    }
}
