package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
@EnableConfigurationProperties(RebrkConfiguration.class)
@ConditionalOnProperty(prefix = "rebrickable.auto-create", name = "clients", havingValue = "true", matchIfMissing = true)
public class RebrkRestClientConfiguration {

    @Autowired
    private RebrkConfiguration configuration;

    @Bean
    @Lazy
    @ConditionalOnMissingBean(value = DefaultRebrkApiRateLimiter.class, name = "rebrkApiRateLimiter")
    DefaultRebrkApiRateLimiter rebrkApiRateLimiter(
            @Value("${rebrickable.api-limits.min-wait-ms-between-calls:1001}") long minDelay,
            @Value("${rebrickable.api-limits.random-ms-addition:123}") long minDelayBonus) {
        return new DefaultRebrkApiRateLimiter(minDelay, minDelayBonus);
    }

    @Bean(name = "rebrickableRestClient")
    //@Lazy
    //@ConditionalOnMissingBean(value = RestClient.class, name = "rebrickableRestClient")
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
    @Lazy
    @ConditionalOnClass(RebrkCategoryRestClient.class)
    @ConditionalOnMissingBean(RebrkCategoryClient.class)
    RebrkCategoryClient rebrkCategoryClient(@Qualifier("rebrickableRestClient") RestClient rebrickableRestClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkCategoryClient bean using RebrkCategoryRestClient");
        return new RebrkCategoryRestClient(rebrickableRestClient, rebrkApiRateLimiter);
    }

    @Bean
    @Lazy
    @ConditionalOnClass(RebrkColorRestClient.class)
    @ConditionalOnMissingBean(RebrkColorClient.class)
    RebrkColorClient rebrkColorClient(@Qualifier("rebrickableRestClient") RestClient rebrickableRestClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkColorClient bean using RebrkColorRestClient");
        return new RebrkColorRestClient(rebrickableRestClient, rebrkApiRateLimiter);
    }

    @Bean
    @Lazy
    @ConditionalOnClass(RebrkElementRestClient.class)
    @ConditionalOnMissingBean(RebrkElementClient.class)
    RebrkElementClient rebrkElementClient(@Qualifier("rebrickableRestClient") RestClient rebrickableRestClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkElementClient bean using RebrkElementRestClient");
        return new RebrkElementRestClient(rebrickableRestClient, rebrkApiRateLimiter);
    }

    @Bean
    @Lazy
    @ConditionalOnClass(RebrkMinifigRestClient.class)
    @ConditionalOnMissingBean(RebrkMinifigClient.class)
    RebrkMinifigClient rebrkMinifigClient(@Qualifier("rebrickableRestClient") RestClient rebrickableRestClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkMinifigClient bean using RebrkMinifigRestClient");
        return new RebrkMinifigRestClient(rebrickableRestClient, rebrkApiRateLimiter);
    }

    @Bean
    @Lazy
    @ConditionalOnClass(RebrkPartRestClient.class)
    @ConditionalOnMissingBean(RebrkPartClient.class)
    RebrkPartClient rebrkPartClient(@Qualifier("rebrickableRestClient") RestClient rebrickableRestClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkPartClient bean using RebrkPartRestClient");
        return new RebrkPartRestClient(rebrickableRestClient, rebrkApiRateLimiter);
    }

    @Bean
    @Lazy
    @ConditionalOnClass(RebrkSetRestClient.class)
    @ConditionalOnMissingBean(RebrkSetClient.class)
    RebrkSetClient rebrkSetClient(@Qualifier("rebrickableRestClient") RestClient rebrickableRestClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkSetClient bean using RebrkSetRestClient");
        return new RebrkSetRestClient(rebrickableRestClient, rebrkApiRateLimiter);
    }

    @Bean
    @Lazy
    @ConditionalOnClass(RebrkThemeRestClient.class)
    @ConditionalOnMissingBean(RebrkThemeClient.class)
    RebrkThemeClient rebrkThemeClient(@Qualifier("rebrickableRestClient") RestClient rebrickableRestClient, RebrkApiRateLimiter rebrkApiRateLimiter) {
        log.info("Creating rebrkThemeClient bean using RebrkThemeRestClient");
        return new RebrkThemeRestClient(rebrickableRestClient, rebrkApiRateLimiter);
    }
}
