package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkInventorySet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;


@Slf4j
public class RebrkInventorySetCatalogLoader extends AbstractCatalogLoader<RebrkInventorySet> implements RebrkInventorySetCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/inventory_sets.csv.gz";

    public RebrkInventorySetCatalogLoader() {
        super(DEFAULT_URI);
    }

    public RebrkInventorySetCatalogLoader(String uri) {
        super((uri == null) ? DEFAULT_URI : uri);
    }

    /**
     * This method maps a CSVRecord to a RebrkInventorySet object.
     * <p>
     * The format of the csv file is as follows:
     * <pre>inventory_id,set_num,quantity</pre>
     *
     * @param record The row of the .csv file as a CSVRecord
     * @return A RebrkInventorySet object populated with data from the CSVRecord
     */
    protected RebrkInventorySet apply(CSVRecord record) {
        RebrkInventorySet inventorySet = new RebrkInventorySet();
        try {
            inventorySet.setId(Long.valueOf(record.get(0)));
            inventorySet.setSetId(record.get(1));
            inventorySet.setQuantity(Integer.valueOf(record.get(2)));
        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return inventorySet;
    }

}
