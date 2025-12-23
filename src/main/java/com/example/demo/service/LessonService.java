package com.example.demo.service;

import com.example.demo.model.Lesson;
import java.util.List;

public interface LessonService {
    Lesson createLesson(Lesson lesson);
    List<Lesson> getLessonsByCourse(Long courseId);
}
