package io.github.jbellis.jvector.example.util;

import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MyTest {
    public static void main(String... args) {
        DownloadHelper.maybeDownloadFvecs(s3FileListing());
        long syncStart = System.nanoTime();
        //s3FileListing();
        System.out.println("Sync Time taken (ms): "+ TimeUnit.MILLISECONDS.convert((System.nanoTime() - syncStart), TimeUnit.NANOSECONDS));
        System.out.println(">>>>>>><<<<<<<<<<<<");
        long asyncStart = System.nanoTime();
        //DownloadHelper.maybeListFilesInS3Async(null);
        System.out.println("Async Time taken (ms): "+ TimeUnit.MILLISECONDS.convert((System.nanoTime() - asyncStart), TimeUnit.NANOSECONDS));
        System.out.println(">>>>>>><<<<<<<<<<<<");
        long async2Start = System.nanoTime();
        //DownloadHelper.maybeListFilesInS3Async2(null);
        System.out.println("Async2 Time taken (ms): "+ TimeUnit.MILLISECONDS.convert((System.nanoTime() - asyncStart), TimeUnit.NANOSECONDS));
    }

    public static List<String> s3FileListing() {
        S3Client s3 = S3Client.builder().region(Region.US_EAST_1).credentialsProvider(AnonymousCredentialsProvider.create()).build();
        ListObjectsV2Request req = ListObjectsV2Request.builder().bucket("astra-vector").build();
        ListObjectsV2Response res = s3.listObjectsV2(req);
        List<String> filenames = res.contents().stream().map(S3Object::key).collect(Collectors.toList());
        for (String filename : filenames) {
            System.out.println(filename);
        }
        return filenames;
    }
}
