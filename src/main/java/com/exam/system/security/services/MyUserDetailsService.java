package com.exam.system.security.services;


import com.exam.system.models.Role;
import com.exam.system.models.User;
import com.exam.system.services.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final IUserService userService;

    public MyUserDetailsService(IUserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(getGrantedAuthorities(user.getRoles()))
                .accountExpired(user.isAccountExpired())
                .accountLocked(user.isAccountLocked())
                .credentialsExpired(user.isCredentialsExpired())
                .disabled(!user.isActive())
                .build();
    }

    private String[] getGrantedAuthorities(Set<Role> roles) {
        String[] roleArray = new String[roles.size()];
        int i = 0;

        for(Role role : roles) {
            roleArray[i] = role.getRole();
            i++;
        }

        return roleArray;
    }
}
