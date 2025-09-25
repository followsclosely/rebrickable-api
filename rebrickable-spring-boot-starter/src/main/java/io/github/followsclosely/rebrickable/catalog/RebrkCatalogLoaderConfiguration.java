package io.github.followsclosely.rebrickable.catalog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnClass(AbstractCatalogLoader.class)
public class RebrkCatalogLoaderConfiguration {

    @Bean
    @ConditionalOnMissingBean(RebrkColorCatalogLoader.class)
    public RebrkColorCatalogLoader rebrkColorCatalogLoader() {
        RebrkColorCatalogLoader loader = new RebrkColorCatalogLoader();
        return loader;
    }
}
