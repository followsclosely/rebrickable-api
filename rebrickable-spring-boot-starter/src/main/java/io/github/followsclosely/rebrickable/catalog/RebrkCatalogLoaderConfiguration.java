package io.github.followsclosely.rebrickable.catalog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Slf4j
@Configuration
@ConditionalOnClass(AbstractCatalogLoader.class)
@ConditionalOnProperty(prefix = "rebrickable.auto-create", name = "loaders", havingValue = "true", matchIfMissing = true)
public class RebrkCatalogLoaderConfiguration {

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkColorCatalogLoader.class)
    public RebrkColorCatalogLoader rebrkColorCatalogLoader() {
        return new RebrkColorCatalogLoader();
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkCategoryCatalogLoader.class)
    public RebrkCategoryCatalogLoader rebrkCategoryCatalogLoader() {
        return new RebrkCategoryCatalogLoader();
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkElementCatalogLoader.class)
    public RebrkElementCatalogLoader rebrkElementCatalogLoader() {
        return new RebrkElementCatalogLoader();
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkInventoryCatalogLoader.class)
    public RebrkInventoryCatalogLoader rebrkInventoryCatalogLoader() {
        return new RebrkInventoryCatalogLoader();
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkInventoryMinifigCatalogLoader.class)
    public RebrkInventoryMinifigCatalogLoader rebrkInventoryMinifigCatalogLoader() {
        return new RebrkInventoryMinifigCatalogLoader();
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkInventorySetCatalogLoader.class)
    public RebrkInventorySetCatalogLoader rebrkInventorySetCatalogLoader() {
        return new RebrkInventorySetCatalogLoader();
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkInventoryPartCatalogLoader.class)
    public RebrkInventoryPartCatalogLoader rebrkInventoryPartCatalogLoader() {
        return new RebrkInventoryPartCatalogLoader();
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkMinifigCatalogLoader.class)
    public RebrkMinifigCatalogLoader rebrkMinifigCatalogLoader() {
        return new RebrkMinifigCatalogLoader();
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkPartCatalogLoader.class)
    public RebrkPartCatalogLoader rebrkPartCatalogLoader() {
        return new RebrkPartCatalogLoader();
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkPartRelationshipCatalogLoader.class)
    public RebrkPartRelationshipCatalogLoader rebrkPartRelationshipCatalogLoader() {
        return new RebrkPartRelationshipCatalogLoader();
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkSetCatalogLoader.class)
    public RebrkSetCatalogLoader rebrkSetCatalogLoader() {
        return new RebrkSetCatalogLoader();
    }

    @Lazy
    @Bean
    @ConditionalOnMissingBean(RebrkThemeCatalogLoader.class)
    public RebrkThemeCatalogLoader rebrkThemeCatalogLoader() {
        return new RebrkThemeCatalogLoader();
    }
}
