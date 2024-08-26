package com.example.springbootapi.controller.entity;

import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Column(name = "pswd", nullable = false, length = 255)
    private String pswd;

    @Enumerated(EnumType.STRING)
    @Column(name = "person", nullable = false, columnDefinition = "ENUM('student', 'faculty') DEFAULT 'student'")
    private PersonType person = PersonType.student;

//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<StudentCourse> studentCourses = new HashSet<>();

    // Constructors, getters, and setters

    public Student() {
        // Default constructor
    }

    public Student(String email, String firstName, String lastName, String pswd, PersonType person) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pswd = pswd;
        this.person = person;
    }

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public PersonType getPerson() {
        return person;
    }

    public void setPerson(PersonType person) {
        this.person = person;
    }

//    public Set<StudentCourse> getStudentCourses() {
//        return studentCourses;
//    }
//
//    public void setStudentCourses(Set<StudentCourse> studentCourses) {
//        this.studentCourses = studentCourses;
//    }

    // Enum for person type
    public enum PersonType {
        student, faculty
    }
}
+