package com.example.demo.controllers;

import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.CourseGradeDTO;
import com.example.demo.dto.StudentInfoDTO;
import com.example.demo.models.Catalog;
import com.example.demo.models.StudentGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
public class StudentCatalogController {
    @Autowired
   RestTemplate restTemplate;


    @GetMapping("/{courseCode}")
    @ResponseStatus(HttpStatus.OK)
    public Catalog getCatalogInfo(@PathVariable Long courseCode) {

        CourseDTO course = restTemplate.getForObject("http://localhost:8083/api/courses/"+ courseCode, CourseDTO.class);
        if (course == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
        }
        CourseGradeDTO[] gradesArray = restTemplate.getForObject("http://localhost:8083/api/grades/" + courseCode, CourseGradeDTO[].class);
        List<CourseGradeDTO> grades = Arrays.asList(gradesArray);

        List<StudentGrade> studentsGrades = new ArrayList<>();

        for(CourseGradeDTO grade : grades) {
            StudentInfoDTO student = restTemplate.getForObject("http://localhost:8082/api/students/"+ grade.getStudentId(), StudentInfoDTO.class);
            if (student == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
            }
            studentsGrades.add(new StudentGrade(student.getName(), student.getAge(), grade.getGrade()));
        }

        return new Catalog(course.getName(), studentsGrades);
    }
}