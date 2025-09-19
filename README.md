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

Replace `"your_api_key"` with your actual Rebrickable API key.