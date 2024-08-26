package com.example.springbootapi.controller;

import com.example.springbootapi.controller.entity.Course;
import com.example.springbootapi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        return courseRepository.findById(id)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @RequestBody Course courseDetails) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setCourseName(courseDetails.getCourseName());
                    course.setCourseDescription(courseDetails.getCourseDescription());
                    Course updatedCourse = courseRepository.save(course);
                    return ResponseEntity.ok().body(updatedCourse);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            try {
                courseRepository.delete(optionalCourse.get());
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
