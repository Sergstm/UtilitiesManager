package core.service;

import core.repository.UserRepository;
import core.model.CustomUser;
import core.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomUser> login = userRepository.findByLogin(username);
        login.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return login.map(CustomUserDetails::new).get();
    }
}
