package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long courseCode;
    private String courseName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "catalog_id")
    private List<StudentGrade> studentGrades;

}
