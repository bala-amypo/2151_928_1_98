@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(
            new Info().title("Micro-Learning API")
                      .version("1.0"));
    }
}
