package io.github.followsclosley.rebrickable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class to load the authorization key from application.properties file.
 */
public class AuthorizationKey {
    public static String VALUE;

    static {
        Properties props = new Properties();
        try (InputStream input = AuthorizationKey.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input != null) {
                props.load(input);
                VALUE = props.getProperty("key");
            } else {
                throw new RuntimeException("application.properties not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load key from application.properties", e);
        }
    }
}
