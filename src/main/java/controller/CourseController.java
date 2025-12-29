package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.impl.CourseServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @PostMapping("/{instructorId}")
    public Course createCourse(
            @PathVariable Long instructorId,
            @RequestBody Course course) {
        return courseService.createCourse(course, instructorId);
    }
}
