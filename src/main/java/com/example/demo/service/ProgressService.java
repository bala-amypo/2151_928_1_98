package com.example.demo.service;

import com.example.demo.model.Progress;

import java.util.List;

public interface ProgressService {
    Progress markLessonComplete(Long userId, Long lessonId);
    List<Progress> getUserProgress(Long userId);
}
