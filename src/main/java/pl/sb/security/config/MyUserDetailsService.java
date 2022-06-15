package pl.sb.security.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sb.security.model.User;
import pl.sb.security.model.UserRole;
import pl.sb.security.repository.UserRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(this::createCredentials)
                .orElseThrow(() -> new UsernameNotFoundException("User" + username + "not found."));

    }

    private org.springframework.security.core.userdetails.User createCredentials(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), convertAuthorities(user.getRoles()));
    }

    private Set<SimpleGrantedAuthority> convertAuthorities(Set<UserRole> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().name()));
        }
        return authorities;
    }

}