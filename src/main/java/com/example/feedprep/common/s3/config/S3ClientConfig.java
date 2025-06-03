package com.example.feedprep.common.s3.config;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@RequiredArgsConstructor
public class S3ClientConfig {

    private final Environment env;

    @Bean
    public S3Client amazoneS3() {

        // AWS S3 버켓의 액세스키, 비밀키
        AwsBasicCredentials credentials =
            AwsBasicCredentials.create(
                env.getProperty("aws.credentials.access-key"),
                env.getProperty("aws.credentials.secret-key")
            );

        // 접속 지역을 한국(AP_NORTHEAST_2)으로 설정
        return S3Client.builder()
            .region(Region.AP_NORTHEAST_2)
            .credentialsProvider(StaticCredentialsProvider.create(credentials))
            .build();
    }
}
