package com.epam.resourceservice.controller;

import com.epam.resourceservice.model.Resource;
import com.epam.resourceservice.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    private final ResourceService resourceService;
    Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Integer>> uploadResource(@RequestParam("file") MultipartFile file) throws IOException {
        var savedResource = resourceService.save(file);
        var responseBody = Map.of("id", savedResource.getId());
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getResource(@PathVariable Integer id) {
        return resourceService.findById(id)
                .map(Resource::getData)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Map<String, List<Integer>>> deleteResources(@RequestParam List<Integer> ids) {
        resourceService.deleteByIds(ids);
        var responseBody = Map.of("ids", ids);
        return ResponseEntity.ok(responseBody);
    }
}
