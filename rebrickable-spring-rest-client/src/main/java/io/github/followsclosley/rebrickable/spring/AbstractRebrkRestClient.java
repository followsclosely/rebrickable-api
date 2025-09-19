package io.github.followsclosley.rebrickable.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class AbstractRebrkRestClient {

    protected final RestClient restClient;

    public AbstractRebrkRestClient(String authorizationKey) {
        this(RestClient.builder()
                .baseUrl("https://rebrickable.com/api/v3/lego/")
                .defaultHeaders(headers -> {
                    headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
                    headers.add("Authorization", "key " + authorizationKey);
                }).build());
    }
}
