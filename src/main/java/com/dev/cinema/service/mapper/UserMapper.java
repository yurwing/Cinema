package com.dev.cinema.service.mapper;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.request.UserRequestDto;
import com.dev.cinema.model.dto.response.UserResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements MapperToDto<User,
        UserResponseDto>, MapperToEntity<User, UserRequestDto> {
    @Override
    public UserResponseDto getDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRoles(user.getRole().stream()
                .map(s -> s.getRoleName().getRole())
                .collect(Collectors.toList()));
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
