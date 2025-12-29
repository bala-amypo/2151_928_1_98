package com.example.demo.service.impl;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

import com.example.demo.repository.*;
@Service
public class CourseServiceImpl {

    private final CourseRepository courseRepo;
    private final UserRepository userRepo;

    public CourseServiceImpl(CourseRepository c, UserRepository u) {
        this.courseRepo = c;
        this.userRepo = u;
    }

    public Course createCourse(Course course, Long instructorId) {
        User u = userRepo.findById(instructorId).orElseThrow(RuntimeException::new);
        if (!"INSTRUCTOR".equals(u.getRole()) && !"ADMIN".equals(u.getRole()))
            throw new RuntimeException();

        if (courseRepo.existsByTitleAndInstructorId(course.getTitle(), instructorId))
            throw new RuntimeException();

        course.setInstructor(u);
        return courseRepo.save(course);
    }

    public Course updateCourse(Long id, Course data) {
        Course c = courseRepo.findById(id).orElseThrow(RuntimeException::new);
        c.setTitle(data.getTitle());
        c.setDescription(data.getDescription());
        return courseRepo.save(c);
    }

    public Course getCourse(Long id) {
        return courseRepo.findById(id).orElseThrow(RuntimeException::new);
    }
}
