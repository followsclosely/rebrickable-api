package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkCategoryClient;
import io.github.followsclosely.rebrickable.RebrkConfiguration;
import io.github.followsclosely.rebrickable.RebrkColorClient;
import io.github.followsclosely.rebrickable.RebrkElementClient;
import io.github.followsclosely.rebrickable.RebrkMinifigClient;
import io.github.followsclosely.rebrickable.RebrkPartClient;
import io.github.followsclosely.rebrickable.RebrkSetClient;
import io.github.followsclosely.rebrickable.RebrkThemeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
@EnableConfigurationProperties(RebrkConfiguration.class)
public class RebrkRestClientConfiguration {

    @Autowired
    private RebrkConfiguration configuration;

    @Bean
    @ConditionalOnMissingBean(value = RestClient.class, name = "rebrickableRestClient")
    RestClient rebrickableRestClient() {
        return RestClient.builder()
                .baseUrl(configuration.getBaseUrl())
                .defaultHeaders(headers -> {
                    headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
                    headers.add("Authorization", "key " + configuration.getKey());
                }).build();
    }

    @Bean
    @ConditionalOnClass(RebrkCategoryRestClient.class)
    @ConditionalOnMissingBean(RebrkCategoryClient.class)
    RebrkCategoryClient rebrkCategoryClient(RestClient restClient) {
        log.info("Creating rebrkCategoryClient bean using RebrkCategoryRestClient");
        return new RebrkCategoryRestClient(restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkColorRestClient.class)
    @ConditionalOnMissingBean(RebrkColorClient.class)
    RebrkColorClient rebrkColorClient(RestClient restClient) {
        log.info("Creating rebrkColorClient bean using RebrkColorRestClient");
        return new RebrkColorRestClient(restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkElementRestClient.class)
    @ConditionalOnMissingBean(RebrkElementClient.class)
    RebrkElementClient rebrkElementClient(RestClient restClient) {
        log.info("Creating rebrkElementClient bean using RebrkElementRestClient");
        return new RebrkElementRestClient(restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkMinifigRestClient.class)
    @ConditionalOnMissingBean(RebrkMinifigClient.class)
    RebrkMinifigClient rebrkMinifigClient(RestClient restClient) {
        log.info("Creating rebrkMinifigClient bean using RebrkMinifigRestClient");
        return new RebrkMinifigRestClient(restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkPartRestClient.class)
    @ConditionalOnMissingBean(RebrkPartClient.class)
    RebrkPartClient rebrkPartClient(RestClient restClient) {
        log.info("Creating rebrkPartClient bean using RebrkPartRestClient");
        return new RebrkPartRestClient(restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkSetRestClient.class)
    @ConditionalOnMissingBean(RebrkSetClient.class)
    RebrkSetClient rebrkSetClient(RestClient restClient) {
        log.info("Creating rebrkSetClient bean using RebrkSetRestClient");
        return new RebrkSetRestClient(restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkThemeRestClient.class)
    @ConditionalOnMissingBean(RebrkThemeClient.class)
    RebrkThemeClient rebrkThemeClient(RestClient restClient) {
        log.info("Creating rebrkThemeClient bean using RebrkThemeRestClient");
        return new RebrkThemeRestClient(restClient);
    }
}
