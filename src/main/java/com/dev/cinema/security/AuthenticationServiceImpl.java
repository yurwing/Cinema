package com.dev.cinema.security;

import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import java.util.Optional;
import javax.naming.AuthenticationException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userByEmail = userService.findByEmail(email);
        String hash = PasswordHashing.getHash(password, userByEmail.get().getSalt());
        if (userByEmail.get().getPassword().equals(hash)) {
            return userByEmail.get();
        }
        throw new AuthenticationException("Incorrect password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        byte[] salt = PasswordHashing.getSalt();
        user.setPassword(PasswordHashing.getHash(password, salt));
        user.setSalt(salt);
        return userService.add(user);
    }
}