package pl.sb.security.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sb.security.model.Role;
import pl.sb.security.model.User;
import pl.sb.security.model.UserRole;
import pl.sb.security.model.dto.UserDto;
import pl.sb.security.repository.UserRepository;
import pl.sb.security.repository.UserRoleRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(UserDto userDto) {
        boolean isNewUserRegistered = false;
        boolean isUsernameAlreadyTaken = userRepository.existsUserByUsername(userDto.getUsername());
        if (!isUsernameAlreadyTaken) {
            User user = userMapper.convertFromDto(userDto);
            userRepository.save(user);
            isNewUserRegistered = true;
        }
        return isNewUserRegistered;
    }

    public UserDto findUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return UserMapper.convertToDto(user);
        } else {
            throw new UsernameNotFoundException("User" + username + "not found.");
        }
    }

    @Transactional
    public void edit(UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userDto.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            setNewPassword(userDto, user);
            user.setAddress(userDto.getAddress());
            user.setPostalCode(userDto.getPostalCode());
            user.setCity(userDto.getCity());
            user.setEmail(userDto.getEmail());
        } else {
            throw new UsernameNotFoundException("User" + userDto.getUsername() + "not found.");
        }
    }

    public List<UserDto> findAllWithRole(Role role) {
        UserRole userRole = userRoleRepository.findByRole(role);
        return userRepository.findAllByRolesContaining(userRole)
                .stream()
                .map(UserMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public boolean isUsernameIsAlreadyTaken(String username) {
        return userRepository.existsUserByUsername(username);
    }

    private void setNewPassword(UserDto userDto, User user) {
        if (!userDto.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
    }

}