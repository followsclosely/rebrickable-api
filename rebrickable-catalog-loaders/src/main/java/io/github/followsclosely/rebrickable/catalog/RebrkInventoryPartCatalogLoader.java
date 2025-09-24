package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkInventoryPart;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;


@Slf4j
public class RebrkInventoryPartCatalogLoader extends AbstractCatalogLoader<RebrkInventoryPart> implements RebrkInventoryPartCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/inventory_parts.csv.gz";

    private final CatalogContext context;

    public RebrkInventoryPartCatalogLoader(CatalogContext context) {
        super(DEFAULT_URI);
        this.context = context;
    }

    /**
     * This method maps a CSVRecord to a RebrkInventoryPart object.
     * <p>
     * The format of the csv file is as follows:
     * <pre>inventory_id,part_num,color_id,quantity,is_spare,img_url</pre>
     *
     * @param record The row of the .csv file as a CSVRecord
     * @return A RebrkInventoryPart object populated with data from the CSVRecord
     */
    protected RebrkInventoryPart apply(CSVRecord record) {
        RebrkInventoryPart inventoryPart = new RebrkInventoryPart();
        try {
            inventoryPart.setId(Long.valueOf(record.get(0)));
            inventoryPart.setPartId(record.get(1));
            inventoryPart.setColorId(Long.valueOf(record.get(2)));
            inventoryPart.setQuantity(Integer.valueOf(record.get(3)));
            inventoryPart.setIsSpare(Boolean.valueOf(record.get(4)));
            inventoryPart.setImageUrl(record.get(5));
        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return inventoryPart;
    }

}
