package com.example.demo.services;

import com.example.demo.models.Catalog;
import com.example.demo.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CatalogService {

    @Autowired
    CatalogRepository catalogRepository;
    public Optional<Catalog> getCourseByCode(Long courseCode) {
        return catalogRepository.findById(courseCode);
    }
}
