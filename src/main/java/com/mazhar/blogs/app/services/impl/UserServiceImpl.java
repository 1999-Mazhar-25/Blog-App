package com.mazhar.blogs.app.services.impl;

import com.mazhar.blogs.app.entities.User;
import com.mazhar.blogs.app.exceptions.ResourceNotFoundException;
import com.mazhar.blogs.app.payloads.UserDto;
import com.mazhar.blogs.app.repositories.UserRepo;
import com.mazhar.blogs.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = userRepo.save(user);
         return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = userRepo.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);

        return this.userToDto(updatedUser);

    }

    @Override
    public UserDto getUserById(Integer userId) {
      User user =  this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
      return this.userToDto(user);

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDto = users.stream()
                .map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDto;
    }

    @Override
    public void deleteUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
        this.userRepo.delete(user);

    }

    private User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);

        /*user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());*/
        return user;
    }

    private UserDto userToDto(User user){

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        /*userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());*/
        return userDto;
    }
}
