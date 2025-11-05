package io.github.followsclosely.rebrickable.catalog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPInputStream;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractCatalogLoader<R> {

    private final String uri;
    private final CSVFormat csvFormat;

    /**
     * Constructs an AbstractCatalogLoader with the specified URI and CSV format.
     *
     * @param uri The URI of the GZIP-compressed CSV file.
     */
    protected AbstractCatalogLoader(String uri) {
        this(uri, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).setDelimiter(',').setIgnoreEmptyLines(true).get());
    }

    /**
     * Loads a Stream of RebrkColor objects from the catalog URI.
     * This method correctly streams the data by returning a lazy stream
     * and ensuring resources are closed when the stream is consumed or closed.
     *
     * @return A Stream of RebrkColor objects.
     * @throws IOException if an I/O error occurs during stream creation.
     */
    public Stream<R> stream() throws IOException {
        try {
            String uri = this.uri;
            if (!uri.contains("?")) {
                uri = String.format("%s?%s", uri, System.currentTimeMillis());
            }

            // Open the chain of streams and the CSVParser.
            // These will be closed by the stream's onClose handler.
            final GZIPInputStream in = new GZIPInputStream(URI.create(uri).toURL().openStream());
            final Reader reader = new InputStreamReader(in);
            final CSVParser csvParser = CSVParser.builder().setReader(reader).setFormat(csvFormat).get();

            // Create a Spliterator from the CSVParser's iterator.
            // This is the core of converting an Iterable to a Stream.
            // The second parameter 'true' indicates a parallel stream is not supported.
            Stream<CSVRecord> csvRecordStream = StreamSupport.stream(csvParser.spliterator(), false);

            // Add an onClose handler to the stream. This is crucial.
            // This handler is automatically called when the stream is fully consumed
            // or when a terminal operation like forEach, collect, or close() is called.
            // It ensures that the underlying resources (csvParser, reader, and gzis) are properly closed.
            csvRecordStream = csvRecordStream.onClose(() -> {
                try {
                    csvParser.close();
                } catch (Exception e) {
                    AbstractCatalogLoader.log.error("Error closing CSVParser", e);
                }
                try {
                    reader.close();
                } catch (Exception e) {
                    AbstractCatalogLoader.log.error("Error closing Reader", e);
                }
                try {
                    in.close();
                } catch (Exception e) {
                    AbstractCatalogLoader.log.error("Error closing GZIPInputStream", e);
                }
            });

            // Map each CSVRecord to a RebrkColor object.
            // This is a lazy operation and will only happen as the stream is consumed.
            return csvRecordStream.map(this::apply);

        } catch (IOException e) {
            throw new IOException("Failed to open or parse the GZIP stream from URI: " + uri, e);
        }
    }

    abstract R apply(CSVRecord record);
}
