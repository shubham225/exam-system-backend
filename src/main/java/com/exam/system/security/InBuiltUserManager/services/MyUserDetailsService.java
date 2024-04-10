package com.exam.system.security.InBuiltUserManager.services;


import com.exam.system.models.Role;
import com.exam.system.models.User;
import com.exam.system.security.InBuiltUserManager.strategies.UserManagerFactory;
import com.exam.system.security.InBuiltUserManager.strategies.UserManagerStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserManagerFactory userManagerFactory;

    public MyUserDetailsService(UserManagerFactory userManagerFactory) {
        this.userManagerFactory = userManagerFactory;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserManagerStrategy userManagerStrategy = userManagerFactory.getUserManagerStrategy();

        User user = userManagerStrategy.getUserByUsername(username);

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
