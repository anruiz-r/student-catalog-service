package com.example.demo.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {
    private Long courseCode;
    private String courseName;
    private List<StudentGrade> studentGrades;

}
