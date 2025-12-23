package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.Progress;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final ProgressRepository progressRepository;
    private final CourseRepository courseRepository;

    public RecommendationServiceImpl(ProgressRepository progressRepository, CourseRepository courseRepository) {
        this.progressRepository = progressRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> recommendCourses(Long userId) {
        List<Long> completedCourseIds = progressRepository.findByUserIdAndCompletedTrue(userId)
                .stream()
                .map(progress -> progress.getLesson().getCourse().getId())
                .distinct()
                .collect(Collectors.toList());

        return courseRepository.findAll()
                .stream()
                .filter(course -> !completedCourseIds.contains(course.getId()))
                .collect(Collectors.toList());
    }
}
