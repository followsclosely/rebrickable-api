package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @ConditionalOnMissingBean(value = DefaultRebrkApiRateLimiter.class, name = "rebrkApiRateLimiter")
    DefaultRebrkApiRateLimiter rebrkApiRateLimiter(
            @Value("${rebrickable.api-limits.min-wait-ms-between-calls:1001}") long minDelay,
            @Value("${rebrickable.api-limits.random-ms-addition:123}") long minDelayBonus) {
        return new DefaultRebrkApiRateLimiter(minDelay, minDelayBonus);
    }

    @Bean
    @ConditionalOnMissingBean(value = RestClient.class, name = "rebrickableRestClient")
    RestClient rebrickableRestClient() {

        RestClient.Builder builder = RestClient.builder()
                .baseUrl(configuration.getBaseUrl())
                .defaultHeaders(headers -> {
                    headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
                    headers.add("Authorization", "key " + configuration.getKey());
                });

        if (log.isDebugEnabled()) {
            builder.defaultStatusHandler(response -> {
                log.debug("HttpResponse {} : {}", response.getStatusCode(), response.getStatusText());
                response.getHeaders().forEach((key, values) -> log.debug("Header: {}={}", key, values));
                return response.getStatusCode().is2xxSuccessful();
            });
        }

        return builder.build();
    }

    @Bean
    @ConditionalOnClass(RebrkCategoryRestClient.class)
    @ConditionalOnMissingBean(RebrkCategoryClient.class)
    RebrkCategoryClient rebrkCategoryClient(RebrkApiRateLimiter rebrkApiRateLimiter, RestClient restClient) {
        log.info("Creating rebrkCategoryClient bean using RebrkCategoryRestClient");
        return new RebrkCategoryRestClient(rebrkApiRateLimiter, restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkColorRestClient.class)
    @ConditionalOnMissingBean(RebrkColorClient.class)
    RebrkColorClient rebrkColorClient(RebrkApiRateLimiter rebrkApiRateLimiter, RestClient restClient) {
        log.info("Creating rebrkColorClient bean using RebrkColorRestClient");
        return new RebrkColorRestClient(rebrkApiRateLimiter, restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkElementRestClient.class)
    @ConditionalOnMissingBean(RebrkElementClient.class)
    RebrkElementClient rebrkElementClient(RebrkApiRateLimiter rebrkApiRateLimiter, RestClient restClient) {
        log.info("Creating rebrkElementClient bean using RebrkElementRestClient");
        return new RebrkElementRestClient(rebrkApiRateLimiter, restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkMinifigRestClient.class)
    @ConditionalOnMissingBean(RebrkMinifigClient.class)
    RebrkMinifigClient rebrkMinifigClient(RebrkApiRateLimiter rebrkApiRateLimiter, RestClient restClient) {
        log.info("Creating rebrkMinifigClient bean using RebrkMinifigRestClient");
        return new RebrkMinifigRestClient(rebrkApiRateLimiter, restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkPartRestClient.class)
    @ConditionalOnMissingBean(RebrkPartClient.class)
    RebrkPartClient rebrkPartClient(RebrkApiRateLimiter rebrkApiRateLimiter, RestClient restClient) {
        log.info("Creating rebrkPartClient bean using RebrkPartRestClient");
        return new RebrkPartRestClient(rebrkApiRateLimiter, restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkSetRestClient.class)
    @ConditionalOnMissingBean(RebrkSetClient.class)
    RebrkSetClient rebrkSetClient(RebrkApiRateLimiter rebrkApiRateLimiter, RestClient restClient) {
        log.info("Creating rebrkSetClient bean using RebrkSetRestClient");
        return new RebrkSetRestClient(rebrkApiRateLimiter, restClient);
    }

    @Bean
    @ConditionalOnClass(RebrkThemeRestClient.class)
    @ConditionalOnMissingBean(RebrkThemeClient.class)
    RebrkThemeClient rebrkThemeClient(RebrkApiRateLimiter rebrkApiRateLimiter, RestClient restClient) {
        log.info("Creating rebrkThemeClient bean using RebrkThemeRestClient");
        return new RebrkThemeRestClient(rebrkApiRateLimiter, restClient);
    }
}
