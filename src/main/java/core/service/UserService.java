package core.service;

import core.repository.UserRepository;
import core.model.CustomUser;
import core.security.UserRole;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private PasswordEncoder encoder = PasswordEncoderFactories
            .createDelegatingPasswordEncoder();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addUser(String username, String password) {
        CustomUser user = new CustomUser(username,
                encoder.encode(password), UserRole.USER);
        userRepository.save(user);
    }
}
