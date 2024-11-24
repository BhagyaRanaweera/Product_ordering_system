package com.phegondev.phegon.productorderingsystem.security;
import com.phegondev.phegon.productorderingsystem.entity.User;
import com.phegondev.phegon.productorderingsystem.exeption.NotFoundExeption;
import com.phegondev.phegon.productorderingsystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.CustomerUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundExeption;
import org.springframework.stereotype.service;

@service
@RequiredArgsConstructor

public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundExeption{
        User user=userRepo.findByEmail(username).orElseThrow(()-> new NotFoundExeption("User/Email not found"));

        return AuthUser.builder()
        .user(user)
        .build();
    }
}
