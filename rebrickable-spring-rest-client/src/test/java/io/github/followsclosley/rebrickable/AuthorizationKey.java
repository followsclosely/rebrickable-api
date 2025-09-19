package io.github.followsclosley.rebrickable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Utility class to load the authorization key from application.properties file.
 */

public class AuthorizationKey {
    public static String VALUE;

    static {
        Properties props = new Properties();
        for (String resource : List.of("application.properties", ".application.properties")) {
            try (InputStream input = AuthorizationKey.class.getClassLoader().getResourceAsStream(resource)) {
                if (input != null) {
                    props.load(input);
                    VALUE = props.getProperty("key");
                    System.out.println("Authorization key loaded from \"" + resource + "\"");
                }
            } catch (IOException e) {
                System.out.println("ERROR: loading key from " + resource + ": " + e.getMessage());
            }
        }

        if( VALUE == null || VALUE.isEmpty() || "<<insert your key here>>".equals(VALUE)) {
            throw new IllegalStateException("Your authorization key not found in application.properties or .application.properties");
        }
    }
}
