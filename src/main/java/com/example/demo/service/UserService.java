public interface UserService {
    User register(User user);
    AuthResponse login(String email, String password);
    User findById(Long id);
}
