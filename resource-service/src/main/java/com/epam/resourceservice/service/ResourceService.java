package com.epam.resourceservice.service;

import com.epam.resourceservice.model.Resource;
import com.epam.resourceservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
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
