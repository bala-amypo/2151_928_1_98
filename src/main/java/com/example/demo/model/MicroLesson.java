@Entity
@Table(name = "micro_lessons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MicroLesson {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Course course;

    private String title;
    private Integer durationMinutes;
    private String contentType;
    private String difficulty;
    private String tags;
    private LocalDate publishDate;
}
