package com.jokeer.dhand.config;

import com.jokeer.dhand.properties.MinIOProperties;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MinIOProperties.class)
public class MinIOConfig {

    private MinIOProperties minIOProperties;

    public MinIOConfig(MinIOProperties minIOProperties) {
        this.minIOProperties = minIOProperties;
    }

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minIOProperties.getMinioEndpoint())
                .credentials(minIOProperties.getMinioAccessKey(), minIOProperties.getMinioSecretKey())
                .build();
    }


}
