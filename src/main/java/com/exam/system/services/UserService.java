package com.exam.system.services;

import com.exam.system.dtos.UserSignupRequestDto;
import com.exam.system.dtos.UserSignupResponseDto;
import com.exam.system.models.Role;
import com.exam.system.models.User;
import com.exam.system.repositories.RoleRepository;
import com.exam.system.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserSignupResponseDto userSignup(UserSignupRequestDto userSignupRequestDto) {
        User user = createUser(userSignupRequestDto, "USER");
        return new UserSignupResponseDto(user);
    }

    public User createUser(UserSignupRequestDto userSignupRequestDto,
                           String role) {
        User user = new User();
        user.setActive(true);
        user.setUsername(userSignupRequestDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userSignupRequestDto.getPassword()));
        user.setCreatedOn(new Date());

        Optional<Role> roleOptional = roleRepository.findByRole(role);

        if(roleOptional.isEmpty())
            user.getRoles().add(new Role(role));
        else
            user.getRoles().add(roleOptional.get());

        user = userRepository.save(user);

        return user;
    }

    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User '"+ username + "' doesn't exists");

        return userOptional.get();
    }
}
