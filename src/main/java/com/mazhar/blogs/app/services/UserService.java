package com.mazhar.blogs.app.services;

import com.mazhar.blogs.app.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUser();

    void deleteUserById(Integer userId);
}
