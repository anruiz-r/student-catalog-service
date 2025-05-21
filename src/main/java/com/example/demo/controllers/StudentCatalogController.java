package com.example.demo.controllers;

import com.example.demo.models.Catalog;
import com.example.demo.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
public class StudentCatalogController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    CatalogRepository catalogRepository;

    @GetMapping("/{courseCode}")
    @ResponseStatus(HttpStatus.OK)
    public Catalog getCatalogInfo(@PathVariable Long courseCode) {

        catalogRepository.findByCourseCode(courseCode).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Catalog not found"));

        String url = "http://localhost:8082/api/students";
        return restTemplate.getForObject(url, Catalog.class);
        //ESTO FALTA POR TERMINAR!
    }
}
