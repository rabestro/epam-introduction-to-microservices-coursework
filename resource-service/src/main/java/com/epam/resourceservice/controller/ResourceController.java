package com.epam.resourceservice.controller;

import com.epam.resourceservice.model.Resource;
import com.epam.resourceservice.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<Resource> uploadResource(@RequestBody Resource resource) {
        Resource savedResource = resourceService.save(resource);
        return ResponseEntity.ok(savedResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResource(@PathVariable Integer id) {
        Resource resource = resourceService.findById(id);
        if (resource == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(resource);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteResources(@RequestParam List<Integer> ids) {
        resourceService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }
}
