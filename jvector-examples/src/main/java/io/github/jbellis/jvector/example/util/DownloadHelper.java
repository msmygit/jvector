package io.github.jbellis.jvector.example.util;

import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;
import software.amazon.awssdk.http.crt.AwsCrtAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3AsyncClientBuilder;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.CompletedFileDownload;
import software.amazon.awssdk.transfer.s3.model.DownloadFileRequest;
import software.amazon.awssdk.transfer.s3.model.FileDownload;
import software.amazon.awssdk.transfer.s3.progress.LoggingTransferListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class DownloadHelper {
    private static final String bucketName = "astra-vector";

    private static S3AsyncClientBuilder s3AsyncClientBuilder() {
        return S3AsyncClient.builder()
                .region(Region.US_EAST_1)
                .httpClient(AwsCrtAsyncHttpClient.builder()
                        .maxConcurrency(1)
                        .build())
                .credentialsProvider(AnonymousCredentialsProvider.create());
    }

    public static void maybeDownloadFvecs(List<String> files) {
        List<String> keys;
        if (null == files || files.isEmpty()) {
            keys = List.of("wikipedia_squad/100k/ada_002_100000_base_vectors.fvec",
                    "wikipedia_squad/100k/ada_002_100000_query_vectors_10000.fvec",
                    "wikipedia_squad/100k/ada_002_100000_indices_query_10000.ivec");
        } else {
            keys = files;
        }
        // TODO how to detect and recover from incomplete downloads?

        // get directory from paths in keys
        List<String> dirs = keys.stream().map(key -> key.substring(0, key.lastIndexOf("/"))).distinct().collect(Collectors.toList());
        for (String dir : dirs) {
            try {
                dir = "fvec/" + dir;
                Files.createDirectories(Paths.get(dir));
            } catch (IOException e) {
                System.err.println("Failed to create directory: " + e.getMessage());
            }
        }

        try (S3AsyncClient s3Client = s3AsyncClientBuilder().build()) {
            S3TransferManager tm = S3TransferManager.builder().s3Client(s3Client).build();
            for (String key : keys) {
                Path path = Paths.get("fvec", key);
                if (Files.exists(path)) {
                    continue;
                }

                System.out.println("Downloading: " + key);
                DownloadFileRequest downloadFileRequest =
                        DownloadFileRequest.builder()
                                .getObjectRequest(b -> b.bucket(bucketName).key(key))
                                .addTransferListener(LoggingTransferListener.create())
                                .destination(Paths.get(path.toString()))
                                .build();

                // 3 retries
                FileDownload downloadFile;
                CompletedFileDownload downloadResult;
                long downloadedSize;
                for (int i = 0; i < 3; i++) {
                    downloadFile = tm.downloadFile(downloadFileRequest);
                    downloadResult = downloadFile.completionFuture().join();
                    downloadedSize = Files.size(path);

                    // Check if downloaded file size matches the expected size
                    if (downloadedSize == downloadResult.response().contentLength()) {
                        System.out.println("Downloaded file of length " + downloadedSize);
                        break;  // Successfully downloaded
                    } else {
                        System.out.println("Incomplete download. Retrying...");
                    }
                }
            }
            tm.close();
        } catch (Exception e) {
            System.out.println("Error downloading data from S3: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void maybeDownloadFvecs() {
        maybeDownloadFvecs(null);
    }

    public static void maybeDownloadHdf5(String datasetName) {
        Path path = Path.of(Hdf5Loader.HDF5_DIR);
        var fullPath = path.resolve(datasetName);
        if (Files.exists(fullPath)) {
            return;
        }

        // Download from https://ann-benchmarks.com/datasetName
        var url = "https://ann-benchmarks.com/" + datasetName;
        System.out.println("Downloading: " + url);

        HttpURLConnection connection;
        while (true) {
            int responseCode;
            try {
                connection = (HttpURLConnection) new URL(url).openConnection();
                responseCode = connection.getResponseCode();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                String newUrl = connection.getHeaderField("Location");
                System.out.println("Redirect detected to URL: " + newUrl);
                url = newUrl;
            } else {
                break;
            }
        }

        try (InputStream in = connection.getInputStream()) {
            Files.createDirectories(path);
            Files.copy(in, fullPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error downloading data: " + e.getMessage());
            System.exit(1);
        }
    }

    public static List<String> s3FileListing() {
        S3Client s3 = S3Client.builder().region(Region.US_EAST_1).credentialsProvider(AnonymousCredentialsProvider.create()).build();
        ListObjectsV2Request req = ListObjectsV2Request.builder().bucket(bucketName).build();
        ListObjectsV2Response res = s3.listObjectsV2(req);
        List<String> filenames = res.contents().stream().map(S3Object::key).collect(Collectors.toList());
        for (String filename : filenames) {
            System.out.println(filename);
        }
        return filenames;
    }
    public static void main(String... args) {
        s3FileListing();
    }
}
