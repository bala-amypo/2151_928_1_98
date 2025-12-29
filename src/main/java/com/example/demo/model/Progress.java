package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    /* ================= FIELDS ================= */

    private Integer progressPercent;

    private String status;   // IN_PROGRESS / COMPLETED

    @Column(precision = 5, scale = 2)
    private BigDecimal score;   // ✅ MUST BE BigDecimal

    /* ================= AUDIT ================= */

    private LocalDateTime lastAccessedAt;

    /* ================= JPA CALLBACK ================= */

    @PrePersist
    public void prePersist() {
        this.lastAccessedAt = LocalDateTime.now();
    }
}
