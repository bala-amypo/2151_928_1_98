package com.example.demo.service.impl;

import com.example.demo.model.Progress;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ProgressServiceImpl {

    private final ProgressRepository repo;
    private final UserRepository userRepo;
    private final MicroLessonRepository lessonRepo;

    public ProgressServiceImpl(ProgressRepository p, UserRepository u, MicroLessonRepository m) {
        this.repo = p;
        this.userRepo = u;
        this.lessonRepo = m;
    }

    public Progress recordProgress(Long userId, Long lessonId, Progress in) {

        // Existence check only (no entity binding needed)
        userRepo.findById(userId).orElseThrow(RuntimeException::new);
        lessonRepo.findById(lessonId).orElseThrow(RuntimeException::new);

        Progress p = repo.findByUserIdAndMicroLessonId(userId, lessonId)
                .orElse(Progress.builder().build());

        p.setStatus(in.getStatus());
        p.setProgressPercent(in.getProgressPercent());
        p.setScore(in.getScore());

        if ("COMPLETED".equals(p.getStatus()) && p.getProgressPercent() != 100) {
            throw new RuntimeException();
        }

        return repo.save(p);
    }

    public List<Progress> getUserProgress(Long userId) {
        return repo.findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
