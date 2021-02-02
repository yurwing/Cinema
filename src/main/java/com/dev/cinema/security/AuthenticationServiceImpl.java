package com.dev.cinema.security;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userByEmail = userService.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Cannot get user by email "
                        + email));
        String hash = PasswordHashing.getHash(password, userByEmail.getSalt());
        if (userByEmail.getPassword().equals(hash)) {
            return userByEmail;
        }
        throw new AuthenticationException("Incorrect password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }
}
