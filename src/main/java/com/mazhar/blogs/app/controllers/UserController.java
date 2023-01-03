package com.mazhar.blogs.app.controllers;

import com.mazhar.blogs.app.payloads.ApiResponse;
import com.mazhar.blogs.app.payloads.UserDto;
import com.mazhar.blogs.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


   // POST - create user

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //PUT - update user

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("userId") Integer uid,
                                              @RequestBody UserDto userDto){
       UserDto userDto1 =  this.userService.updateUser(userDto,uid);
//       return new ResponseEntity<>(userDto1 ,HttpStatus.ACCEPTED)
        return ResponseEntity.ok(userDto1);
    }

    //DELETE - delete user by Id

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") Integer uid){
        this.userService.deleteUserById(uid);
        return new ResponseEntity<ApiResponse>
                (new ApiResponse("DELETED SUCCESSFULLY",true),HttpStatus.CREATED);
    }


    //GET - get user by Id

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer uid){
       UserDto userDto = this.userService.getUserById(uid);
       return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    //GET - get All users

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = this.userService.getAllUser();
        return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
    }

}
