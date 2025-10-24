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
    @ConditionalOnMissingBean(value = RebrkApiRateLimiter.class, name = "rebrkApiRateLimiter")
    RebrkApiRateLimiter rebrkApiRateLimiter(
            @Value("${rebrickable.api-limits.min-wait-ms-between-calls:1001}") long minDelay,
            @Value("${rebrickable.api-limits.random-ms-addition:123}") long minDelayBonus) {
        return new RebrkApiRateLimiter(minDelay, minDelayBonus);
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
    RebrkCategoryClient rebrkCategoryClient(RestClient restClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkCategoryClient bean using RebrkCategoryRestClient");
        RebrkCategoryRestClient client = new RebrkCategoryRestClient(restClient);
        client.setRebrkApiRateLimiter(rebrkApiRateLimiter);
        return client;
    }

    @Bean
    @ConditionalOnClass(RebrkColorRestClient.class)
    @ConditionalOnMissingBean(RebrkColorClient.class)
    RebrkColorClient rebrkColorClient(RestClient restClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkColorClient bean using RebrkColorRestClient");
        RebrkColorRestClient client = new RebrkColorRestClient(restClient);
        client.setRebrkApiRateLimiter(rebrkApiRateLimiter);
        return client;
    }

    @Bean
    @ConditionalOnClass(RebrkElementRestClient.class)
    @ConditionalOnMissingBean(RebrkElementClient.class)
    RebrkElementClient rebrkElementClient(RestClient restClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkElementClient bean using RebrkElementRestClient");
        RebrkElementRestClient client = new RebrkElementRestClient(restClient);
        client.setRebrkApiRateLimiter(rebrkApiRateLimiter);
        return client;
    }

    @Bean
    @ConditionalOnClass(RebrkMinifigRestClient.class)
    @ConditionalOnMissingBean(RebrkMinifigClient.class)
    RebrkMinifigClient rebrkMinifigClient(RestClient restClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkMinifigClient bean using RebrkMinifigRestClient");
        RebrkMinifigRestClient client = new RebrkMinifigRestClient(restClient);
        client.setRebrkApiRateLimiter(rebrkApiRateLimiter);
        return client;
    }

    @Bean
    @ConditionalOnClass(RebrkPartRestClient.class)
    @ConditionalOnMissingBean(RebrkPartClient.class)
    RebrkPartClient rebrkPartClient(RestClient restClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkPartClient bean using RebrkPartRestClient");
        RebrkPartRestClient client = new RebrkPartRestClient(restClient);
        client.setRebrkApiRateLimiter(rebrkApiRateLimiter);
        return client;
    }

    @Bean
    @ConditionalOnClass(RebrkSetRestClient.class)
    @ConditionalOnMissingBean(RebrkSetClient.class)
    RebrkSetClient rebrkSetClient(RestClient restClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkSetClient bean using RebrkSetRestClient");
        RebrkSetRestClient client = new RebrkSetRestClient(restClient);
        client.setRebrkApiRateLimiter(rebrkApiRateLimiter);
        return client;
    }

    @Bean
    @ConditionalOnClass(RebrkThemeRestClient.class)
    @ConditionalOnMissingBean(RebrkThemeClient.class)
    RebrkThemeClient rebrkThemeClient(RestClient restClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkThemeClient bean using RebrkThemeRestClient");
        RebrkThemeRestClient client = new RebrkThemeRestClient(restClient);
        client.setRebrkApiRateLimiter(rebrkApiRateLimiter);
        return client;
    }
}
