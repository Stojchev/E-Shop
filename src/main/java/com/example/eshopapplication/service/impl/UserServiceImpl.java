package com.example.eshopapplication.service.impl;

import com.example.eshopapplication.dto.UserDto;
import com.example.eshopapplication.entity.Role;
import com.example.eshopapplication.entity.User;
import com.example.eshopapplication.repository.RoleRepository;
import com.example.eshopapplication.repository.UserRepository;
import com.example.eshopapplication.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        Role role = roleRepository.findByName("ADMIN");
        Role role1=roleRepository.findByName("USER");
//        if(role == null){
//            role = checkRoleExist("ADMIN");
//        }
        if(role1 == null){
            role1= checkRoleExist("USER");
        }
//        user.addRole(role);
        user.addRole(role1);
        userRepository.save(user);

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
    private Role checkRoleExist(String nameOfRole){
        Role role = new Role();
        role.setName(nameOfRole);
        return roleRepository.save(role);
    }
}
