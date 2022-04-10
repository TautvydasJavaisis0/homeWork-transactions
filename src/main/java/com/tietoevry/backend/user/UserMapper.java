package com.tietoevry.backend.user;

import com.tietoevry.backend.user.model.Role;
import com.tietoevry.backend.user.model.User;
import com.tietoevry.backend.user.model.UserCreationDto;
import com.tietoevry.backend.user.model.UserDto;

import java.util.List;
import java.util.Optional;

public class UserMapper {
    private UserMapper() {}

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .build();
    }

    public static User toUser(UserDto userDto, Optional<List<Role>> roleList) {
        return User.builder()
            .id(userDto.getId())
            .username(userDto.getUsername())
            .email(userDto.getEmail())
            .firstName(userDto.getFirstName())
            .lastName(userDto.getLastName())
            .role(roleList.get())
            .build();
    }


    public static User toUser(UserCreationDto userDto, Optional<List<Role>> roleList) {
        return User.builder()
            .username(userDto.getUsername())
            .email(userDto.getEmail())
            .password(userDto.getPassword())
            .firstName(userDto.getFirstName())
            .lastName(userDto.getLastName())
            .role(roleList.get())
            .build();
    }
}
