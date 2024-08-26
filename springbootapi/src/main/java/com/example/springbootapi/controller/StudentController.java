package com.example.springbootapi.controller;

import com.example.springbootapi.controller.entity.Student;
import com.example.springbootapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // GET all students
    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET student by ID
    @GetMapping("/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        Optional<Student> student = studentRepository.findById(email);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create a new student
    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            Student createdStudent = studentRepository.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT update an existing student
    @PutMapping("/{email}")
    public ResponseEntity<Student> updateStudent(@PathVariable String email, @RequestBody Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(email);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setFirstName(studentDetails.getFirstName());
            existingStudent.setLastName(studentDetails.getLastName());
            existingStudent.setPswd(studentDetails.getPswd());
            existingStudent.setPerson(studentDetails.getPerson());

            try {
                Student updatedStudent = studentRepository.save(existingStudent);
                return ResponseEntity.ok(updatedStudent);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a student
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String email) {
        Optional<Student> optionalStudent = studentRepository.findById(email);
        if (optionalStudent.isPresent()) {
            try {
                studentRepository.delete(optionalStudent.get());
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
