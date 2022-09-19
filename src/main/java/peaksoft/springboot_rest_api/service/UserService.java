package peaksoft.springboot_rest_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.springboot_rest_api.dto.UserRequest;
import peaksoft.springboot_rest_api.dto.UserResponse;
import peaksoft.springboot_rest_api.entity.User;
import peaksoft.springboot_rest_api.repository.UserRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest request) {
        User user = mapToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
       return mapToResponse(userRepository.save(user));
    }

    public User mapToEntity(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .isActive(true)
                .created(LocalDate.now())
                .build();

    }

    public UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .created(user.getCreated())
                .isActive(user.isActive())
                .build();
    }
}
