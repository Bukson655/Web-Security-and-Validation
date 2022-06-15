package pl.sb.security.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sb.security.model.Role;
import pl.sb.security.model.User;
import pl.sb.security.repository.UserRepository;
import pl.sb.security.repository.UserRoleRepository;

import javax.transaction.Transactional;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    public UserRoleService(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void degradeAdmin(Long id) {
        userRepository.findByIdEqualsAndRolesContaining(id, userRoleRepository.findByRole(Role.ROLE_ADMIN))
                .map(this::removeAdminRole)
                .orElseThrow(() -> new UsernameNotFoundException("User with ID" + id + "not found."));
    }

    @Transactional
    public void promoteToAdmin(Long id) {
        userRepository.findById(id)
                .map(this::addAdminRole)
                .orElseThrow(() -> new UsernameNotFoundException("User with ID" + id + "not found."));
    }

    private boolean removeAdminRole(User user) {
        return user.getRoles().remove(userRoleRepository.findByRole(Role.ROLE_ADMIN));
    }

    private boolean addAdminRole(User user) {
        return user.getRoles().add(userRoleRepository.findByRole(Role.ROLE_ADMIN));
    }

}