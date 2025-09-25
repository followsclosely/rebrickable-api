package io.github.followsclosely.rebrickable;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("rebrickable")
public class RebrkConfiguration {
    private String key = null;
    private String baseUrl = "https://rebrickable.com/api/v3/lego/";
}
