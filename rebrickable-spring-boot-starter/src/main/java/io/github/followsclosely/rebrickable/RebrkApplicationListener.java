package io.github.followsclosely.rebrickable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.StringUtils;

@Slf4j
public class RebrkApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    /**
     * Obfuscate a key by replacing all but the first and last few characters with asterisks.
     *
     * @param key           the key to obfuscate
     * @param previewLength the number of characters to show at the start and end of the key
     * @return the obfuscated key
     */
    private static String obfuscateKey(String key, int previewLength) {
        if (key == null || key.length() <= previewLength) {
            return key == null ? null : "*".repeat(key.length());
        }
        int maskLength = key.length() - previewLength;
        return key.substring(0, previewLength / 2) + "*".repeat(maskLength) + key.substring(key.length() - previewLength / 2);
    }

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        String keyName = "rebrickable.key";
        String keyValue = event.getEnvironment().getProperty(keyName);

        if (!StringUtils.hasLength(keyValue)) {
            throw new RuntimeException("Missing Resource: \"" + keyName + "\" is required to initialize the RestClient bean.");
        } else {
            log.info("Found Resource: \"{}\" is set to: {}", keyName, obfuscateKey(keyValue, 10));
        }
    }
}
