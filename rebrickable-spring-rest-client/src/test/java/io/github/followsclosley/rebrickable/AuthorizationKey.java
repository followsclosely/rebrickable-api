package io.github.followsclosley.rebrickable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Utility class for loading the authorization key from the application's properties file.
 * <p>
 * This class attempts to read the "key" property from either "application.properties" or ".application.properties"
 * located in the classpath. The loaded key is stored in the static {@link #VALUE} field.
 * <p>
 * If the key is missing, empty, or set to the default placeholder value, an {@link IllegalStateException} is thrown
 * during class initialization.
 * <p>
 * Usage:
 * <pre>
 *     String key = AuthorizationKey.VALUE;
 * </pre>
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
                    if (!"<<insert your key here>>".equals(VALUE)) {
                        System.out.println("Authorization key loaded from \"" + resource + "\"");
                    }
                }
            } catch (IOException e) {
                System.out.println("ERROR: loading key from " + resource + ": " + e.getMessage());
            }
        }

        if (VALUE == null || VALUE.isEmpty() || "<<insert your key here>>".equals(VALUE)) {
            throw new IllegalStateException("Your authorization key not found in application.properties or .application.properties");
        }
    }
}
