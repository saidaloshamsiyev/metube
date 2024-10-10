package org.example.metube;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class S3Uploader {

    @Autowired
    private  S3Client s3Client;

    @Value("${aws.bucket.name}")
    private  String bucketName;


    public String uploadVideoToS3(String videoUrl) throws Exception {
        URL url = new URL(videoUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if (connection.getResponseCode() == 200) {
            InputStream inputStream = connection.getInputStream();

            String objectKey = "videos/" + System.currentTimeMillis() + ".mp4";

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

            s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, connection.getContentLength()));


            return s3Client.utilities().getUrl(b -> b.bucket(bucketName).key(objectKey)).toString();
        } else {
            throw new Exception("Video URL dan ma'lumot olishda xato yuz berdi.");
        }
    }
}
