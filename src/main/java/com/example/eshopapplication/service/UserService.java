package com.example.eshopapplication.service;


import com.example.eshopapplication.dto.UserDto;
import com.example.eshopapplication.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
