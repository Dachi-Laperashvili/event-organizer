package com.example.eventorganizer.security;

import com.example.eventorganizer.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/", "/registration").
                                permitAll()
                                .anyRequest().
                                authenticated())

                .formLogin(login -> login.
                        loginPage("/login").
                        permitAll())

                .logout(logout -> logout.
                        logoutUrl("/logout").
                        logoutSuccessUrl("/login") );
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
