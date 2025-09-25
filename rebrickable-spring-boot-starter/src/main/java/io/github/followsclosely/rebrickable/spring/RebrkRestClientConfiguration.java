package io.github.followsclosely.rebrickable.spring;

import io.github.followsclosely.rebrickable.RebrkCategoryClient;
import io.github.followsclosely.rebrickable.RebrkConfiguration;
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
        RebrkCategoryRestClient client = new RebrkCategoryRestClient(restClient);
        log.info("Created Bean: {}", client);
        return client;
    }
}
