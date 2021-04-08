package com.seb.recommender.security.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!this.username.equals(username)) {
            log.error("credential mismatch");
            throw new UsernameNotFoundException("credential mismatch");
        }
        return new User(username, password, new ArrayList<>());
    }
}
