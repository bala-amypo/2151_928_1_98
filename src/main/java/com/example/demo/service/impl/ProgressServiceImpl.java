package com.example.demo.service.impl;

import com.example.demo.model.Lesson;
import com.example.demo.model.Progress;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.service.ProgressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final LessonRepository lessonRepository;

    public ProgressServiceImpl(ProgressRepository progressRepository, LessonRepository lessonRepository) {
        this.progressRepository = progressRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Progress markLessonComplete(Long userId, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Progress progress = new Progress();
        progress.setUserId(userId);
        progress.setLesson(lesson);
        progress.setCompleted(true);

        return progressRepository.save(progress);
    }

    @Override
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository.findByUserId(userId);
    }
}
