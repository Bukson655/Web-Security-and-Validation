package pl.sb.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sb.security.model.Role;
import pl.sb.security.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRole(Role role);

}