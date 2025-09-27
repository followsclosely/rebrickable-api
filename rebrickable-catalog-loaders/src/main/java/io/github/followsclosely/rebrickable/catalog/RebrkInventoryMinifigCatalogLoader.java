package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkInventoryMinifig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;


@Slf4j
public class RebrkInventoryMinifigCatalogLoader extends AbstractCatalogLoader<RebrkInventoryMinifig> implements RebrkInventoryMinifigCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/inventory_minifigs.csv.gz";

    public RebrkInventoryMinifigCatalogLoader() {
        super(DEFAULT_URI);
    }
    public RebrkInventoryMinifigCatalogLoader(String uri) {
        super((uri == null) ? DEFAULT_URI : uri);
    }

    /**
     * This method maps a CSVRecord to a RebrkInventoryMinifig object.
     * <p>
     * The format of the csv file is as follows:
     * <pre>inventory_id,fig_num,quantity</pre>
     *
     * @param record The row of the .csv file as a CSVRecord
     * @return A RebrkInventoryMinifig object populated with data from the CSVRecord
     */
    protected RebrkInventoryMinifig apply(CSVRecord record) {
        RebrkInventoryMinifig inventoryMinifig = new RebrkInventoryMinifig();
        try {
            inventoryMinifig.setId(Long.valueOf(record.get(0)));
            inventoryMinifig.setMinifigId(record.get(1));
            inventoryMinifig.setQuantity(Integer.valueOf(record.get(2)));
        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return inventoryMinifig;
    }

}
