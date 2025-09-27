package io.github.followsclosely.rebrickable.catalog;

import io.github.followsclosely.rebrickable.dto.RebrkPartRelationship;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;


@Slf4j
public class RebrkPartRelationshipCatalogLoader extends AbstractCatalogLoader<RebrkPartRelationship> implements RebrkPartRelationshipCatalog {

    private final static String DEFAULT_URI = "https://cdn.rebrickable.com/media/downloads/part_relationships.csv.gz";


    public RebrkPartRelationshipCatalogLoader() {
        super(DEFAULT_URI);
    }
    public RebrkPartRelationshipCatalogLoader(String uri) {
        super((uri == null) ? DEFAULT_URI : uri);
    }

    /**
     * This method maps a CSVRecord to a RebrkPartRelationship object.
     * <p>
     * The format of the csv file is as follows:
     * <pre>rel_type,child_part_num,parent_part_num</pre>
     *
     * @param record The row of the .csv file as a CSVRecord
     * @return A RebrkPartRelationship object populated with data from the CSVRecord
     */
    protected RebrkPartRelationship apply(CSVRecord record) {
        RebrkPartRelationship relationship = new RebrkPartRelationship();
        try {
            relationship.setType(record.get(0));
            relationship.setChildId(record.get(1));
            relationship.setParentId(record.get(2));
        } catch (Exception e) {
            log.error("Error processing record {}: {}", record.getRecordNumber(), e.getMessage(), e);
        }
        return relationship;
    }

}
