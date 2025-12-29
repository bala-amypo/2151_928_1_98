package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.util.List;

public class ProgressServiceImpl {

    private final ProgressRepository repo;
    private final UserRepository userRepo;
    private final MicroLessonRepository lessonRepo;

    public ProgressServiceImpl(ProgressRepository p, UserRepository u, MicroLessonRepository m) {
        repo = p; userRepo = u; lessonRepo = m;
    }

    public Progress recordProgress(Long userId, Long lessonId, Progress in) {
        User u = userRepo.findById(userId).orElseThrow(RuntimeException::new);
        MicroLesson ml = lessonRepo.findById(lessonId).orElseThrow(RuntimeException::new);

        Progress p = repo.findByUserIdAndMicroLessonId(userId, lessonId)
                .orElse(Progress.builder().user(u).microLesson(ml).build());

        p.setStatus(in.getStatus());
        p.setProgressPercent(in.getProgressPercent());
        p.setScore(in.getScore());

        if ("COMPLETED".equals(p.getStatus()) && p.getProgressPercent() != 100)
            throw new RuntimeException();

        return repo.save(p);
    }

    public List<Progress> getUserProgress(Long userId) {
        return repo.findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
