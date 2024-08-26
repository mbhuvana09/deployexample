package com.example.springbootapi.controller.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    private String courseName;
    private String courseDescription;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<StudentCourse> studentCourses = new HashSet<>();

    // Getters and Setters

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

//    public Set<StudentCourse> getStudentCourses() {
//        return studentCourses;
//    }

//    public void setStudentCourses(Set<StudentCourse> studentCourses) {
//        this.studentCourses = studentCourses;
//    }
}
