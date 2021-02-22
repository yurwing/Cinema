package com.dev.cinema.security;

import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Cannot get user by email " + email));
        UserBuilder builder = org.springframework.security.core.userdetails.User
                .withUsername(email);
        builder.password(user.getPassword());
        builder.roles(user.getRoles().stream()
                .map(s -> s.getRoleName().toString())
                .toArray(String[]::new));
        return builder.build();
    }
}
