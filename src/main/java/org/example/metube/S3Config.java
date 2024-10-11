package org.example.metube;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

/*we*/
    @Bean
    public AwsCredentials awsCredentials(){
        String secretKey = "gbvSTbUULPiKIoJrFEA3HroCDLM179N/C/WJPhTq";
        String accessKeyId = "AKIA6GBMGGFRCYJEPOLK";
        return AwsBasicCredentials.create(accessKeyId, secretKey);
    }

    @Bean
    public S3Client s3Client(){
        return S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(this::awsCredentials)
                .build();
    }
}
