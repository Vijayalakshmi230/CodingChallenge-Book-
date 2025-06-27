package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.config.JwtUtils;
import com.example.demo.exception.UsernameNotFoundException;
import com.example.demo.repo.UserRepository;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.demo.entity.User user = repo.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public String login(String username, String password) {
        com.example.demo.entity.User user = repo.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
        	UserDetails userDetails = loadUserByUsername(username);
        	return new JwtUtils().generateToken(userDetails);  // ✅ Correct
        }

        throw new RuntimeException("Invalid credentials");
    }
}


