package com.amresh.studentgradecalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amresh.studentgradecalculator.model.StudentGrade;
import com.amresh.studentgradecalculator.repository.StudentGradeRepository;

@Service
public class StudentGradeService {

    @Autowired
    private StudentGradeRepository repository;

    public StudentGrade calculateGrade(StudentGrade student) {

        int total = student.getSubject1()
                + student.getSubject2()
                + student.getSubject3();

        double average = total / 3.0;

        String grade;

        if (average >= 90) grade = "A+";
        else if (average >= 80) grade = "A";
        else if (average >= 70) grade = "B";
        else if (average >= 60) grade = "C";
        else if (average >= 50) grade = "D";
        else grade = "F";

        student.setTotal(total);
        student.setAverage(average);
        student.setGrade(grade);

        return repository.save(student);
    }
}
