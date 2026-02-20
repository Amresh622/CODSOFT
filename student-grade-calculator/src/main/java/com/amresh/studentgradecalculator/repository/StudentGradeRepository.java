package com.amresh.studentgradecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amresh.studentgradecalculator.model.StudentGrade;

public interface StudentGradeRepository 
        extends JpaRepository<StudentGrade, Long> {

}

