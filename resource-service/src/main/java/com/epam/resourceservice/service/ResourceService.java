package com.epam.resourceservice.service;

import com.epam.resourceservice.core.SongMetadataExtractor;
import com.epam.resourceservice.core.SongMetadataMapper;
import com.epam.resourceservice.model.Resource;
import com.epam.resourceservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final Function<MultipartFile, Map<String, String>> mp3MetadataExtractor;
    private final RestTemplate restTemplate;
    private final String songServiceUrl = "http://localhost:8081/songs";  // replace with actual Song Service URL

    @Autowired
    public ResourceService(
            ResourceRepository resourceRepository,
            Function<MultipartFile, Map<String, String>> metadataExtractor,
            RestTemplate restTemplate) {
        this.resourceRepository = resourceRepository;
        this.mp3MetadataExtractor = metadataExtractor;
        this.restTemplate = restTemplate;
    }

    public Resource save(MultipartFile file) throws IOException {
        var resource = new Resource();
        resource.setData(file.getBytes());
        var savedResource = resourceRepository.save(resource);

        // Extract metadata from the file
        var songMetadata = mp3MetadataExtractor.apply(file);

        // Send a request to the Song Service to save the song metadata
        restTemplate.postForObject(songServiceUrl, songMetadata, Void.class);

        return savedResource;
    }

    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }

    public Optional<Resource> findById(Integer id) {
        return resourceRepository.findById(id);
    }

    public void deleteByIds(List<Integer> ids) {
        resourceRepository.deleteAllById(ids);
    }
}
