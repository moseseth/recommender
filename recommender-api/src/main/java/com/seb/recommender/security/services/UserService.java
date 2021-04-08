package com.seb.recommender.security.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(!userName.equals(username)) {
            throw new UsernameNotFoundException("credential mismatch");
        }
        return new User(username, password, new ArrayList<>());
    }
}
