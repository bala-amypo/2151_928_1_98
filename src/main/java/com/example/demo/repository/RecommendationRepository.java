public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByUserIdOrderByGeneratedAtDesc(Long userId);
    List<Recommendation> findByUserIdAndGeneratedAtBetween(
        Long userId, LocalDateTime start, LocalDateTime end);
}
