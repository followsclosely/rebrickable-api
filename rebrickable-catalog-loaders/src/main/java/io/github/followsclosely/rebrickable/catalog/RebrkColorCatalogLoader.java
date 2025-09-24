package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkColor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;


@Slf4j
public class RebrkColorCatalogLoader extends AbstractCatalogLoader<RebrkColor> implements RebrkColorCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/colors.csv.gz";

    RebrkColorCatalogLoader() {
        super(DEFAULT_URI);
    }

    /**
     * This method maps a CSVRecord to a RebrkColor object.
     * <p>
     * The format of the csv file is as follows:
     * <pre>id,name,rgb,is_trans,num_parts,num_sets,y1,y2</pre>
     *
     * @param record The row of the .csv file as a CSVRecord
     * @return A RebrkColor object populated with data from the CSVRecord
     */
    protected RebrkColor apply(CSVRecord record) {
        RebrkColor color = new RebrkColor();
        try {
            color.setId(Long.parseLong(record.get(0)));
            color.setName(record.get(1));
            color.setRgb(record.get(2));
            color.setTransparent("t".equalsIgnoreCase(record.get(3)));
        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return color;
    }

}
