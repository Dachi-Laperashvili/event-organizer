package com.example.eventorganizer.registration;

import com.example.eventorganizer.user.User;
import com.example.eventorganizer.user.UserRepository;
import com.example.eventorganizer.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private RegistrationService registrationService;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        registrationService = new RegistrationService(userRepository, bCryptPasswordEncoder);
    }
    @Test
    public void testRegister(){
        RegistrationRequest request  = new RegistrationRequest(
                "William",
                "Woe",
                "william.woe@example.com",
                "password"
        );
        when(bCryptPasswordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        User user = registrationService.register(request);

        verify(userRepository).save(user);

        assertEquals("William",user.getFirstName());
        assertEquals("Woe",user.getLastName());
        assertEquals("william.woe@example.com",user.getEmail());
        assertEquals("encodedPassword",user.getPassword());
        assertEquals(UserRole.USER,user.getRole());
    }
}