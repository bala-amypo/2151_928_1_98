@Entity
@Table(name = "recommendations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime generatedAt;
    private String recommendedLessonIds;
    private String basisSnapshot;
    private BigDecimal confidenceScore;

    @PrePersist
    void init() {
        generatedAt = LocalDateTime.now();
    }
}
