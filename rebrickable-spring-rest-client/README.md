# rebrickable-api

A Java-based REST client for rebrickable using Gradle and Lombok for streamlined API integration.

##Usage
Here is a usage section you can add to your `README.md`:

## Usage

```java
// Initialize the REST client with your API key
RebrkSetClient client = new RebrkSetRestClient("your_api_key");

// Example: Fetch a set by its ID
RebrkSet set = client.getSet("1234-1");
```

Replace `"your_api_key"` with your actual Rebrickable API key.

## Usage

| Rebrickable URL                                               | Rebrickable Description                                                                         | API Details                                                  |
|:--------------------------------------------------------------|:------------------------------------------------------------------------------------------------|:-------------------------------------------------------------|
| **get /api/v3/lego/colors/**                                  | get a list of all Colors.                                                                       | rebrkColorRestClient.getColors();                            |
| **get /api/v3/lego/colors/{id}/**                             | get details about a specific Color.                                                             | rebrkColorRestClient.getColor(1L);                           |
| **get /api/v3/lego/elements/{element_id}/**                   | get details about a specific Element ID.                                                        | rebrkElementRestClient.getElement("4190230");                |
| **get /api/v3/lego/minifigs/**                                | get a list of Minifigs.                                                                         | rebrkMinifigRestClient.getMinifigs(Query.builder().build()); |
| **get /api/v3/lego/minifigs/{set_num}/**                      | get details for a specific Minifig.                                                             | rebrkMinifigRestClient.getMinifig("fig-013000");             |
| **get /api/v3/lego/minifigs/{set_num}/parts/**                | get a list of all Inventory Parts in this Minifig.                                              | rebrkElementRestClient.getElementsFromMinifig("fig-004551"); |
| **get /api/v3/lego/minifigs/{set_num}/sets/**                 | get a list of Sets a Minifig has appeared in.                                                   | rebrkSetRestClient.getSetsThatContainMinifig("fig-004551");  |
| **get /api/v3/lego/part_categories/**                         | get a list of all Part Categories.                                                              | client.getCategories();                                      |
| **get /api/v3/lego/part_categories/{id}/**                    | get details about a specific Part Category.                                                     | client.getCategory(1L);                                      |
| **get /api/v3/lego/parts/**                                   | get a list of Parts.                                                                            | rebrkPartRestClient.getParts(Query.builder().build());       |
| **get /api/v3/lego/parts/{part_num}/**                        | get details about a specific Part.                                                              | rebrkPartRestClient.getPart("3846");                         |
| **get /api/v3/lego/parts/{part_num}/colors/**                 | get a list of all Colors a Part has appeared in.                                                | client.getPartColors("3846");                                |
| **get /api/v3/lego/parts/{part_num}/colors/{color_id}/**      | get details about a specific Part/Color combination.                                            | N/A                                                          |
| **get /api/v3/lego/parts/{part_num}/colors/{color_id}/sets/** | get a list of all Sets the Part/Color combination has appeard in.                               | N/A                                                          |
| **get /api/v3/lego/sets/**                                    | get a list of Sets, optionally filtered by any of the below parameters.                         | rebrkSetRestClient.getSets(Query.builder().build());         |
| **get /api/v3/lego/sets/{set_num}/**                          | get details for a specific Set.                                                                 | rebrkSetRestClient.getSet("10305-1");                        |
| **get /api/v3/lego/sets/{set_num}/alternates/**               | get a list of MOCs which are Alternate Builds of a specific Set - i.e. all parts in the MOC can | N/A                                                          |
| **get /api/v3/lego/sets/{set_num}/minifigs/**                 | get a list of all Inventory Minifigs in this Set.                                               | rebrkSetRestClient.getSet("10305-1", false, true);           |
| **get /api/v3/lego/sets/{set_num}/parts/**                    | get a list of all Inventory Parts in this Set.                                                  | rebrkSetRestClient.getSet("10305-1", true, false);           |
| **get /api/v3/lego/sets/{set_num}/sets/**                     | get a list of all Inventory Sets in this Set.                                                   | N/A                                                          |
| **get /api/v3/lego/themes/**                                  | Return all Themes                                                                               | result = client.getThemes();                                 |
| **get /api/v3/lego/themes/{id}/**                             | Return details for a specific Theme                                                             | client.getTheme(186L);                                       |