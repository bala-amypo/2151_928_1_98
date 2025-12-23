@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @Override
    public User register(User user) {
        if (userRepo.existsByEmail(user.getEmail()))
            throw new IllegalArgumentException("Email already exists");

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public AuthResponse login(String email, String password) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid login"));

        if (!encoder.matches(password, user.getPassword()))
            throw new IllegalArgumentException("Invalid login");

        String token = jwtUtil.generateToken(
                Map.of("role", user.getRole(), "userId", user.getId()),
                email);

        return new AuthResponse(token, user.getId(), user.getEmail(), user.getRole());
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
