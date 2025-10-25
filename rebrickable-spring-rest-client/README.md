# rebrickable-api

A Java-based REST client for rebrickable for streamlined API integration.

## Features
- Comprehensive coverage of Rebrickable API endpoints.
- Built with Spring RestClient for robust HTTP communication.
- Supports rate limiting to comply with Rebrickable API usage policies.
- Easy to use with clear method mappings to API endpoints.
- Modular design for easy extension and maintenance.
- Detailed documentation and examples for quick integration.
- Open-source and community-driven.
- Supports Java 8 and above.
- Handles JSON serialization/deserialization seamlessly.
- Error handling for API responses.
- Configurable timeouts and retries for network robustness.
- Supports pagination for endpoints that return large datasets.
- Includes models for all major Rebrickable entities (Sets, Parts, Minifigs, Colors, Themes, etc.).

## Installation

```groovy
implementation('io.github.followsclosely:rebrickable-api:3.0.0')
implementation('io.github.followsclosely:rebrickable-spring-rest-client:3.0.0')
```

## Usage

A most basic example usage:
```java
// Initialize the REST client with your API key
RebrkSetClient client = new RebrkSetRestClient("your_api_key");

// Example: Fetch a set by its ID
RebrkSet set = client.getSet("1234-1");
```

### Example: Using DefaultRebrkApiRateLimiter
To adjust the rate of API requests, use the built-in DefaultRebrkApiRateLimiter:

```java
DefaultRebrkApiRateLimiter rateLimiter = new DefaultRebrkApiRateLimiter(2000); // 2000 ms between requests
RebrkSetClient client = new RebrkSetRestClient("your_api_key", rateLimiter);

for (String id : setIds) {
    //This call will be rate limited
    RebrkSet set = client.getSet(id);
    System.out.println("Fetched set: " + set.getName());
    // process set
}
```

* Replace `"your_api_key"` with your actual Rebrickable API key.
* Note that the rate limiter will only apply if you use the same instance of the client for all requests. Note that 
  this is done automatically if you use the constructor that does not take a rate limiter as a parameter.

### Mapped Endpoints

| Rebrickable URL                                               | Rebrickable Description                                                                         | API Details                                                  |
|:--------------------------------------------------------------|:------------------------------------------------------------------------------------------------|:-------------------------------------------------------------|
| **get /api/v3/lego/colors/**                                  | get a list of all Colors.                                                                       | rebrkColorRestClient.getColors();                            |
| **get /api/v3/lego/colors/{id}/**                             | get details about a specific Color.                                                             | rebrkColorRestClient.getColor(1L);                           |
| **get /api/v3/lego/elements/{element_id}/**                   | get details about a specific Element ID.                                                        | rebrkElementRestClient.getElement("4190230");                |
| **get /api/v3/lego/minifigs/**                                | get a list of Minifigs.                                                                         | rebrkMinifigRestClient.getMinifigs(new Query());             |
| **get /api/v3/lego/minifigs/{set_num}/**                      | get details for a specific Minifig.                                                             | rebrkMinifigRestClient.getMinifig("fig-013000");             |
| **get /api/v3/lego/minifigs/{set_num}/parts/**                | get a list of all Inventory Parts in this Minifig.                                              | rebrkElementRestClient.getElementsFromMinifig("fig-004551"); |
| **get /api/v3/lego/minifigs/{set_num}/sets/**                 | get a list of Sets a Minifig has appeared in.                                                   | rebrkSetRestClient.getSetsThatContainMinifig("fig-004551");  |
| **get /api/v3/lego/part_categories/**                         | get a list of all Part Categories.                                                              | rebrkCategoryRestClient.getCategories();                     |
| **get /api/v3/lego/part_categories/{id}/**                    | get details about a specific Part Category.                                                     | rebrkCategoryRestClient.getCategory(1L);                     |
| **get /api/v3/lego/parts/**                                   | get a list of Parts.                                                                            | rebrkPartRestClient.getParts(new Query());                   |
| **get /api/v3/lego/parts/{part_num}/**                        | get details about a specific Part.                                                              | rebrkPartRestClient.getPart("3846");                         |
| **get /api/v3/lego/parts/{part_num}/colors/**                 | get a list of all Colors a Part has appeared in.                                                | rebrkPartRestClient.getPartColors("3846");                   |
| **get /api/v3/lego/parts/{part_num}/colors/{color_id}/**      | get details about a specific Part/Color combination.                                            | N/A                                                          |
| **get /api/v3/lego/parts/{part_num}/colors/{color_id}/sets/** | get a list of all Sets the Part/Color combination has appeared in.                              | rebrkSetClient.getSetsThatContainPartAndColor("3005", "5");  |
| **get /api/v3/lego/sets/**                                    | get a list of Sets, optionally filtered by any of the below parameters.                         | rebrkSetRestClient.getSets(new Query());                     |
| **get /api/v3/lego/sets/{set_num}/**                          | get details for a specific Set.                                                                 | rebrkSetRestClient.getSet("10305-1");                        |
| **get /api/v3/lego/sets/{set_num}/alternates/**               | get a list of MOCs which are Alternate Builds of a specific Set - i.e. all parts in the MOC can | rebrkSetClient.getAlternates("10350-1", new SimpleQuery());  |
| **get /api/v3/lego/sets/{set_num}/minifigs/**                 | get a list of all Inventory Minifigs in this Set.                                               | rebrkSetRestClient.getSet("10305-1", false, true);           |
| **get /api/v3/lego/sets/{set_num}/parts/**                    | get a list of all Inventory Parts in this Set.                                                  | rebrkSetRestClient.getSet("10305-1", true, false);           |
| **get /api/v3/lego/sets/{set_num}/sets/**                     | get a list of all Inventory Sets in this Set.                                                   | N/A                                                          |
| **get /api/v3/lego/themes/**                                  | Return all Themes                                                                               | rebrkThemeRestClient.getThemes();                            |
| **get /api/v3/lego/themes/{id}/**                             | Return details for a specific Theme                                                             | rebrkThemeRestClient.getTheme(186L);                         |

## Spring Boot Starter
To integrate the Rebrickable API client into a Spring Boot application, you can use the provided Spring Boot Starter. 
This starter simplifies the configuration and setup process. See the 
[rebrickable-spring-rest-client](https://github.com/followsclosely/rebrickable-api/tree/master/rebrickable-spring-boot-starter) 
for more details.

## Contributing

Contributions are welcome! Please open issues or submit pull requests via GitHub.

## License

This project is licensed under the terms of the LICENSE file in this repository.

## Contact

For questions or support, open an issue on GitHub.
