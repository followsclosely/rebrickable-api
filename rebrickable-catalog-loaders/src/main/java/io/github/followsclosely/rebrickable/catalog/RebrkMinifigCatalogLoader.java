package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkMinifig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class RebrkMinifigCatalogLoader extends AbstractCatalogLoader<RebrkMinifig> implements RebrkMinifigCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/minifigs.csv.gz";

    public RebrkMinifigCatalogLoader() {
        super(DEFAULT_URI);
    }

    /**
     * This method maps a CSVRecord to a RebrkMinifig object.
     * <p>
     * The format of the csv file is as follows:
     * <pre>fig_num,name,num_parts,img_url</pre>
     *
     * @param record The row of the .csv file as a CSVRecord
     * @return A RebrkMinifig object populated with data from the CSVRecord
     */
    protected RebrkMinifig apply(CSVRecord record) {
        RebrkMinifig minifig = new RebrkMinifig();
        try {
            minifig.setId(record.get(0));
            minifig.setName(record.get(1));
            minifig.setParts(Integer.parseInt(record.get(2)));
            minifig.setImageUrl(record.get(3));
        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return minifig;
    }

}
