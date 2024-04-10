package com.exam.system.security.InBuiltUserManager.strategies;

import com.exam.system.models.User;

public interface UserManagerStrategy {
    public User getUserByUsername(String username);
}
