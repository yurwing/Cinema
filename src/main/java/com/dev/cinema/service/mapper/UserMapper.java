package com.dev.cinema.service.mapper;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.request.UserRequestDto;
import com.dev.cinema.model.dto.response.UserResponseDto;
import com.dev.cinema.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements MapperToDto<User,
        UserResponseDto>, MapperToEntity<User, UserRequestDto> {
    private final RoleService roleService;

    @Autowired
    public UserMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UserResponseDto getDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    @Override
    public User getEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }
}
