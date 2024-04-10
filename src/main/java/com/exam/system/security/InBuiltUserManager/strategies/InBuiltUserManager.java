package com.exam.system.security.InBuiltUserManager.strategies;

import com.exam.system.models.User;
import com.exam.system.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InBuiltUserManager implements UserManagerStrategy {
    private final UserRepository userRepository;

    public InBuiltUserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("User '"+ username + "' doesn't exists");

        return userOptional.get();
    }
}
