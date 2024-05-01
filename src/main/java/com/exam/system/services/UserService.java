package com.exam.system.services;

import com.exam.system.dtos.user.UserResponseDto;
import com.exam.system.dtos.user.UserSignupRequestDto;
import com.exam.system.dtos.user.UserSignupResponseDto;
import com.exam.system.models.Role;
import com.exam.system.models.User;
import com.exam.system.repositories.RoleRepository;
import com.exam.system.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

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

    public UserResponseDto getUserDetails(long id) {
        User user = getUserById(id);

        return new UserResponseDto(user);
    }

    public UserResponseDto getUserDetails(String username) {
        User user = getUserByUsername(username);

        return new UserResponseDto(user);
    }

    public User createUser(UserSignupRequestDto userSignupRequestDto,
                           String role) {
        User user = new User();
        user.setActive(true);
        user.setUsername(userSignupRequestDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userSignupRequestDto.getPassword()));
        user.setFullName(userSignupRequestDto.getFullName());
        user.setGender(userSignupRequestDto.getGender());
        user.setInstitute(userSignupRequestDto.getInstitute());
        user.setDegree(userSignupRequestDto.getDegree());
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

    public User getUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User '"+ id + "' doesn't exists");

        return userOptional.get();
    }
}
