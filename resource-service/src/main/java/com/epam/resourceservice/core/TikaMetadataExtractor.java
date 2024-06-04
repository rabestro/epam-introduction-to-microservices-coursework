package com.epam.resourceservice.core;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.function.Function;

public class TikaMetadataExtractor implements Function<MultipartFile, Metadata> {

    @Override
    public Metadata apply(MultipartFile multipartFile) {
        var tika = new Tika();
        var metadata = new Metadata();
        try {
            tika.parse(multipartFile.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return metadata;
    }
}
