package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= RELATIONS ================= */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "micro_lesson_id", nullable = false)
    private MicroLesson microLesson;

    /* ================= BUSINESS FIELDS ================= */

    @Column(nullable = false)
    private String status;          // IN_PROGRESS / COMPLETED

    @Column(nullable = false)
    private Integer progressPercent; // ✅ Integer (NOT BigDecimal)

    private Integer score;            // ✅ Integer (NOT BigDecimal)

    /* ================= AUDIT FIELDS ================= */

    private LocalDateTime createdAt;
    private LocalDateTime lastAccessedAt;

    /* ================= JPA CALLBACK ================= */

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.lastAccessedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastAccessedAt = LocalDateTime.now();
    }
}
