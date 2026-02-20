package com.amresh.studentgradecalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.amresh.studentgradecalculator.model.StudentGrade;
import com.amresh.studentgradecalculator.service.StudentGradeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class StudentGradeController {

    @Autowired
    private StudentGradeService service;

    @PostMapping("/calculate")
    public StudentGrade calculate(@RequestBody StudentGrade student) {
        return service.calculateGrade(student);
    }
}
