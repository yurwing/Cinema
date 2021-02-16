package com.dev.cinema.controller;

import com.dev.cinema.model.dto.response.UserResponseDto;
import com.dev.cinema.service.UserService;
import com.dev.cinema.service.mapper.UserMapper;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto get(@RequestParam @NotNull @Size(min = 4) @Valid String email) {
        return userMapper.getDto(userService.findByEmail(email).get());
    }
}
