package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkPart;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;


@Slf4j
public class RebrkPartCatalogLoader extends AbstractCatalogLoader<RebrkPart> implements RebrkPartCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/parts.csv.gz";

    private final CatalogContext context;

    public RebrkPartCatalogLoader(CatalogContext context) {
        super(DEFAULT_URI);
        this.context = context;
    }

    /**
     * This method maps a CSVRecord to a RebrkSet object.
     * <p>
     * The format of the csv file is as follows:
     * <pre>part_num,name,part_cat_id,part_material</pre>
     *
     * @param record The row of the .csv file as a CSVRecord
     * @return A RebrkSet object populated with data from the CSVRecord
     */
    protected RebrkPart apply(CSVRecord record) {
        RebrkPart part = new RebrkPart();
        try {
            part.setId(record.get(0));
            part.setName(record.get(1));
            part.setCategoryId(Long.parseLong(record.get(2)));
            if (context != null && part.getCategoryId() != null) {
                part.setCategory(context.getCategory(part.getCategoryId()));
            }
            part.setMaterial(record.get(3));
        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return part;
    }

}
