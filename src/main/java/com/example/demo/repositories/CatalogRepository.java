package com.example.demo.repositories;

import com.example.demo.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
   Optional<Catalog> findByCourseCode(Long courseCode);
}
