package com.epam.resourceservice.core;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.function.Function;

@FunctionalInterface
public interface SongMetadataExtractor extends Function<MultipartFile, Map<String, String>> {
}
