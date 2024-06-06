package com.epam.resourceservice.config;

import com.epam.resourceservice.core.SongMetadataExtractor;
import com.epam.resourceservice.core.TikaMetadataExtractor;
import com.epam.resourceservice.core.SongMetadataMapper;
import org.apache.tika.metadata.Metadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.function.Function;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Function<MultipartFile, Metadata> mp3MetadataExtractor() {
        return new TikaMetadataExtractor();
    }

    @Bean
    public Function<Metadata, Map<String, String>> songMetadataMapper() {
        return new SongMetadataMapper();
    }

    @Bean
    public Function<MultipartFile, Map<String, String>> songInfoExtractor() {
        return mp3MetadataExtractor().andThen(songMetadataMapper());
    }
}
