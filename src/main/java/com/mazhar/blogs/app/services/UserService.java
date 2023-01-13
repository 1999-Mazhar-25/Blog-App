package com.mazhar.blogs.app.services;

import com.mazhar.blogs.app.payloads.UserDto;
import com.mazhar.blogs.app.payloads.UserResponse;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto getUserById(Integer userId);

    UserResponse getAllUser(Integer pageNumber, Integer pageSize);

    void deleteUserById(Integer userId);
}
