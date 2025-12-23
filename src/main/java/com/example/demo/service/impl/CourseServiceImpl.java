package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElse(null);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
}
    `