@Entity
@Table(name = "courses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;
    private String category;

    @ManyToOne
    private User instructor;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
