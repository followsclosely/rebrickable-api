package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkCategory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class RebrkCategoryCatalogLoader extends AbstractCatalogLoader<RebrkCategory> implements RebrkCategoryCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/part_categories.csv.gz";

    public RebrkCategoryCatalogLoader() {
        super(DEFAULT_URI);
    }

    public RebrkCategoryCatalogLoader(String uri) {
        super(uri == null ? DEFAULT_URI : uri);
    }

    /**
     * This method maps a CSVRecord to a RebrkTheme object.
     * <p>
     * The format of the csv file is as follows:
     * <pre>id,name,parent_id</pre>
     *
     * @param record The row of the .csv file as a CSVRecord
     * @return A RebrkTheme object populated with data from the CSVRecord
     */
    protected RebrkCategory apply(CSVRecord record) {
        RebrkCategory category = new RebrkCategory();
        try {
            category.setId(Long.parseLong(record.get(0)));
            category.setName(record.get(1));
        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return category;
    }

}
