package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkElement;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;


@Slf4j
public class RebrkElementCatalogLoader extends AbstractCatalogLoader<RebrkElement> implements RebrkElementCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/elements.csv.gz";

    public RebrkElementCatalogLoader() {
        super(DEFAULT_URI);
    }

    public RebrkElementCatalogLoader(String uri) {
        super((uri == null) ? DEFAULT_URI : uri);
    }

    /**
     * This method maps a CSVRecord to a RebrkElement object.
     * <p>
     * The format of the csv file is as follows:
     * <pre>element_id,part_num,color_id,design_id</pre>
     *
     * @param record The row of the .csv file as a CSVRecord
     * @return A RebrkElement object populated with data from the CSVRecord
     */
    protected RebrkElement apply(CSVRecord record) {
        RebrkElement element = new RebrkElement();
        try {
            element.setId(record.get(0));
            element.setPartId(record.get(1));
            //todo: Should I try and load a part here?
            element.setColorId(Long.parseLong(record.get(2)));

            if (record.isSet(3)) {
                element.setDesignId(record.get(3));
            }

        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return element;
    }

}
