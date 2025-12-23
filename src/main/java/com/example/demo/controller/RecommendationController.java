package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Course>> recommendCourses(@PathVariable Long userId) {
        return ResponseEntity.ok(recommendationService.recommendCourses(userId));
    }
}
