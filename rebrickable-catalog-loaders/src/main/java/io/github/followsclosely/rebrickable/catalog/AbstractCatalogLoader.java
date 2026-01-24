package io.github.followsclosely.rebrickable.catalog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
     * For HTTP/HTTPS URIs the remote resource is downloaded to a temporary file
     * first so the remote socket is not kept open while the returned stream is
     * consumed/processed. This avoids remote timeouts when processing is slow.
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

            final GZIPInputStream in;
            final Reader reader;
            final CSVParser csvParser;
            final Path tempFileToDelete;

            // If the URI is HTTP(S) download to a temporary file first so the remote
            // socket/connection is not held open while the caller processes the stream.
            if (uri.startsWith("http://") || uri.startsWith("https://")) {
                tempFileToDelete = Files.createTempFile("rebrickable-catalog-", ".gz");
                tempFileToDelete.toFile().deleteOnExit();

                final HttpURLConnection conn = (HttpURLConnection) URI.create(uri).toURL().openConnection();
                conn.setConnectTimeout(30_000);
                conn.setReadTimeout(30_000);
                conn.setInstanceFollowRedirects(true);

                try (InputStream remoteIn = conn.getInputStream()) {
                    // copy the entire remote content to the temp file
                    Files.copy(remoteIn, tempFileToDelete, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    // If download fails, try to delete temp file and rethrow
                    try { Files.deleteIfExists(tempFileToDelete); } catch (Exception ignored) {}
                    throw new IOException("Failed to download remote catalog to temp file: " + uri, e);
                }

                in = new GZIPInputStream(Files.newInputStream(tempFileToDelete));
                reader = new InputStreamReader(in);
                csvParser = CSVParser.builder().setReader(reader).setFormat(csvFormat).get();
            } else {
                // Non-HTTP URI (file:, classpath:, etc.) — stream directly
                tempFileToDelete = null;
                in = new GZIPInputStream(URI.create(uri).toURL().openStream());
                reader = new InputStreamReader(in);
                csvParser = CSVParser.builder().setReader(reader).setFormat(csvFormat).get();
            }

            Stream<CSVRecord> csvRecordStream = StreamSupport.stream(csvParser.spliterator(), false);

            // Ensure resources and temporary file are cleaned up when the returned stream is closed
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
                if (tempFileToDelete != null) {
                    try {
                        Files.deleteIfExists(tempFileToDelete);
                    } catch (Exception e) {
                        AbstractCatalogLoader.log.warn("Failed to delete temp catalog file {}", tempFileToDelete, e);
                    }
                }
            });

            return csvRecordStream.map(this::apply);

        } catch (IOException e) {
            throw new IOException("Failed to open or parse the GZIP stream from URI: " + uri, e);
        }
    }

    abstract R apply(CSVRecord record);
}
