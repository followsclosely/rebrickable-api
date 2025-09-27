package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkInventory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;


@Slf4j
public class RebrkInventoryCatalogLoader extends AbstractCatalogLoader<RebrkInventory> implements RebrkInventoryCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/inventories.csv.gz";

    public RebrkInventoryCatalogLoader() {
        super(DEFAULT_URI);
    }

    public RebrkInventoryCatalogLoader(String uri) {
        super((uri==null) ? DEFAULT_URI : uri);
    }

    /**
     * This method maps a CSVRecord to a RebrkInventory object.
     * <p>
     * The format of the csv file is as follows:
     * <pre>id,version,set_num</pre>
     *
     * @param record The row of the .csv file as a CSVRecord
     * @return A RebrkInventory object populated with data from the CSVRecord
     */
    protected RebrkInventory apply(CSVRecord record) {
        RebrkInventory inventory = new RebrkInventory();
        try {
            inventory.setId(Long.valueOf(record.get(0)));
            inventory.setVersion(Integer.valueOf(record.get(1)));
            inventory.setSetId(record.get(0));
        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return inventory;
    }

}
