package io.github.followsclosley.rebrickable.catalog;

import io.github.followsclosley.rebrickable.dto.RebrkTheme;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;


@Slf4j
public class RebrkThemeCatalogLoader extends AbstractCatalogLoader<RebrkTheme> implements RebrkThemeCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/themes.csv.gz";

    RebrkThemeCatalogLoader() {
        super(DEFAULT_URI);
    }

    /**
     * The format of the csv file is as follows:
     * <pre>id,name,parent_id</pre>
     *
     * @param record
     * @return
     */
    protected RebrkTheme apply(CSVRecord record) {
        RebrkTheme theme = new RebrkTheme();
        try {
            theme.setId(Long.parseLong(record.get(0)));
            theme.setName(record.get(1));
            if (record.isSet(2)) {
                String value = record.get(2);
                if (value != null && !value.isEmpty()) {
                    theme.setParentId(Long.parseLong(value));
                }
            }
        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return theme;
    }

}
