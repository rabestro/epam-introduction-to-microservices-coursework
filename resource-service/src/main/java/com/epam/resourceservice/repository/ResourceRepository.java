package com.epam.resourceservice.repository;


import com.epam.resourceservice.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
