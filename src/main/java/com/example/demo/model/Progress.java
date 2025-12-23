@Entity
@Table(name = "progress")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Progress {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private MicroLesson microLesson;

    private String status;
    private Integer progressPercent;
    private BigDecimal score;
    private LocalDateTime lastAccessedAt;

    @PrePersist
    void init() {
        lastAccessedAt = LocalDateTime.now();
    }
}
