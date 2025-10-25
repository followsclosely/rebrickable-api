# rebrickable-api

A Java-based REST client for rebrickable using Gradle and Lombok for streamlined API integration.

##Usage
Here is a usage section you can add to your `README.md`:

## Usage

```java
// Initialize the REST client with your API key
RebrkSetRestClient client = new RebrkSetRestClient("your_api_key");

// Example: Fetch a set by its ID
RebrkSet set = client.getSet("1234-1");
```

* Replace `"your_api_key"` with your actual Rebrickable API key.

## rebrickable-api

This module provides the core Java REST client interfaces for interacting with the Rebrickable API. 
This module serves as the foundation for other modules that build upon the core functionality.

## rebrickable-catalog-loaders

This module is responsible for loading and processing catalog data from Rebrickable. It contains utilities for
importing, transforming, or managing LEGO set/catalog information.
See [LEGO Catalog Database Download](https://rebrickable.com/downloads/) for more details on available data.

## rebrickable-spring-rest-client

This module implements the REST client interfaces defined in the rebrickable-api module. It provides Spring-friendly beans,
configuration, and integration points, making it easy to inject and use the Rebrickable RestClients in a Spring Application.

## rebrickable-spring-boot-starter

This module is a Spring Boot starter, which autoconfigures the CatalogLoaders and RestClients and related beans for 
Spring Boot projects. By including this starter, developers can quickly enable Rebrickable API integration in their 
Spring Boot applications with minimal setup.

A build.gradle snippet to include the starter in your Spring Boot project:
```groovy
dependencies {
    implementation('io.github.followsclosely:rebrickable-spring-boot-starter:3.0.0')
    // The inclusion of this dependency will cause the starter to auto-create all the loaders.
    implementation('io.github.followsclosely:rebrickable-catalog-loaders:3.0.0')
    // The inclusion of this dependency will cause the starter to auto-create the RestClients.
    implementation('io.github.followsclosely:rebrickable-spring-rest-client:3.0.0')
}
```
A simple Spring Boot application that loads and prints the Rebrickable color catalog:
```java
@SpringBootApplication
public class HelloRebrickableApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloRebrickableApplication.class, args);
    }
}

@Component
public class ColorCommandLineRunner implements CommandLineRunner {

    @Autowired
    public RebrkColorCatalogLoader colorCatalogLoader;

    @Override
    public void run(String... args) throws IOException {
        this.colorCatalogLoader.stream().forEach(System.out::println);
    }
}
```
When you run the application, you should see output similar to the following:
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.6)

2025-10-24T20:54:38.418-04:00  INFO 19312 --- [           main] i.g.f.r.HelloRebrickableApplication      : Starting HelloRebrickableApplication using Java 17.0.2 with PID 1
...
Starting color catalog load...
RebrkColor(id=-1, name=[Unknown], rgb=0033B2)
RebrkColor(id=0, name=Black, rgb=05131D)
RebrkColor(id=1, name=Blue, rgb=0055BF)
RebrkColor(id=2, name=Green, rgb=237841)
RebrkColor(id=3, name=Dark Turquoise, rgb=008F9B)
RebrkColor(id=4, name=Red, rgb=C91A09)
...
```
