package org.example.metube;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    @Value("${aws.secret-access-key}")
    private String secretKey;


    @Value("${aws.access-key-id}")
    private String accessKeyId;



    @Bean
    public AwsCredentials awsCredentials(){
        return AwsBasicCredentials.create(accessKeyId,secretKey);
    }

    @Bean
    public S3Client s3Client(){
        return S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(this::awsCredentials)
                .build();
    }
}
