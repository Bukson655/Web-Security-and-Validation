package pl.sb.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.sb.security.model.Role;
import pl.sb.security.model.User;
import pl.sb.security.model.UserRole;
import pl.sb.security.model.dto.UserDto;
import pl.sb.security.repository.UserRoleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserMapper(PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    User convertFromDto(UserDto userDto) {
        User user = new User();
        user.setId(user.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(reformatStringData(userDto.getFirstName()));
        user.setLastName(reformatStringData(userDto.getLastName()));
        user.setAddress(StringUtils.capitalize(userDto.getAddress()));
        user.setPostalCode(userDto.getPostalCode());
        user.setCity(reformatStringData(userDto.getCity()));
        user.setEmail(userDto.getEmail().toLowerCase());
        user.setTermsAgreement(userDto.isTermsAgreement());
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRoleRepository.findByRole(Role.ROLE_USER));
        user.setRoles(roles);
        return user;
    }

    private String reformatStringData(String data) {
        return StringUtils.capitalize(data.toLowerCase());
    }

    public static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAddress(user.getAddress());
        userDto.setPostalCode(user.getPostalCode());
        userDto.setCity(user.getCity());
        userDto.setEmail(user.getEmail());
        userDto.setTermsAgreement(user.isTermsAgreement());
        userDto.setRoles(user.getRoles());
        return userDto;
    }

}