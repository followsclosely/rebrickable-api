package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class RebrkSetCatalogLoader extends AbstractCatalogLoader<RebrkSet> implements RebrkSetCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/sets.csv.gz";

    public RebrkSetCatalogLoader() {
        super(DEFAULT_URI);
    }
    public RebrkSetCatalogLoader(String uri, CatalogContext context) {
        super((uri==null) ? DEFAULT_URI : uri);
    }

    /**
     * This method maps a CSVRecord to a RebrkSet object.
     * <p>
     * The format of the csv file is as follows:
     * <pre>set_num,name,year,theme_id,num_parts,img_url</pre>
     *
     * @param record The row of the .csv file as a CSVRecord
     * @return A RebrkSet object populated with data from the CSVRecord
     */
    protected RebrkSet apply(CSVRecord record) {
        RebrkSet set = new RebrkSet();
        try {
            set.setNumber(record.get(0));
            set.setName(record.get(1));
            set.setYear(Integer.parseInt(record.get(2)));
            set.setThemeId(Long.parseLong(record.get(3)));
            set.setNumberOfParts(Integer.parseInt(record.get(4)));
            set.setImageUrl(record.get(5));
        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return set;
    }

}
