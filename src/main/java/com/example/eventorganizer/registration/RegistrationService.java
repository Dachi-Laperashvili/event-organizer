package com.example.eventorganizer.registration;

import com.example.eventorganizer.user.User;
import com.example.eventorganizer.user.UserRepository;
import com.example.eventorganizer.user.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(RegistrationRequest request){
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                bCryptPasswordEncoder.encode(request.getPassword()),
                UserRole.USER
        );

        userRepository.save(user);
        return user;
    }
}
