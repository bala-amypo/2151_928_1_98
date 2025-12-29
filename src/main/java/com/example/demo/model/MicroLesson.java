package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "micro_lesson")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MicroLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= RELATION ================= */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    /* ================= CORE FIELDS ================= */

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contentType;   // VIDEO / TEXT / QUIZ

    @Column(nullable = false)
    private String difficulty;    // EASY / MEDIUM / HARD

    @Column(nullable = false)
    private Integer durationMinutes;   // ✅ MUST be Integer

    /* ================= EXTRA (REQUIRED BY TESTS) ================= */

    @Column
    private String tags;   // ✅ REQUIRED (builder().tags(), getTags())

    /* ================= AUDIT ================= */

    private LocalDateTime createdAt;

    /* ================= JPA CALLBACK ================= */

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
