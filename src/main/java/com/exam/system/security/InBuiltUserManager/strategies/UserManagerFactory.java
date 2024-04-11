package com.exam.system.security.InBuiltUserManager.strategies;

import org.springframework.stereotype.Component;

@Component
public class UserManagerFactory {
    private final InBuiltUserManager inBuiltUserManager;

    public UserManagerFactory(InBuiltUserManager inBuiltUserManager) {
        this.inBuiltUserManager = inBuiltUserManager;
    }

    public UserManagerStrategy getUserManagerStrategy() {
        return inBuiltUserManager;
    }
}
