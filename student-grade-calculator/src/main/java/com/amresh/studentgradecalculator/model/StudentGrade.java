package com.amresh.studentgradecalculator.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_grade")
public class StudentGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int subject1;
    private int subject2;
    private int subject3;

    private int total;
    private double average;
    private String grade;

    // Getters and Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getSubject1() { return subject1; }

    public void setSubject1(int subject1) { this.subject1 = subject1; }

    public int getSubject2() { return subject2; }

    public void setSubject2(int subject2) { this.subject2 = subject2; }

    public int getSubject3() { return subject3; }

    public void setSubject3(int subject3) { this.subject3 = subject3; }

    public int getTotal() { return total; }

    public void setTotal(int total) { this.total = total; }

    public double getAverage() { return average; }

    public void setAverage(double average) { this.average = average; }

    public String getGrade() { return grade; }

    public void setGrade(String grade) { this.grade = grade; }
}
