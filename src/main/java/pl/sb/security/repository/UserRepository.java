package pl.sb.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sb.security.model.Role;
import pl.sb.security.model.User;
import pl.sb.security.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByIdEqualsAndRolesContaining(Long id, UserRole userRole);

    boolean existsUserByUsername(String username);

    List<User> findAllByRolesContaining(UserRole userRole);

}